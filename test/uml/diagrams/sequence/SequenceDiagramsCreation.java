package uml.diagrams.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;


public class SequenceDiagramsCreation {
    public static Collection<Object[]> sequenceDiagramsName() {
        return Arrays.asList(new Object[][] {
            {"lifeline"},
            {"test123"},
            {"a"},
            {"1"}
        });
    }

    @ParameterizedTest
    @MethodSource("sequenceDiagramsName")
    void testCreateSequenceDiagrams(String name) throws SequenceDiagramRuleException {
        SequenceDiagrams diagram = new SequenceDiagrams(name);
        
        assertEquals(name, diagram.getName());
    }
}
