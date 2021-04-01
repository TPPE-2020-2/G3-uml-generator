package uml.diagrams.sequence.fragments;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.sequencediagram.SequenceDiagram;

public class OptionalToStringTest {
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
			{ "name1", sequenceDiagram, "<Optional name=\"name1\" representedBy=\"default\"/>" },
			{ "name2", sequenceDiagram, "<Optional name=\"name2\" representedBy=\"default\"/>" },
			{ "name3", sequenceDiagram, "<Optional name=\"name3\" representedBy=\"default\"/>" }, });
	}

	@ParameterizedTest
	@MethodSource("optionalParams")
	void testOptionalSuccessWithValidNamesConstructor(String name, SequenceDiagram diagram, String expectedOutput)
			throws SequenceDiagramRuleException {
		optional.setName(name);
		assertEquals(expectedOutput, optional.toString());
	}
}
