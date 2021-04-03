package uml.diagrams.activity.entities.startnode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class StartNodeToStringTest {

	private StartNode initialNode;
	
 	public static Collection<Object[]> startNodeNames() {
		return Arrays.asList(new Object[][] {
	        { "name", "<StartNode name=\"name\" />" },
	        { "initialNode", "<StartNode name=\"initialNode\" />" },
	        { "abcd123", "<StartNode name=\"abcd123\" />" },
	        { "22", "<StartNode name=\"22\" />" }
		});
	}
	
	@ParameterizedTest
	@MethodSource("startNodeNames")
	void testGetStartNodeName(String name, String expectedValue) throws ActivityDiagramRuleException {
		initialNode = new StartNode(name);

		assertEquals(initialNode.toString(), expectedValue);
	}

}
