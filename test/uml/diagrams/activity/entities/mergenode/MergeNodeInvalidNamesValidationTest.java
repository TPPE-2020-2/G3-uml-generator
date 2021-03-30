package uml.diagrams.activity.entities.mergenode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class MergeNodeInvalidNamesValidationTest {

	private final String NAME = "mergeNode";
	private final String ERROR_MESSAGE = "O nome não pode ser vazio";

	private MergeNode mergeNode;
	
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
				() -> new MergeNode(value));
		
		assertEquals(exception.getMessage(), ERROR_MESSAGE);
	}

	@ParameterizedTest
	@MethodSource("emptyValues")
	void testErrorWhenNullNameOnSetter(String value) throws ActivityDiagramRuleException {
		mergeNode = new MergeNode(NAME);
		
		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> mergeNode.setName(value));
		
		assertEquals(exception.getMessage(), ERROR_MESSAGE);
	}

}
