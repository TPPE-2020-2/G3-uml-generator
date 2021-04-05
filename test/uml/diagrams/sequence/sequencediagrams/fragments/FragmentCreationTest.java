package uml.diagrams.sequence.sequencediagrams.fragments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.fragments.Optional;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;

public class FragmentCreationTest {
    
    private static final String DEFAULT_NAME = "default";
    
    private Fragment fragment;
    private SequenceDiagram sequenceDiagram;

    @BeforeEach
    public void setup()
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
        sequenceDiagram = new SequenceDiagram("diagram1", true);
        fragment = new Fragment(new Optional(DEFAULT_NAME, sequenceDiagram));
    }
    
    public static Collection<Object[]> fragmentParams() {
        return Arrays.asList(new Object[][] {
            { "name1"},
            { "name2"},
            { "name3"}
        });
    }
    
    @ParameterizedTest
    @MethodSource("fragmentParams")
    void testFragmentWithValidOptionalsConstructor(String name)
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
        fragment = new Fragment(new Optional(name, sequenceDiagram));

        assertEquals(name, fragment.getName());
    }
    
    @ParameterizedTest
    @MethodSource("fragmentParams")
    void testFragmentWithValidOptionalSetter(String name)
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
        fragment.setOptional(new Optional(name, sequenceDiagram));

        assertEquals(name, fragment.getName());
    }

    @Test
    void testFailFragmentWithInvalidOptionalConstructor()
            throws SequenceDiagramRuleException {
        SequenceDiagramRuleException exception = assertThrows(SequenceDiagramRuleException.class,
                () -> new Fragment(null));

        assertEquals(Fragment.INVALID_OPTIONAL, exception.getMessage());
    }

    @Test
    void testFailFragmentWithInvalidNamesSetter()
            throws SequenceDiagramRuleException {
        SequenceDiagramRuleException exception = assertThrows(SequenceDiagramRuleException.class,
                () -> new Fragment(null));

        assertEquals(Fragment.INVALID_OPTIONAL, exception.getMessage());
    }
}
