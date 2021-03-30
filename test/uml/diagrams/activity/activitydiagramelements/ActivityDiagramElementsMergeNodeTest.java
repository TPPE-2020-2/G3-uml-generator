package uml.diagrams.activity.activitydiagramelements;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.ActivityDiagramElements;
import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityDiagramElementsMergeNodeTest {
	private ActivityDiagramElements activityDiagramElements;

	private static MergeNode NODE_1;
	private static MergeNode NODE_2;
	private static MergeNode NODE_3;
	
	@BeforeEach
	public void setup() throws ActivityDiagramRuleException {
		activityDiagramElements = new ActivityDiagramElements();
	}
	
	@BeforeAll
	public static void initialSetup() throws ActivityDiagramRuleException {
		NODE_1 = new MergeNode("node1");
		NODE_2 = new MergeNode("node2");
		NODE_3 = new MergeNode("node3");
	}
	
	public static Collection<Object[]> mergeNodes() throws ActivityDiagramRuleException {
		return Arrays.asList(new Object[][] {
			{ new ArrayList<MergeNode>(), 0 },
	        { Arrays.asList(NODE_1),1 },
	        { Arrays.asList(NODE_1, NODE_2), 2 },
	        { Arrays.asList(NODE_1, NODE_2, NODE_3), 3 },
		});
	}
	
	@ParameterizedTest
	@MethodSource("mergeNodes")
	void testAddFinalNodes(List<MergeNode> nodes, int nodeLength) throws ActivityDiagramRuleException {
		for (MergeNode node : nodes) {
			activityDiagramElements.addMergeNode(node);			
		}
		
		assertEquals(nodeLength, activityDiagramElements.getMergeNodes().size());
	}
	
	public static Collection<Object[]> mergeNodesFind() throws ActivityDiagramRuleException {
		return Arrays.asList(new Object[][] {
			{ new ArrayList<MergeNode>(), NODE_1, null },
			{ Arrays.asList(NODE_1), NODE_1, NODE_1},
	        { Arrays.asList(NODE_1, NODE_2), NODE_2, NODE_2 },
	        { Arrays.asList(NODE_1, NODE_2, NODE_3), NODE_3, NODE_3 },
		});
	}
	
	@ParameterizedTest
	@MethodSource("mergeNodesFind")
	void testGetActivityNode(List<MergeNode> nodes, MergeNode nodeToFind, MergeNode expectedNode) 
		throws ActivityDiagramRuleException {
		for (MergeNode node : nodes) {
			activityDiagramElements.addMergeNode(node);			
		}
		
		assertEquals(expectedNode, activityDiagramElements.getMergeNode(nodeToFind.getName()));
	}
	
	public static Collection<Object[]> mergeNodesRemoveFind() throws ActivityDiagramRuleException {
		return Arrays.asList(new Object[][] {
			{ new ArrayList<MergeNode>(), NODE_1, new ArrayList<MergeNode>() },
			{ Arrays.asList(NODE_1), NODE_1, new ArrayList<MergeNode>()},
	        { Arrays.asList(NODE_1, NODE_2), NODE_2, Arrays.asList(NODE_1) },
	        { Arrays.asList(NODE_1, NODE_2, NODE_3), NODE_3, Arrays.asList(NODE_1, NODE_2)},
	        { Arrays.asList(NODE_2, NODE_3), NODE_1, Arrays.asList(NODE_2, NODE_3)},
		});
	}
	
	@ParameterizedTest
	@MethodSource("mergeNodesRemoveFind")
	void testRemoveFinalNode(List<MergeNode> nodes, MergeNode nodeToRemove, List<MergeNode> expectedList) 
		throws ActivityDiagramRuleException {
		for (MergeNode node : nodes) {
			activityDiagramElements.addMergeNode(node);			
		}
		
		activityDiagramElements.removeMergeNode(nodeToRemove.getName());
		
		assertArrayEquals(expectedList.toArray(), activityDiagramElements.getMergeNodes().toArray());
	}
}
