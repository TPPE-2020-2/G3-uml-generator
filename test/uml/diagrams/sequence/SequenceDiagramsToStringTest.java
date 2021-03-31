package uml.diagrams.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;

public class SequenceDiagramsToStringTest {
	
    public static Collection<Object[]>sequenceDiagramsName() {
        return Arrays.asList(new Object[][] {
            {"name", "<SequenceDiagrams name=\"name\" />"},
            {"test123", "<SequenceDiagrams name=\"test123\" />"},
            {"a", "<SequenceDiagrams name=\"a\" />"},
            {"1", "<SequenceDiagrams name=\"1\" />"}
        });
    }
    
    @ParameterizedTest
    @MethodSource("sequenceDiagramsName")
    void testGetSequenceDiagramsToString(String name, String expectedString) throws SequenceDiagramRuleException {
        SequenceDiagrams diagram = new SequenceDiagrams(name);

        assertEquals(expectedString, diagram.toString());
    }

}
