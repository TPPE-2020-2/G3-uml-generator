package uml.diagrams.activity.entities.decisionnode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class DecisionNodeInvalidNamesValidation {

	private final String NAME = "finalNode";
	private final String ERROR_MESSAGE = "O nome não pode ser vazio";

	private DecisionNode decisionNode;
	
	public static Collection emptyValues() {
		return Arrays.asList(new Object[][] {
	        { "" },
	        { null }
		});
	}

	@ParameterizedTest
	@MethodSource("emptyValues")
	void testErrorWhenNullName(String value) {
		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> new DecisionNode(value));
		
		assertEquals(exception.getMessage(), ERROR_MESSAGE);
	}

	@ParameterizedTest
	@MethodSource("emptyValues")
	void testErrorWhenNullNameOnSetter(String value) throws ActivityDiagramRuleException {
		decisionNode = new DecisionNode(NAME);
		
		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> decisionNode.setName(value));
		
		assertEquals(exception.getMessage(), ERROR_MESSAGE);
	}

}
