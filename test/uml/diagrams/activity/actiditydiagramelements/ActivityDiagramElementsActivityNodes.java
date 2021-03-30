package uml.diagrams.activity.actiditydiagramelements;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.ActivityDiagramElements;
import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class ActivityDiagramElementsActivityNodes {

private ActivityDiagramElements activityDiagramElements;
	
	@BeforeEach
	public void setup() {
		activityDiagramElements = new ActivityDiagramElements();
	}
	
	public static Collection<Object[]> activityNodes() throws ActivityDiagramRuleException {
		return Arrays.asList(new Object[][] {
	        { Arrays.asList(new ActivityNode("node1")),1 },
	        { Arrays.asList(new ActivityNode("node1"), new ActivityNode("node2")), 2 },
	        { Arrays.asList(new ActivityNode("node1"), new ActivityNode("node2"), new ActivityNode("node3")), 3 },
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

}
