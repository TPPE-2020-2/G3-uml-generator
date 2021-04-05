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

public class OptionalInvalidSequenceDiagramTest {
    
    private Optional validOptional;

    private static final String DEFAULT_NAME = "default";
    private static SequenceDiagram validSequenceDiagram;

    @BeforeEach
    public void setup()
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
        validOptional = new Optional(DEFAULT_NAME, validSequenceDiagram);
    }

    @BeforeAll
    public static void init() throws SequenceDiagramRuleException {
        validSequenceDiagram = new SequenceDiagram(DEFAULT_NAME, true);
    }

    public static Collection<Object[]> optionalInvalidSequenceDiagramParams()
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
        return Arrays.asList(new Object[][] { 
            { DEFAULT_NAME, null }
        });
    }

    @ParameterizedTest
    @MethodSource("optionalInvalidSequenceDiagramParams")
    void testFailWithInvalidSequenceDiagramConstructor(String optionalName, SequenceDiagram diagram)
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
        EmptyOptionalFragmentException exception = assertThrows(EmptyOptionalFragmentException.class,
                () -> new Optional(optionalName, diagram));

        assertEquals(Optional.REPRESENTED_BY_ERROR, exception.getMessage());
    }
    
    @ParameterizedTest
    @MethodSource("optionalInvalidSequenceDiagramParams")
    void testFailWithInvalidSequenceDiagramSetter(String optionalName, SequenceDiagram diagram)
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
        EmptyOptionalFragmentException exception = assertThrows(EmptyOptionalFragmentException.class,
                () -> validOptional.setRepresentedBy(diagram));

        assertEquals(Optional.REPRESENTED_BY_ERROR, exception.getMessage());
    }
}
