package uml.diagrams.sequence.sequencediagrams;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;


public class SequenceDiagramCreation {
    public static Collection<Object[]> sequenceDiagramsName() {
        return Arrays.asList(new Object[][] {
            {"lifeline", true},
            {"test123", false},
            {"a", true},
            {"1", false}
        });
    }

    @ParameterizedTest
    @MethodSource("sequenceDiagramsName")
    void testCreateSequenceDiagrams(String name,Boolean guardCondition) throws SequenceDiagramRuleException {
        SequenceDiagram diagram = new SequenceDiagram(name, guardCondition);
        
        assertEquals(name, diagram.getName());
        assertEquals(guardCondition, diagram.getGuardCondition());
    }
}
