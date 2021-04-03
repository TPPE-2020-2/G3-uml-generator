package uml.diagrams.sequence.fragments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;
import uml.diagrams.sequence.sequencediagrams.fragments.Fragment;

public class OptionalInvalidSequenceDiagramTest {
    
    private Optional validOptional;

    private static final String DEFAULT_NAME = "default";
    private static SequenceDiagram validSequenceDiagram;
    private static Fragment validFragment;

    @BeforeEach
    public void setup()
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
        validOptional = new Optional(validFragment, validSequenceDiagram);
    }

    @BeforeAll
    public static void init() throws SequenceDiagramRuleException {
        validFragment = new Fragment(DEFAULT_NAME);
        validSequenceDiagram = new SequenceDiagram(DEFAULT_NAME, true);
        
        validSequenceDiagram.addElement(validFragment);
    }

    public static Collection<Object[]> optionalInvalidSequenceDiagramParams()
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
        return Arrays.asList(new Object[][] { 
            { validFragment, null }
        });
    }

    @ParameterizedTest
    @MethodSource("optionalInvalidSequenceDiagramParams")
    void testFailWithInvalidSequenceDiagramConstructor(Fragment fragment, SequenceDiagram diagram)
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
        EmptyOptionalFragmentException exception = assertThrows(EmptyOptionalFragmentException.class,
                () -> new Optional(fragment, diagram));

        assertEquals(Optional.REPRESENTED_BY_ERROR, exception.getMessage());
    }
    
    @ParameterizedTest
    @MethodSource("optionalInvalidSequenceDiagramParams")
    void testFailWithInvalidSequenceDiagramSetter(Fragment fragment, SequenceDiagram diagram)
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
        EmptyOptionalFragmentException exception = assertThrows(EmptyOptionalFragmentException.class,
                () -> validOptional.setRepresentedBy(diagram));

        assertEquals(Optional.REPRESENTED_BY_ERROR, exception.getMessage());
    }
}
