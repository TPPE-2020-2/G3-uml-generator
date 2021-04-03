package uml.diagrams.sequence.sequencediagrams.fragments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.BaseElement;
import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;

public class FragmentNameTest {
    
    private static final String DEFAULT_NAME = "default";
    
    private Fragment fragment;

    @BeforeEach
    public void setup()
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
        fragment = new Fragment(DEFAULT_NAME);
    }
    
    public static Collection<Object[]> fragmentParams() {
        return Arrays.asList(new Object[][] {
            { "name1"},
            { "name2"},
            { "name3"}
        });
    }
    
    public static Collection<Object[]> optionalInvalidNameParams() {
        return Arrays.asList(new Object[][] { 
            { "" }, 
            { null }, });
    }
    
    @ParameterizedTest
    @MethodSource("fragmentParams")
    void testFragmentWithValidNamesConstructor(String name)
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
        fragment = new Fragment(name);

        assertEquals(name, fragment.getName());
    }
    
    @ParameterizedTest
    @MethodSource("fragmentParams")
    void testFragmentWithValidNamesSetter(String name)
            throws SequenceDiagramRuleException {
        fragment.setName(name);

        assertEquals(name, fragment.getName());
    }

    @ParameterizedTest
    @MethodSource("optionalInvalidNameParams")
    void testFailFragmentWithInvalidNamesConstructor(String name)
            throws SequenceDiagramRuleException {
        SequenceDiagramRuleException exception = assertThrows(SequenceDiagramRuleException.class,
                () -> new Fragment(name));

        assertEquals(BaseElement.NAME_ERROR, exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("optionalInvalidNameParams")
    void testFailFragmentWithInvalidNamesSetter(String name)
            throws SequenceDiagramRuleException {
        SequenceDiagramRuleException exception = assertThrows(SequenceDiagramRuleException.class,
                () -> fragment.setName(name));

        assertEquals(BaseElement.NAME_ERROR, exception.getMessage());
    }
}
