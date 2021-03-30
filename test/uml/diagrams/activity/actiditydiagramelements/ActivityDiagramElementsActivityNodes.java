package uml.diagrams.activity.actiditydiagramelements;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.ActivityDiagramElements;
import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class ActivityDiagramElementsActivityNodes {

private ActivityDiagramElements activityDiagramElements;
	private static ActivityNode NODE_1;
	private static ActivityNode NODE_2;
	private static ActivityNode NODE_3;
	
	@BeforeEach
	public void setup() throws ActivityDiagramRuleException {
		activityDiagramElements = new ActivityDiagramElements();
	}
	
	@BeforeAll
	public static void initialSetup() throws ActivityDiagramRuleException {
		NODE_1 = new ActivityNode("node1");
		NODE_2 = new ActivityNode("node2");
		NODE_3 = new ActivityNode("node3");
	}
	
	public static Collection<Object[]> activityNodes() throws ActivityDiagramRuleException {
		return Arrays.asList(new Object[][] {
			{ new ArrayList<ActivityNode>(), 0 },
	        { Arrays.asList(NODE_1),1 },
	        { Arrays.asList(NODE_1, NODE_2), 2 },
	        { Arrays.asList(NODE_1, NODE_2, NODE_3), 3 },
		});
	}
	
	@ParameterizedTest
	@MethodSource("activityNodes")
	void testAddActivityNodes(List<ActivityNode> nodes, int nodeLength) throws ActivityDiagramRuleException {
		for (ActivityNode node : nodes) {
			activityDiagramElements.addActivityNode(node);			
		}
		
		assertEquals(nodeLength, activityDiagramElements.getActivityNodes().size());
	}
	
	public static Collection<Object[]> activityNodesFind() throws ActivityDiagramRuleException {
		System.out.println(NODE_1.toString());
		return Arrays.asList(new Object[][] {
			{ new ArrayList<ActivityNode>(), NODE_1, null },
			{ Arrays.asList(NODE_1), NODE_1, NODE_1},
	        { Arrays.asList(NODE_1, NODE_2), NODE_2, NODE_2 },
	        { Arrays.asList(NODE_1, NODE_2, NODE_3), NODE_3, NODE_3 },
		});
	}
	
	@ParameterizedTest
	@MethodSource("activityNodesFind")
	void testGetActivityNode(List<ActivityNode> nodes, ActivityNode nodeToFind, ActivityNode expectedNode) 
		throws ActivityDiagramRuleException {
		for (ActivityNode node : nodes) {
			activityDiagramElements.addActivityNode(node);			
		}
		
		assertEquals(expectedNode, activityDiagramElements.getActivityNode(nodeToFind.getName()));
	}

}
