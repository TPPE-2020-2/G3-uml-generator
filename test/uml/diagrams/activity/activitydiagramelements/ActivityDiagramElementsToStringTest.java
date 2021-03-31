package uml.diagrams.activity.activitydiagramelements;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uml.diagrams.activity.ActivityDiagramElements;
import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityDiagramElementsToStringTest {
	private ActivityDiagramElements activityDiagramElement;
	
	private static StartNode startNode;
	private static ActivityNode activityNode;
	private static DecisionNode decisionNode;
	private static MergeNode mergeNode;
	private static FinalNode finalNode;
	
	@BeforeEach
	public void setup() {
		activityDiagramElement = new ActivityDiagramElements();
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
	public void testBasicDiagramElements() throws ActivityDiagramRuleException {
		String expectedString = "<ActivityDiagramElements>" + 
				startNode.toString() +
				activityNode.toString() + 
				finalNode.toString() +
				"</ActivityDiagramElements>";
		
		activityDiagramElement.setStartNode(startNode);
		activityDiagramElement.addActivityNode(activityNode);
		activityDiagramElement.addFinalNode(finalNode);
		
		assertDoesNotThrow(() -> activityDiagramElement.validateDiagramElements());
		assertEquals(expectedString, activityDiagramElement.toString());
	}
	
	@Test
	public void testBasicDiagramElementsWithTwoActivityNodes() throws ActivityDiagramRuleException {
		ActivityNode activityNode2 = new ActivityNode("activity2");
		
		String expectedString = "<ActivityDiagramElements>" + 
				startNode.toString() +
				activityNode.toString() +
				activityNode2.toString() +
				finalNode.toString() +
				"</ActivityDiagramElements>";
		
		activityDiagramElement.setStartNode(startNode);
		activityDiagramElement.addActivityNode(activityNode);
		activityDiagramElement.addActivityNode(activityNode2);
		activityDiagramElement.addFinalNode(finalNode);
		
		assertDoesNotThrow(() -> activityDiagramElement.validateDiagramElements());
		assertEquals(expectedString, activityDiagramElement.toString());
	}
	
	@Test
	public void testDiagramElementsWithDecisionAndMerge() throws ActivityDiagramRuleException {
		String expectedString = "<ActivityDiagramElements>" + 
				startNode.toString() +
				activityNode.toString() + 
				decisionNode.toString() +
				mergeNode.toString() +
				finalNode.toString() +
				"</ActivityDiagramElements>";
		
		activityDiagramElement.setStartNode(startNode);
		activityDiagramElement.addActivityNode(activityNode);
		activityDiagramElement.addDecisionNode(decisionNode);
		activityDiagramElement.addMergeNode(mergeNode);
		activityDiagramElement.addFinalNode(finalNode);
		
		assertDoesNotThrow(() -> activityDiagramElement.validateDiagramElements());
		assertEquals(expectedString, activityDiagramElement.toString());
	}
	
	@Test
	public void testDiagramElementsWithDecisionAndMergeAndMultipleActivities() throws ActivityDiagramRuleException {
		ActivityNode activityNode2 = new ActivityNode("activity2");
		
		String expectedString = "<ActivityDiagramElements>" + 
				startNode.toString() +
				activityNode.toString() + 
				activityNode2.toString() +
				decisionNode.toString() +
				mergeNode.toString() +
				finalNode.toString() +
				"</ActivityDiagramElements>";
		
		activityDiagramElement.setStartNode(startNode);
		activityDiagramElement.addActivityNode(activityNode);
		activityDiagramElement.addActivityNode(activityNode2);
		activityDiagramElement.addDecisionNode(decisionNode);
		activityDiagramElement.addMergeNode(mergeNode);
		activityDiagramElement.addFinalNode(finalNode);
		
		assertDoesNotThrow(() -> activityDiagramElement.validateDiagramElements());
		assertEquals(expectedString, activityDiagramElement.toString());
	}
}
