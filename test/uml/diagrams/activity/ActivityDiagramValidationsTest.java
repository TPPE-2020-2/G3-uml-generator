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
	private final static String NODES_WITHOUT_TRANSITION = "<1> <2> não está ligado à nenhum elemento!";
	private final static String NODES_WITHOUT_PARENT_TRANSITION = "<1> <2> não é ligado por nenhum elemento pai!";
	private final static String FINAL_NODE_WITHOUT_PARENT_TRANSITION = "O final node <> não possui uma transição para ele!";
	
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
		activityDiagram.addNodeElement(activityNode2);
		activityDiagram.addNodeElement(finalNode);

		activityDiagram.addTransition("transition1", 0.5f, activityNode, finalNode);
		activityDiagram.addTransition("transition1", 0.5f, activityNode2, finalNode);

		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> activityDiagram.validateActivityDiagram());
		
		assertEquals(NO_START_NODE_TRANSITION_ERROR_MESSAGE, exception.getMessage());
	}
	
	@Test
	public void testValidationWhenActivitysWithoutTransition() throws ActivityDiagramRuleException {
		ActivityNode activityNode2 = new ActivityNode("activity2");

		activityDiagram.addNodeElement(startNode);
		activityDiagram.addNodeElement(activityNode);
		activityDiagram.addNodeElement(activityNode2);
		activityDiagram.addNodeElement(finalNode);

		activityDiagram.addTransition("transition1", 0.5f, startNode, activityNode);
		activityDiagram.addTransition("transition1", 0.5f, activityNode2, finalNode);

		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> activityDiagram.validateActivityDiagram());
		
		assertEquals(NODES_WITHOUT_TRANSITION
				.replace("<1>", "ActivityNode")
				.replace("<2>", activityNode.getName()),
				exception.getMessage());
	}
	
	@Test
	public void testValidationWhenActivitysWithoutTargetTransition() throws ActivityDiagramRuleException {
		ActivityNode activityNode2 = new ActivityNode("activity2");

		activityDiagram.addNodeElement(startNode);
		activityDiagram.addNodeElement(activityNode);
		activityDiagram.addNodeElement(activityNode2);
		activityDiagram.addNodeElement(finalNode);

		activityDiagram.addTransition("transition1", 0.5f, startNode, activityNode);
		activityDiagram.addTransition("transition1", 0.5f, activityNode, finalNode);
		activityDiagram.addTransition("transition1", 0.5f, activityNode2, finalNode);

		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> activityDiagram.validateActivityDiagram());
		
		assertEquals(NODES_WITHOUT_PARENT_TRANSITION
				.replace("<1>", "ActivityNode")
				.replace("<2>", activityNode2.getName()),
				exception.getMessage());
	}
	
	@Test
	public void testValidationWhenNoTransitionsToFinalNode() throws ActivityDiagramRuleException {
		ActivityNode activityNode2 = new ActivityNode("activity2");

		activityDiagram.addNodeElement(startNode);
		activityDiagram.addNodeElement(activityNode);
		activityDiagram.addNodeElement(activityNode2);
		activityDiagram.addNodeElement(finalNode);

		activityDiagram.addTransition("transition1", 0.5f, startNode, activityNode);
		activityDiagram.addTransition("transition1", 0.5f, activityNode, activityNode2);
		activityDiagram.addTransition("transition1", 0.5f, activityNode2, activityNode);

		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> activityDiagram.validateActivityDiagram());
		
		assertEquals(FINAL_NODE_WITHOUT_PARENT_TRANSITION.replace("<>", finalNode.getName()), exception.getMessage());
	}
	
	@Test
	public void testValidationWhenDecisionNodeWithoutTransition() throws ActivityDiagramRuleException {

		activityDiagram.addNodeElement(startNode);
		activityDiagram.addNodeElement(activityNode);
		activityDiagram.addNodeElement(decisionNode);
		activityDiagram.addNodeElement(finalNode);

		activityDiagram.addTransition("transition1", 0.5f, startNode, activityNode);
		activityDiagram.addTransition("transition1", 0.5f, activityNode, decisionNode);

		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> activityDiagram.validateActivityDiagram());
		
		assertEquals(NODES_WITHOUT_TRANSITION
				.replace("<1>", "DecisionNode")
				.replace("<2>", decisionNode.getName()),
				exception.getMessage());
	}
	
	@Test
	public void testValidationWhenDecisionNodeWithoutTargetTransition() throws ActivityDiagramRuleException {

		activityDiagram.addNodeElement(startNode);
		activityDiagram.addNodeElement(activityNode);
		activityDiagram.addNodeElement(decisionNode);
		activityDiagram.addNodeElement(finalNode);

		activityDiagram.addTransition("transition1", 0.5f, startNode, activityNode);
		activityDiagram.addTransition("transition1", 0.5f, activityNode, finalNode);
		activityDiagram.addTransition("transition1", 0.5f, decisionNode, finalNode);

		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> activityDiagram.validateActivityDiagram());
		
		assertEquals(NODES_WITHOUT_PARENT_TRANSITION
				.replace("<1>", "DecisionNode")
				.replace("<2>", decisionNode.getName()),
				exception.getMessage());
	}
	
	@Test
	public void testValidationWhenMergeNodeWithoutTransition() throws ActivityDiagramRuleException {

		activityDiagram.addNodeElement(startNode);
		activityDiagram.addNodeElement(activityNode);
		activityDiagram.addNodeElement(mergeNode);
		activityDiagram.addNodeElement(finalNode);

		activityDiagram.addTransition("transition1", 0.5f, startNode, activityNode);
		activityDiagram.addTransition("transition1", 0.5f, activityNode, mergeNode);

		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> activityDiagram.validateActivityDiagram());
		
		assertEquals(NODES_WITHOUT_TRANSITION
				.replace("<1>", "MergeNode")
				.replace("<2>", mergeNode.getName()),
				exception.getMessage());
	}
	
	@Test
	public void testValidationWhenMergeNodeWithoutTargetTransition() throws ActivityDiagramRuleException {

		activityDiagram.addNodeElement(startNode);
		activityDiagram.addNodeElement(activityNode);
		activityDiagram.addNodeElement(mergeNode);
		activityDiagram.addNodeElement(finalNode);

		activityDiagram.addTransition("transition1", 0.5f, startNode, activityNode);
		activityDiagram.addTransition("transition1", 0.5f, activityNode, finalNode);
		activityDiagram.addTransition("transition1", 0.5f, mergeNode, finalNode);

		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> activityDiagram.validateActivityDiagram());
		
		assertEquals(NODES_WITHOUT_PARENT_TRANSITION
				.replace("<1>", "MergeNode")
				.replace("<2>", mergeNode.getName()),
				exception.getMessage());
	}
}
