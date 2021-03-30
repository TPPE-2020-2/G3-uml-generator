package uml.diagrams.activity.activitydiagramelements;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.ActivityDiagramElements;
import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class ActivityDiagramElementsStartNodeTest {

	private ActivityDiagramElements activityDiagramElements;
	
	@BeforeEach
	public void setup() {
		activityDiagramElements = new ActivityDiagramElements();
	}
	
	public static Collection<Object[]> startNodes() throws ActivityDiagramRuleException {
		return Arrays.asList(new Object[][] {
	        { new StartNode("defaultName"), "defaultName" },
	        { new StartNode("transition"), "transition" },
	        { new StartNode("triangulacao"), "triangulacao"  } 
		});
	}
	
	@ParameterizedTest
	@MethodSource("startNodes")
	void testStartNodes(StartNode startNode, String nodeName) throws ActivityDiagramRuleException {
		activityDiagramElements.setStartNode(startNode);
		
		assertEquals(nodeName, activityDiagramElements.getStartNode().getName());
	}

}
