package uml.diagrams.sequence.fragments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;
import uml.diagrams.sequence.sequencediagrams.fragments.Fragment;

public class OptionalCreationTest {

    public static Collection<Object[]> optionalParams()
	        throws SequenceDiagramRuleException {
	    Fragment fragment1 = new Fragment("fragment1");
	    Fragment fragment2 = new Fragment("fragment2");
	    Fragment fragment3 = new Fragment("fragment3");
	    SequenceDiagram sequenceDiagram1 = new SequenceDiagram("diagram1", true);
	    SequenceDiagram sequenceDiagram2 = new SequenceDiagram("diagram2", false);
	    SequenceDiagram sequenceDiagram3 = new SequenceDiagram("diagram3", true);
	    
	    sequenceDiagram1.addElement(fragment1);

	    sequenceDiagram2.addElement(fragment1);
	    sequenceDiagram2.addElement(fragment2);

	    sequenceDiagram3.addElement(fragment1);
        sequenceDiagram3.addElement(fragment2);
        sequenceDiagram3.addElement(fragment3);

		return Arrays.asList(new Object[][] {
			{ fragment1, sequenceDiagram1 },
			{ fragment2, sequenceDiagram2 },
			{ fragment3, sequenceDiagram3 }
		});
	}

	@ParameterizedTest
	@MethodSource("optionalParams")
	void testOptionalSuccessWithValidFragmentsConstructor(Fragment fragment, SequenceDiagram diagram)
			throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
	    Optional optional = new Optional(fragment, diagram);

		assertEquals(fragment, optional.getFragment());
		assertEquals(diagram, optional.getRepresentedBy());
	}

	public static Collection<Object[]> optionalInvalidFragmentsParams()
	        throws SequenceDiagramRuleException {
	    Fragment fragment1 = new Fragment("fragment1");
        Fragment fragment2 = new Fragment("fragment2");
        Fragment fragment3 = new Fragment("fragment3");
        SequenceDiagram sequenceDiagram1 = new SequenceDiagram("diagram1", true);
        SequenceDiagram sequenceDiagram2 = new SequenceDiagram("diagram2", false);
        SequenceDiagram sequenceDiagram3 = new SequenceDiagram("diagram3", true);
        
        sequenceDiagram1.addElement(fragment1);

        sequenceDiagram2.addElement(fragment1);

        sequenceDiagram3.addElement(fragment1);
        sequenceDiagram3.addElement(fragment2);

        return Arrays.asList(new Object[][] {
            { null, sequenceDiagram1 },
            { fragment2, sequenceDiagram2 },
            { fragment3, sequenceDiagram3 }
        });
	}

	@ParameterizedTest
	@MethodSource("optionalInvalidFragmentsParams")
	void testFailOptionalSuccessWithInvalidNamesConstructor(Fragment fragment, SequenceDiagram diagram)
			throws SequenceDiagramRuleException {
		SequenceDiagramRuleException exception = assertThrows(SequenceDiagramRuleException.class,
				() -> new Optional(fragment, diagram));

		assertEquals(Optional.FRAGMENT_ERROR, exception.getMessage());
	}
}
