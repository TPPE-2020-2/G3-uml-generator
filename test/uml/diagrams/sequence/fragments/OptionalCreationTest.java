package uml.diagrams.sequence.fragments;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;

public class OptionalCreationTest {

    public static Collection<Object[]> optionalParams()
	        throws SequenceDiagramRuleException {
        SequenceDiagram sequenceDiagram1 = new SequenceDiagram("diagram1", true);
        SequenceDiagram sequenceDiagram2 = new SequenceDiagram("diagram2", false);
        SequenceDiagram sequenceDiagram3 = new SequenceDiagram("diagram3", true);

		return Arrays.asList(new Object[][] {
			{ "fragment1", sequenceDiagram1 },
			{ "fragment2", sequenceDiagram2 },
			{ "fragment3", sequenceDiagram3 }
		});
	}

	@ParameterizedTest
	@MethodSource("optionalParams")
	void testOptionalSuccessWithValidFragmentsConstructor(String optionalName, SequenceDiagram diagram)
			throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
	    Optional optional = new Optional(optionalName, diagram);

		assertEquals(optionalName, optional.getName());
		assertEquals(diagram, optional.getRepresentedBy());
	}
}
