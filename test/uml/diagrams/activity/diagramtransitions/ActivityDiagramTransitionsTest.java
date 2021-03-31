package uml.diagrams.activity.diagramtransitions;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uml.diagrams.activity.ActivityDiagramElements;
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
	private final static String START_NODE_WITH_NON_ACTIVITY_TARGET_ERROR_MESSAGE = "Um diagrama de atividades deve se conectar somente à um activity!";

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
}
