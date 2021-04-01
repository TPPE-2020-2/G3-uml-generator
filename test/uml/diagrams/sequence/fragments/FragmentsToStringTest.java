package uml.diagrams.sequence.fragments;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;

public class FragmentsToStringTest {
	private final static String DEFAULT_NAME = "default";
	private static SequenceDiagram sequenceDiagram;

	private Fragments fragments;

	@BeforeEach
	public void setup() throws SequenceDiagramRuleException {
		fragments = new Fragments();
	}

	@BeforeAll
	public static void init() throws SequenceDiagramRuleException {
		sequenceDiagram = new SequenceDiagram(DEFAULT_NAME, true);
	}

	public static Collection<Object[]> optionalParams()
	        throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
		return Arrays.asList(new Object[][] {
			{ new ArrayList<Optional>(), "<Fragments></Fragments>"},
			{ Arrays.asList(
					new Optional(DEFAULT_NAME, sequenceDiagram)), 
			"<Fragments><Optional name=\"default\" representedBy=\"default\"/></Fragments>"},
			{ Arrays.asList(
					new Optional(DEFAULT_NAME, sequenceDiagram), 
					new Optional(DEFAULT_NAME + "2", sequenceDiagram)), 
			"<Fragments>"
			+ "<Optional name=\"default\" representedBy=\"default\"/>"
			+ "<Optional name=\"default2\" representedBy=\"default\"/>"
			+ "</Fragments>"},
			{ Arrays.asList(
					new Optional(DEFAULT_NAME, sequenceDiagram), 
					new Optional(DEFAULT_NAME + "2", sequenceDiagram),
					new Optional(DEFAULT_NAME + "3", sequenceDiagram)), 
			"<Fragments>"
			+ "<Optional name=\"default\" representedBy=\"default\"/>"
			+ "<Optional name=\"default2\" representedBy=\"default\"/>"
			+ "<Optional name=\"default3\" representedBy=\"default\"/>"
			+ "</Fragments>" }
		});
	}

	@ParameterizedTest
	@MethodSource("optionalParams")
	void testGetAndSetValuesWithSuccess(List<Optional> optionals, String expectedToString)
			throws SequenceDiagramRuleException {
		for (Optional optional : optionals)
			fragments.addOptional(optional);
		assertEquals(expectedToString, fragments.toString());
	}
}
