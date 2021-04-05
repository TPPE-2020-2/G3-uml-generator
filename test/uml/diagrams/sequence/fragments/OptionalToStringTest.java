package uml.diagrams.sequence.fragments;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;

public class OptionalToStringTest {

	public static Collection<Object[]> optionalParams() throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
        SequenceDiagram sequenceDiagram1 = new SequenceDiagram("diagram1", true);
        SequenceDiagram sequenceDiagram2 = new SequenceDiagram("diagram2", false);
        SequenceDiagram sequenceDiagram3 = new SequenceDiagram("diagram3", true);

        return Arrays.asList(new Object[][] {
            {
                new Optional("fragment1", sequenceDiagram1),
                "<Optional name=\"fragment1\" representedBy=\"diagram1\"/>"
            },
            {
                new Optional("fragment2", sequenceDiagram2),
                "<Optional name=\"fragment2\" representedBy=\"diagram2\"/>"
            },
            {
                new Optional("fragment3", sequenceDiagram3),
                "<Optional name=\"fragment3\" representedBy=\"diagram3\"/>"
            }
        });
	}

	@ParameterizedTest
	@MethodSource("optionalParams")
	void testOptionalSuccessWithValidFragmentsConstructor(Optional optional, String expectedOutput)
			throws SequenceDiagramRuleException {
		assertEquals(expectedOutput, optional.toString());
	}
}
