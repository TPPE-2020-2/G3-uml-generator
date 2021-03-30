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
import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityDiagramElementsDecisionNodeTest {
	private ActivityDiagramElements activityDiagramElements;

	private static DecisionNode NODE_1;
	private static DecisionNode NODE_2;
	private static DecisionNode NODE_3;
	
	@BeforeEach
	public void setup() throws ActivityDiagramRuleException {
		activityDiagramElements = new ActivityDiagramElements();
	}
	
	@BeforeAll
	public static void initialSetup() throws ActivityDiagramRuleException {
		NODE_1 = new DecisionNode("node1");
		NODE_2 = new DecisionNode("node2");
		NODE_3 = new DecisionNode("node3");
	}
	
	public static Collection<Object[]> decisionNodes() throws ActivityDiagramRuleException {
		return Arrays.asList(new Object[][] {
			{ new ArrayList<DecisionNode>(), 0 },
	        { Arrays.asList(NODE_1),1 },
	        { Arrays.asList(NODE_1, NODE_2), 2 },
	        { Arrays.asList(NODE_1, NODE_2, NODE_3), 3 },
		});
	}
	
	@ParameterizedTest
	@MethodSource("decisionNodes")
	void testAddFinalNodes(List<DecisionNode> nodes, int nodeLength) throws ActivityDiagramRuleException {
		for (DecisionNode node : nodes) {
			activityDiagramElements.addDecisionNode(node);			
		}
		
		assertEquals(nodeLength, activityDiagramElements.getDecisionNodes().size());
	}
	
	public static Collection<Object[]> finalNodesFind() throws ActivityDiagramRuleException {
		return Arrays.asList(new Object[][] {
			{ new ArrayList<DecisionNode>(), NODE_1, null },
			{ Arrays.asList(NODE_1), NODE_1, NODE_1},
	        { Arrays.asList(NODE_1, NODE_2), NODE_2, NODE_2 },
	        { Arrays.asList(NODE_1, NODE_2, NODE_3), NODE_3, NODE_3 },
		});
	}
	
	@ParameterizedTest
	@MethodSource("finalNodesFind")
	void testGetActivityNode(List<DecisionNode> nodes, DecisionNode nodeToFind, DecisionNode expectedNode) 
		throws ActivityDiagramRuleException {
		for (DecisionNode node : nodes) {
			activityDiagramElements.addDecisionNode(node);			
		}
		
		assertEquals(expectedNode, activityDiagramElements.getDecisionNode(nodeToFind.getName()));
	}
	
	public static Collection<Object[]> decisionNodesRemoveFind() throws ActivityDiagramRuleException {
		return Arrays.asList(new Object[][] {
			{ new ArrayList<DecisionNode>(), NODE_1, new ArrayList<DecisionNode>() },
			{ Arrays.asList(NODE_1), NODE_1, new ArrayList<DecisionNode>()},
	        { Arrays.asList(NODE_1, NODE_2), NODE_2, Arrays.asList(NODE_1) },
	        { Arrays.asList(NODE_1, NODE_2, NODE_3), NODE_3, Arrays.asList(NODE_1, NODE_2)},
	        { Arrays.asList(NODE_2, NODE_3), NODE_1, Arrays.asList(NODE_2, NODE_3)},
		});
	}
	
	@ParameterizedTest
	@MethodSource("decisionNodesRemoveFind")
	void testRemoveFinalNode(List<DecisionNode> nodes, DecisionNode nodeToRemove, List<DecisionNode> expectedList) 
		throws ActivityDiagramRuleException {
		for (DecisionNode node : nodes) {
			activityDiagramElements.addDecisionNode(node);			
		}
		
		activityDiagramElements.removeDecisionNode(nodeToRemove.getName());
		
		assertArrayEquals(expectedList.toArray(), activityDiagramElements.getDecisionNodes().toArray());
	}
}
