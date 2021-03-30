package uml.diagrams.activity.entities.decisionnode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class DecisionNodeToStringTest {

	private DecisionNode decisionNode;

 	public static Collection decisionNodeNames() {
		return Arrays.asList(new Object[][] {
	        { "name", "<DecisionNode name=\"name\" />" },
	        { "decisionNode", "<DecisionNode name=\"decisionNode\" />" },
	        { "abcd123", "<DecisionNode name=\"abcd123\" />" },
	        { "22", "<DecisionNode name=\"22\" />" }
		});
	}

	@ParameterizedTest
	@MethodSource("decisionNodeNames")
	void testGetStartNodeName(String name, String expectedValue) throws ActivityDiagramRuleException {
		decisionNode = new DecisionNode(name);

		assertEquals(decisionNode.toString(), expectedValue);
	}


}
