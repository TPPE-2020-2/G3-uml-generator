package uml.diagrams.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityDiagramElementsTest {
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
	
	@Test
	public void testAddStartNode() {
		activityDiagram.addNodeElement(startNode);

		assertEquals(startNode, activityDiagram.getActivityDiagramElements().getStartNode());
	}
	
	@Test
	public void testAddActivityNode() {
		activityDiagram.addNodeElement(activityNode);
		
		assertEquals(activityNode, activityDiagram.getActivityDiagramElements().getActivityNode(activityNode.getName()));
	}
	
	@Test
	public void testAddMergeNode() {
		activityDiagram.addNodeElement(mergeNode);
		
		assertEquals(mergeNode, activityDiagram.getActivityDiagramElements().getMergeNode(mergeNode.getName()));
	}
	
	@Test
	public void testAddDecisionNode() {
		activityDiagram.addNodeElement(decisionNode);
		
		assertEquals(decisionNode, activityDiagram.getActivityDiagramElements().getDecisionNode(decisionNode.getName()));
	}
	
	@Test
	public void testAddFinalNode() {
		activityDiagram.addNodeElement(finalNode);
		
		assertEquals(finalNode, activityDiagram.getActivityDiagramElements().getFinalNode(finalNode.getName()));
	}
}
