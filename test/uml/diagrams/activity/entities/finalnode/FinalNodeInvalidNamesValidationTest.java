package uml.diagrams.activity.entities.finalnode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class FinalNodeInvalidNamesValidationTest {
	
	private final String NAME = "finalNode";
	private final String ERROR_MESSAGE = "O nome não pode ser vazio";

	private FinalNode finalNode;
	
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
				() -> new FinalNode(value));
		
		assertEquals(exception.getMessage(), ERROR_MESSAGE);
	}

	@ParameterizedTest
	@MethodSource("emptyValues")
	void testErrorWhenNullNameOnSetter(String value) throws ActivityDiagramRuleException {
		finalNode = new FinalNode(NAME);
		
		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> finalNode.setName(value));
		
		assertEquals(exception.getMessage(), ERROR_MESSAGE);
	}

}
