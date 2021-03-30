package uml.diagrams.activity.entities.decisionnode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class DecisionNodeSetNameTest {

	private DecisionNode decisionNode;
	private final String INITIAL_NAME = "NAME";
	
	@BeforeEach
	public void setup() throws ActivityDiagramRuleException {
		decisionNode = new DecisionNode(INITIAL_NAME);
	}

	public static Collection decisionNodeNames() {
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
		decisionNode.setName(name);

		assertEquals(name, decisionNode.getName());
	}

}
