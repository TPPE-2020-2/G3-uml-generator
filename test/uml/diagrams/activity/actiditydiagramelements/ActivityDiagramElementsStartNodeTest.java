package uml.diagrams.activity.actiditydiagramelements;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.StartNode;

class ActivityDiagramElementsStartNodeTest {

	private ActivityDiagramElements activityDiagramElements;
	
	@BeforeEach
	public void setup() {
		activityDiagramElements = new ActivityDiagramElements();
	}
	
	public static Collection<Object[]> startNodes() {
		return Arrays.asList(new Object[][] {
	        { new StartNode("defaultName"), "defaultName" },
		});
	}
	
	@ParameterizedTest
	@MethodSource("startNodes")
	void testStartNodes(StartNode startNode, String nodeName) {
		activityDiagramElements.setStartNode(startNode);
		
		assertEquals(nodeName, activityDiagramElements.getStartNode().getName());
	}

}
