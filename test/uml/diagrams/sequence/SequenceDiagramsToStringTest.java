package uml.diagrams.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;

public class SequenceDiagramsToStringTest {
	
    public static Collection<Object[]>sequenceDiagramsNameTrue() {
        return Arrays.asList(new Object[][] {
            {"name", "<SequenceDiagram name=\"name\" guardCondition=\"true\" />"},
            {"test123", "<SequenceDiagram name=\"test123\" guardCondition=\"true\" />"},
            {"a", "<SequenceDiagram name=\"a\" guardCondition=\"true\" />"},
        });
    }
    
    public static Collection<Object[]>sequenceDiagramsNameFalse() {
        return Arrays.asList(new Object[][] {
            {"name", "<SequenceDiagram name=\"name\" guardCondition=\"false\" />"},
            {"test123", "<SequenceDiagram name=\"test123\" guardCondition=\"false\" />"},
            {"a", "<SequenceDiagram name=\"a\" guardCondition=\"false\" />"},
        });
    }
    
    @ParameterizedTest
    @MethodSource("sequenceDiagramsNameTrue")
    void testGetSequenceDiagramsToStringTrue(String name, String expectedString) throws SequenceDiagramRuleException {
        SequenceDiagrams diagram = new SequenceDiagrams(name, true);

        assertEquals(expectedString, diagram.toString());
    }

    @ParameterizedTest
    @MethodSource("sequenceDiagramsNameFalse")
    void testGetSequenceDiagramsToStringFalse(String name, String expectedString) throws SequenceDiagramRuleException {
        SequenceDiagrams diagram = new SequenceDiagrams(name, false);

        assertEquals(expectedString, diagram.toString());
    }
}
