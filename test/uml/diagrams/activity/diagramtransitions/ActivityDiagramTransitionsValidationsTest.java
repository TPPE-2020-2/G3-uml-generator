package uml.diagrams.activity.diagramtransitions;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.ActivityDiagramTransitions;
import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityDiagramTransitionsValidationsTest {
	private ActivityDiagramTransitions diagramTransitions;
	
	private final static String MULTIPLE_START_NODE_ERROR_MESSAGE = "Um diagrama de atividades deve ter somente um start node!";
	private final static String CONNECTING_WITH_START_NODE_ERROR_MESSAGE = "Um start node não pode estar numa transição como target";
	private final static String START_NODE_WITH_NON_ACTIVITY_TARGET_ERROR_MESSAGE = "Um diagrama de atividades deve se conectar somente à um activity!";
	private final static String NULL_VALUES_ERROR_MESSAGE = "Não é possível inserir valores nulos!";
	private final static String MULTIPLE_ACTIVITY_TRANSITIONS_ERROR_MESSAGE = "Não é possível ter mais de uma transição para um mesmo activity node";
	private final static String MULTIPLE_DECISION_NODE_TARGETS = "Não é possível um mesmo decision node ser target de múltiplos elements; Use um merge node";
	private final static String MULTIPLE_MERGE_NODE_TARGETS = "Não é possível um mesmo mrege node ser possuir multiplos targes; Use um decision node";
	private final static String FINAL_NODE_AS_SOURCE_ERROR_MESSAGE = "Um final node não pode se conectar com outro elemento como um source";

	private final static String NAME = "transition";
	private final static Float PROB = 0.5f;
	
	private static StartNode startNode;
	private static ActivityNode activityNode;
	private static DecisionNode decisionNode;
	private static MergeNode mergeNode;
	private static FinalNode finalNode;
	
	@BeforeAll
	public static void initialSetup() throws ActivityDiagramRuleException {
		startNode = new StartNode("start");
		activityNode = new ActivityNode("activity");
		decisionNode = new DecisionNode("decision");
		mergeNode = new MergeNode("merge");
		finalNode = new FinalNode("final");
	}

	@BeforeEach
	public void setup() {
		diagramTransitions = new ActivityDiagramTransitions();
	}
	
	public static Collection<Object[]> validScenarios() throws ActivityDiagramRuleException {
		return Arrays.asList(new Object[][] {
			{ Arrays.asList(
					new TransitionTempHolder(NAME, PROB, startNode, activityNode))},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, activityNode, new ActivityNode("activity2")))},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, activityNode, decisionNode))},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, activityNode, mergeNode))},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, activityNode, finalNode))},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, decisionNode, activityNode),
	        		new TransitionTempHolder(NAME + "2", PROB, decisionNode, new ActivityNode("activity2")),
	        		new TransitionTempHolder(NAME + "3", PROB, decisionNode, mergeNode))},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, activityNode, mergeNode),
	        		new TransitionTempHolder(NAME + "2", PROB, new ActivityNode("activity2"), mergeNode),
	        		new TransitionTempHolder(NAME + "3", PROB, decisionNode, mergeNode))},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, activityNode, finalNode))},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, activityNode, finalNode),
	        		new TransitionTempHolder(NAME + "2", PROB, mergeNode, finalNode))},
		});
	}
	
	@ParameterizedTest
	@MethodSource("validScenarios")
	public void testValidScenarios(List<TransitionTempHolder> values) {
		
		assertDoesNotThrow(() -> {
			for (TransitionTempHolder transition : values) 
				diagramTransitions.addTransition(transition.getName(), transition.getProb(), transition.getSource(), transition.getTarget());
		});
	}
	
	public static Collection<Object[]> invalidScenarios() throws ActivityDiagramRuleException {
		return Arrays.asList(new Object[][] {
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, startNode, activityNode),
	        		new TransitionTempHolder(NAME + "2", PROB, startNode, activityNode)),
	        		MULTIPLE_START_NODE_ERROR_MESSAGE},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, startNode, decisionNode)),
	        		START_NODE_WITH_NON_ACTIVITY_TARGET_ERROR_MESSAGE},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, startNode, null)),
        			NULL_VALUES_ERROR_MESSAGE},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, null, decisionNode)),
    				NULL_VALUES_ERROR_MESSAGE},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, activityNode, new ActivityNode("activity2")),
					new TransitionTempHolder(NAME + "2", PROB, activityNode, new ActivityNode("activity3"))),
					MULTIPLE_ACTIVITY_TRANSITIONS_ERROR_MESSAGE},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, activityNode, startNode)),
					CONNECTING_WITH_START_NODE_ERROR_MESSAGE},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, activityNode, decisionNode),
	        		new TransitionTempHolder(NAME + "2", PROB, new ActivityNode("activity2"), decisionNode)),
					MULTIPLE_DECISION_NODE_TARGETS},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, mergeNode, activityNode),
	        		new TransitionTempHolder(NAME + "2", PROB, mergeNode, new ActivityNode("activity2"))),
					MULTIPLE_MERGE_NODE_TARGETS},	        
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, finalNode, activityNode)),
					FINAL_NODE_AS_SOURCE_ERROR_MESSAGE},
		});
	}
	
	@ParameterizedTest
	@MethodSource("invalidScenarios")
	public void testInvalidScenarios(List<TransitionTempHolder> values, String expectedErrorMessage) {
		
		ActivityDiagramRuleException exception = assertThrows(ActivityDiagramRuleException.class, () -> {
			for (TransitionTempHolder transition : values) 
				diagramTransitions.addTransition(transition.getName(), transition.getProb(), transition.getSource(), transition.getTarget());
		});
		
		assertEquals(expectedErrorMessage, exception.getMessage());
	}
}
