package uml.diagrams.activity.entities.decisionnode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class DecisionNodeConstructorNameTest {
	
	private DecisionNode decisionNode;

	public static Collection<Object[]> decisionNodeNames() {
		return Arrays.asList(new Object[][] {
	        { "name" },
	        { "decisionNode" },
	        { "abcd123" },
	        { "22" }
		});
	}
	
	@ParameterizedTest
	@MethodSource("decisionNodeNames")
	void testGetFinalNodeName(String name) throws ActivityDiagramRuleException {
		decisionNode = new DecisionNode(name);

		assertEquals(name, decisionNode.getName());
	}

}
