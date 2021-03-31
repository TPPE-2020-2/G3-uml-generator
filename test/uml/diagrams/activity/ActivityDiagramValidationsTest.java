package uml.diagrams.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityDiagramValidationsTest {
	private ActivityDiagram activityDiagram;
	private ActivityDiagramElements activityDiagramElements;
	private ActivityDiagramTransitions activityDiagramTransitions;
	
	private final static String NO_START_NODE_TRANSITION_ERROR_MESSAGE = "Transição para o StartNode não encontrada!";
	private final static String LESS_THAN_MININIUM_TRANSITIONS_ERROR_MESSAGE = "É necessário no mínimo duas transições";
	
	private final static String DEFAULT_NAME = "default";
	private static StartNode startNode;
	private static ActivityNode activityNode;
	private static DecisionNode decisionNode;
	private static MergeNode mergeNode;
	private static FinalNode finalNode;
	
	@BeforeEach
	public void setup() throws ActivityDiagramRuleException {
		activityDiagram = new ActivityDiagram(DEFAULT_NAME);
		activityDiagramElements = new ActivityDiagramElements();
		activityDiagramTransitions = new ActivityDiagramTransitions();
	}
	
	@BeforeAll
	public static void initialSetup() throws ActivityDiagramRuleException {
		startNode = new StartNode("start");
		activityNode = new ActivityNode("activity");
		decisionNode = new DecisionNode("decision");
		mergeNode = new MergeNode("merge");
		finalNode = new FinalNode("final");
	}
	
	@Test
	public void testValidationWhenOnlyOneTransition() throws ActivityDiagramRuleException {
		ActivityNode activityNode2 = new ActivityNode("activity2");

		activityDiagram.addNodeElement(startNode);
		activityDiagram.addNodeElement(activityNode);
		activityDiagram.addNodeElement(finalNode);

		activityDiagram.addTransition("transition1", 0.5f, activityNode, finalNode);

		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> activityDiagram.validateActivityDiagram());
		
		assertEquals(LESS_THAN_MININIUM_TRANSITIONS_ERROR_MESSAGE, exception.getMessage());
	}
	
	@Test
	public void testValidationWhenNoStartNodeTransition() throws ActivityDiagramRuleException {
		ActivityNode activityNode2 = new ActivityNode("activity2");

		activityDiagram.addNodeElement(startNode);
		activityDiagram.addNodeElement(activityNode);
		activityDiagram.addNodeElement(activityNode);
		activityDiagram.addNodeElement(finalNode);

		activityDiagram.addTransition("transition1", 0.5f, activityNode, finalNode);
		activityDiagram.addTransition("transition1", 0.5f, activityNode2, finalNode);

		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> activityDiagram.validateActivityDiagram());
		
		assertEquals(NO_START_NODE_TRANSITION_ERROR_MESSAGE, exception.getMessage());
	}
}
