package uml.diagrams.activity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.BaseNode;
import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.entities.Transition;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityDiagramToStringTest {
	private ActivityDiagram activityDiagram;
	
	private final static String DEFAULT_NAME = "default";
	private static StartNode startNode;
	private static ActivityNode activityNode;
	private static DecisionNode decisionNode;
	private static MergeNode mergeNode;
	private static FinalNode finalNode;
	
	@BeforeEach
	public void setup() throws ActivityDiagramRuleException {
		activityDiagram = new ActivityDiagram(DEFAULT_NAME);
	}
	
	@BeforeAll
	public static void initialSetup() throws ActivityDiagramRuleException {
		startNode = new StartNode("start");
		activityNode = new ActivityNode("activity");
		decisionNode = new DecisionNode("decision");
		mergeNode = new MergeNode("merge");
		finalNode = new FinalNode("final");
	}
	
	private <T extends BaseNode> Transition createTransition(String name, float prob, T source, T target ) 
			throws ActivityDiagramRuleException {
		Transition tempTransition = new Transition();
		
		tempTransition.setName(name);
		tempTransition.setProb(prob);
		tempTransition.setSource(source);
		tempTransition.setTarget(target);
		
		return tempTransition;
	}
	
	@Test
	public void testSimpleToString() throws ActivityDiagramRuleException {
		activityDiagram
			.addNodeElement(startNode)
			.addNodeElement(activityNode)
			.addNodeElement(finalNode);
		
		Transition transition1 = createTransition("transition1", 0.5f, startNode, activityNode);
		Transition transition2 = createTransition("transition2", 0.5f, activityNode, finalNode);

		activityDiagram
			.addTransition(transition1)
			.addTransition(transition2);

		assertDoesNotThrow(() -> activityDiagram.validateActivityDiagram());
		
		String expectedResult = "<ActivityDiagram name=\"default\">" +
				"<ActivityDiagramElements>" +
					startNode.toString() +
					activityNode.toString() +
					finalNode.toString() +
				"</ActivityDiagramElements>" +
				"<ActivityDiagramTransitions>" +
					transition1.toString() +
					transition2.toString() +
				"</ActivityDiagramTransitions>" +
				"</ActivityDiagram>";
		
		assertEquals(expectedResult, activityDiagram.toString());
	}
	
	@Test
	public void testSimpleToString2() throws ActivityDiagramRuleException {
		ActivityNode activityNode2 = new ActivityNode("activity2");
		
		activityDiagram
			.addNodeElement(startNode)
			.addNodeElement(activityNode)
			.addNodeElement(activityNode2)
			.addNodeElement(finalNode);
		
		Transition transition1 = createTransition("transition1", 0.5f, startNode, activityNode);
		Transition transition2 = createTransition("transition2", 0.5f, activityNode, activityNode2);
		Transition transition3 = createTransition("transition2", 0.5f, activityNode2, finalNode);

		activityDiagram
			.addTransition(transition1)
			.addTransition(transition2)
			.addTransition(transition3);

		assertDoesNotThrow(() -> activityDiagram.validateActivityDiagram());
		
		String expectedResult = "<ActivityDiagram name=\"default\">" +
				"<ActivityDiagramElements>" +
					startNode.toString() +
					activityNode.toString() +
					activityNode2.toString() +
					finalNode.toString() +
				"</ActivityDiagramElements>" +
				"<ActivityDiagramTransitions>" +
					transition1.toString() +
					transition2.toString() +
					transition3.toString() +
				"</ActivityDiagramTransitions>" +
				"</ActivityDiagram>";
		
		assertEquals(expectedResult, activityDiagram.toString());
	}
	
	@Test
	public void testToStringWithDecisionAndMergeNodes() throws ActivityDiagramRuleException {
		ActivityNode activityNode2 = new ActivityNode("activity2");
		ActivityNode activityNode3 = new ActivityNode("activity3");
		ActivityNode activityNode4 = new ActivityNode("activity4");
		
		activityDiagram
			.addNodeElement(startNode)
			.addNodeElement(activityNode)
			.addNodeElement(activityNode2)
			.addNodeElement(activityNode3)
			.addNodeElement(activityNode4)
			.addNodeElement(mergeNode)
			.addNodeElement(decisionNode)		
			.addNodeElement(finalNode);
		
		Transition transition1 = createTransition("transition1", 0.5f, startNode, activityNode);
		Transition transition2 = createTransition("transition2", 0.5f, activityNode, activityNode2);
		Transition transition3 = createTransition("transition2", 0.5f, activityNode2, activityNode3);
		Transition transition4 = createTransition("transition2", 0.5f, activityNode3, decisionNode);
		Transition transition5 = createTransition("transition2", 0.5f, decisionNode, activityNode4);
		Transition transition6 = createTransition("transition2", 0.5f, decisionNode, mergeNode);
		Transition transition7 = createTransition("transition2", 0.5f, activityNode4, mergeNode);
		Transition transition8 = createTransition("transition2", 0.5f, mergeNode, finalNode);

		activityDiagram
			.addTransition(transition1)
			.addTransition(transition2)
			.addTransition(transition3)
			.addTransition(transition4)
			.addTransition(transition5)
			.addTransition(transition6)
			.addTransition(transition7)
			.addTransition(transition8);
		
		assertDoesNotThrow(() -> activityDiagram.validateActivityDiagram());
		
		String expectedResult = 
			"<ActivityDiagram name=\"default\">" +
				"<ActivityDiagramElements>" +
					startNode.toString() +
					activityNode.toString() +
					activityNode2.toString() +
					activityNode3.toString() +
					activityNode4.toString() +
					decisionNode.toString() +
					mergeNode.toString() +
					finalNode.toString() +
				"</ActivityDiagramElements>" +
				"<ActivityDiagramTransitions>" +
					transition1.toString() +
					transition2.toString() +
					transition3.toString() +
					transition4.toString() +
					transition5.toString() +
					transition6.toString() +
					transition7.toString() +
					transition8.toString() +
				"</ActivityDiagramTransitions>" +
			"</ActivityDiagram>";
		
		assertEquals(expectedResult, activityDiagram.toString());
	}
}
