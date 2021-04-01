package uml.diagrams.activity.entities.activitynode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityNodeInvalidNamesValidationTest {

	private final String NAME = "finalNode";
	private final String ERROR_MESSAGE = "O nome não pode ser vazio";

	private ActivityNode activityNode;
	
	public static Collection<Object[]> emptyValues() {
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
				() -> new ActivityNode(value));
		
		assertEquals(exception.getMessage(), ERROR_MESSAGE);
	}

	@ParameterizedTest
	@MethodSource("emptyValues")
	void testErrorWhenNullNameOnSetter(String value) throws ActivityDiagramRuleException {
		activityNode = new ActivityNode(NAME);
		
		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> activityNode.setName(value));
		
		assertEquals(exception.getMessage(), ERROR_MESSAGE);
	}
}
