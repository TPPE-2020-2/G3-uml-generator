package uml.diagrams.sequence.fragments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.BaseElement;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;

public class OptionalNameTest {
	private Optional optional;

	private static final String DEFAULT_NAME = "default";
	private static SequenceDiagram sequenceDiagram;

	@BeforeEach
	public void setup() throws SequenceDiagramRuleException {
		optional = new Optional(DEFAULT_NAME, sequenceDiagram);
	}

	@BeforeAll
	public static void init() throws SequenceDiagramRuleException {
		sequenceDiagram = new SequenceDiagram(DEFAULT_NAME, true);
	}

	public static Collection<Object[]> optionalParams() {
		return Arrays.asList(new Object[][] {
			{ "name1", sequenceDiagram },
			{ "name2", sequenceDiagram },
			{ "name3", sequenceDiagram }, });
	}

	@ParameterizedTest
	@MethodSource("optionalParams")
	void testOptionalSuccessWithValidNamesConstructor(String name, SequenceDiagram diagram)
			throws SequenceDiagramRuleException {
		optional = new Optional(name, diagram);
		assertEquals(name, optional.getName());
	}

	@ParameterizedTest
	@MethodSource("optionalParams")
	void testOptionalSuccessWithValidNamesSetter(String name, SequenceDiagram diagram)
			throws SequenceDiagramRuleException {
		optional.setName(name);

		assertEquals(name, optional.getName());
	}

	public static Collection<Object[]> optionalInvalidNameParams() {
		return Arrays.asList(new Object[][] { 
			{ "", sequenceDiagram }, 
			{ null, sequenceDiagram }, });
	}

	@ParameterizedTest
	@MethodSource("optionalInvalidNameParams")
	void testFailOptionalSuccessWithInvalidNamesConstructor(String name, SequenceDiagram diagram)
			throws SequenceDiagramRuleException {
		SequenceDiagramRuleException exception = assertThrows(SequenceDiagramRuleException.class,
				() -> new Optional(name, diagram));

		assertEquals(BaseElement.NAME_ERROR, exception.getMessage());
	}

	@ParameterizedTest
	@MethodSource("optionalInvalidNameParams")
	void testFailOptionalSuccessWithInvalidNamesSetter(String name, SequenceDiagram diagram)
			throws SequenceDiagramRuleException {
		SequenceDiagramRuleException exception = assertThrows(SequenceDiagramRuleException.class,
				() -> optional.setName(name));

		assertEquals(BaseElement.NAME_ERROR, exception.getMessage());
	}
}
