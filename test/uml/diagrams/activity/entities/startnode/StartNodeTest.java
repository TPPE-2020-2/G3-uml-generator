package uml.diagrams.activity.entities.startnode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class StartNodeTest {
	private final String NAME = "startNode";
	private final String ERROR_MESSAGE = "O nome não pode ser vazio";

	private StartNode initialNode;
	
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
				() -> new StartNode(value));
		
		assertEquals(exception.getMessage(), ERROR_MESSAGE);
	}

	@ParameterizedTest
	@MethodSource("emptyValues")
	void testErrorWhenNullNameOnSetter(String value) throws ActivityDiagramRuleException {
		initialNode = new StartNode(NAME);
		
		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> initialNode.setName(value));
		
		assertEquals(exception.getMessage(), ERROR_MESSAGE);
	}
}
