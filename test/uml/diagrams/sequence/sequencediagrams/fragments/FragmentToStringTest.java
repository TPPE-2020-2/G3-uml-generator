package uml.diagrams.sequence.sequencediagrams.fragments;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;

public class FragmentToStringTest {

    public static Collection<Object[]> fragmentParams() {
        return Arrays.asList(new Object[][] { 
            { "name1", "<Fragment name=\"name1\" />" },
            { "name2", "<Fragment name=\"name2\" />" },
            { "name3", "<Fragment name=\"name3\" />" }, });
    }

    @ParameterizedTest
    @MethodSource("fragmentParams")
    void testFragmentToString(String name, String expectedOutput)
            throws SequenceDiagramRuleException {
        Fragment fragment = new Fragment (name);
        assertEquals(expectedOutput, fragment.toString());
    }
}
