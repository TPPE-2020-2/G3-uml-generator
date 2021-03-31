package uml.diagrams.activity.diagramtransitions;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.ActivityDiagramTransitions;
import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityDiagramTransitionsTest {
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
	
	@Test
	public void testTransitionFromStartNodeToActivity() {
		
		assertDoesNotThrow(() -> {
			diagramTransitions.addTransition(NAME, PROB, startNode, activityNode);
		});
	}
	
	@Test
	public void testErrorTransitionFromStartNodeToActivityMultipleTimes() {
		
		ActivityDiagramRuleException exception = assertThrows(ActivityDiagramRuleException.class, () -> {
			diagramTransitions.addTransition(NAME, PROB, startNode, activityNode);
			diagramTransitions.addTransition(NAME + "1", PROB, startNode, activityNode);
		});
		
		assertEquals(MULTIPLE_START_NODE_ERROR_MESSAGE, exception.getMessage());
	}
	
	@Test
	public void testErrorTransitionFromStartNodeToNonActivityNode() {
		
		ActivityDiagramRuleException exception = assertThrows(ActivityDiagramRuleException.class, () -> {
			diagramTransitions.addTransition(NAME, PROB, startNode, decisionNode);
		});
		
		assertEquals(START_NODE_WITH_NON_ACTIVITY_TARGET_ERROR_MESSAGE, exception.getMessage());
	}
	
	@Test
	public void testErrorWithNullValues() {
		
		ActivityDiagramRuleException exception = assertThrows(ActivityDiagramRuleException.class, () -> {
			diagramTransitions.addTransition(NAME, PROB, null, null);
		});
		
		assertEquals(NULL_VALUES_ERROR_MESSAGE, exception.getMessage());
	}
	
	@Test
	public void testActivityTransitions() {
		
		assertDoesNotThrow(() -> {
			diagramTransitions.addTransition(NAME, PROB, activityNode, new ActivityNode("activity2"));
		});
	}
	
	@Test
	public void testActivityTransitionsWithDecision() {
		
		assertDoesNotThrow(() -> {
			diagramTransitions.addTransition(NAME, PROB, activityNode, decisionNode);
		});
	}
	
	@Test
	public void testActivityTransitionsWithMerge() {
		
		assertDoesNotThrow(() -> {
			diagramTransitions.addTransition(NAME, PROB, activityNode, mergeNode);
		});
	}
	
	@Test
	public void testActivityTransitionsWithFinal() {
		
		assertDoesNotThrow(() -> {
			diagramTransitions.addTransition(NAME, PROB, activityNode, finalNode);
		});
	}
	
	@Test
	public void testErrorWithMultipleActivityTransitions() {
		
		ActivityDiagramRuleException exception = assertThrows(ActivityDiagramRuleException.class ,() -> {
			diagramTransitions.addTransition(NAME, PROB, activityNode, new ActivityNode("activity2"));
			diagramTransitions.addTransition(NAME, PROB, activityNode, new ActivityNode("activity3"));
		});
		
		assertEquals(MULTIPLE_ACTIVITY_TRANSITIONS_ERROR_MESSAGE, exception.getMessage());
	}
	
	@Test
	public void testErrorWithActivityToStartNode() {
		
		ActivityDiagramRuleException exception = assertThrows(ActivityDiagramRuleException.class ,() -> {
			diagramTransitions.addTransition(NAME, PROB, activityNode, startNode);
		});
		
		assertEquals(CONNECTING_WITH_START_NODE_ERROR_MESSAGE, exception.getMessage());
	}
	
	@Test
	public void testDecisionNodeTransitions() {
		
		assertDoesNotThrow(() -> {
			diagramTransitions.addTransition(NAME, PROB, decisionNode, activityNode);
			diagramTransitions.addTransition(NAME, PROB, decisionNode, new ActivityNode("activity2"));
			diagramTransitions.addTransition(NAME, PROB, decisionNode, mergeNode);
		});
	}
	
	@Test
	public void testFailWithMultiTargetOnDecisionNode() {
		
		ActivityDiagramRuleException exception = assertThrows(ActivityDiagramRuleException.class, () -> {
			diagramTransitions.addTransition(NAME, PROB, activityNode, decisionNode);
			diagramTransitions.addTransition(NAME, PROB, new ActivityNode("activity2"), decisionNode);
		});
		
		assertEquals(MULTIPLE_DECISION_NODE_TARGETS, exception.getMessage());
	}
	
	@Test
	public void testMergeNodeTransitions() {
		
		assertDoesNotThrow(() -> {
			diagramTransitions.addTransition(NAME, PROB, activityNode, mergeNode);
			diagramTransitions.addTransition(NAME, PROB, new ActivityNode("activity2"), mergeNode);
			diagramTransitions.addTransition(NAME, PROB, decisionNode, mergeNode);
		});
	}
	
	@Test
	public void testFailWithMultiTargetOnMergeNode() {
		
		ActivityDiagramRuleException exception = assertThrows(ActivityDiagramRuleException.class, () -> {
			diagramTransitions.addTransition(NAME, PROB, mergeNode, activityNode);
			diagramTransitions.addTransition(NAME, PROB, mergeNode, new ActivityNode("activity2"));
		});
		
		assertEquals(MULTIPLE_MERGE_NODE_TARGETS, exception.getMessage());
	}
	
	@Test
	public void testFinalNodeTransitions() {
		
		assertDoesNotThrow(() -> {
			diagramTransitions.addTransition(NAME, PROB, activityNode, finalNode);
		});
	}
	
	@Test
	public void testFinalNodeMultipleTransitions() {
		
		assertDoesNotThrow(() -> {
			diagramTransitions.addTransition(NAME, PROB, activityNode, finalNode);
			diagramTransitions.addTransition(NAME, PROB, mergeNode, finalNode);
		});
	}
	
	@Test
	public void testFailWithFinalNodesBeingSource() {
		
		ActivityDiagramRuleException exception = assertThrows(ActivityDiagramRuleException.class, () -> {
			diagramTransitions.addTransition(NAME, PROB, finalNode, activityNode);
		});
		
		assertEquals(FINAL_NODE_AS_SOURCE_ERROR_MESSAGE, exception.getMessage());
	}
}
