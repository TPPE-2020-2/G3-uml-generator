package uml.diagrams.activity.actiditydiagramelements;

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
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityDiagramElementsFinalNodeTest {
	private ActivityDiagramElements activityDiagramElements;

	private static FinalNode NODE_1;
	private static FinalNode NODE_2;
	private static FinalNode NODE_3;
	
	@BeforeEach
	public void setup() throws ActivityDiagramRuleException {
		activityDiagramElements = new ActivityDiagramElements();
	}
	
	@BeforeAll
	public static void initialSetup() throws ActivityDiagramRuleException {
		NODE_1 = new FinalNode("node1");
		NODE_2 = new FinalNode("node2");
		NODE_3 = new FinalNode("node3");
	}
	
	public static Collection<Object[]> finalNodes() throws ActivityDiagramRuleException {
		return Arrays.asList(new Object[][] {
			{ new ArrayList<FinalNode>(), 0 },
	        { Arrays.asList(NODE_1),1 },
	        { Arrays.asList(NODE_1, NODE_2), 2 },
	        { Arrays.asList(NODE_1, NODE_2, NODE_3), 3 },
		});
	}
	
	@ParameterizedTest
	@MethodSource("finalNodes")
	void testAddFinalNodes(List<FinalNode> nodes, int nodeLength) throws ActivityDiagramRuleException {
		for (FinalNode node : nodes) {
			activityDiagramElements.addFinalNode(node);			
		}
		
		assertEquals(nodeLength, activityDiagramElements.getFinalNodes().size());
	}
	
	public static Collection<Object[]> finalNodesFind() throws ActivityDiagramRuleException {
		return Arrays.asList(new Object[][] {
			{ new ArrayList<FinalNode>(), NODE_1, null },
			{ Arrays.asList(NODE_1), NODE_1, NODE_1},
	        { Arrays.asList(NODE_1, NODE_2), NODE_2, NODE_2 },
	        { Arrays.asList(NODE_1, NODE_2, NODE_3), NODE_3, NODE_3 },
		});
	}
	
	@ParameterizedTest
	@MethodSource("finalNodesFind")
	void testGetActivityNode(List<FinalNode> nodes, FinalNode nodeToFind, FinalNode expectedNode) 
		throws ActivityDiagramRuleException {
		for (FinalNode node : nodes) {
			activityDiagramElements.addFinalNode(node);			
		}
		
		assertEquals(expectedNode, activityDiagramElements.getFinalNode(nodeToFind.getName()));
	}
	
	public static Collection<Object[]> finalNodesRemoveFind() throws ActivityDiagramRuleException {
		return Arrays.asList(new Object[][] {
			{ new ArrayList<FinalNode>(), NODE_1, new ArrayList<FinalNode>() },
			{ Arrays.asList(NODE_1), NODE_1, new ArrayList<FinalNode>()},
	        { Arrays.asList(NODE_1, NODE_2), NODE_2, Arrays.asList(NODE_1) },
	        { Arrays.asList(NODE_1, NODE_2, NODE_3), NODE_3, Arrays.asList(NODE_1, NODE_2)},
	        { Arrays.asList(NODE_2, NODE_3), NODE_1, Arrays.asList(NODE_2, NODE_3)},
		});
	}
	
	@ParameterizedTest
	@MethodSource("finalNodesRemoveFind")
	void testRemoveActivityNode(List<FinalNode> nodes, FinalNode nodeToRemove, List<FinalNode> expectedList) 
		throws ActivityDiagramRuleException {
		for (FinalNode node : nodes) {
			activityDiagramElements.addFinalNode(node);			
		}
		
		activityDiagramElements.removeFinalNode(nodeToRemove.getName());
		
		assertArrayEquals(expectedList.toArray(), activityDiagramElements.getFinalNodes().toArray());
	}
}
