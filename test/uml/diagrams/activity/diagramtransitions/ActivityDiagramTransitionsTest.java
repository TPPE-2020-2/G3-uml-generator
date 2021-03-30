package uml.diagrams.activity.diagramtransitions;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
}
