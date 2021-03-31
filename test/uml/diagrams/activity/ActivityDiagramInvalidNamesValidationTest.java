package uml.diagrams.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityDiagramInvalidNamesValidationTest {
	private final String NAME = "finalNode";
	private final String ERROR_MESSAGE = "O nome não pode ser vazio";

	private ActivityDiagram activityDiagram;
	
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
				() -> new ActivityDiagram(value));
		
		assertEquals(exception.getMessage(), ERROR_MESSAGE);
	}

	@ParameterizedTest
	@MethodSource("emptyValues")
	void testErrorWhenNullNameOnSetter(String value) throws ActivityDiagramRuleException {
		activityDiagram = new ActivityDiagram(NAME);
		
		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> activityDiagram.setName(value));
		
		assertEquals(exception.getMessage(), ERROR_MESSAGE);
	}
}
