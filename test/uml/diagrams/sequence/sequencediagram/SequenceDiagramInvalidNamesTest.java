package uml.diagrams.sequence.sequencediagram;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.BaseElement;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;


public class SequenceDiagramInvalidNamesTest {
    public static Collection<Object[]> sequenceDiagramsName() {
        return Arrays.asList(new Object[][] {
        	{"", true},
            {null, false}
        });
    }

    @ParameterizedTest
    @MethodSource("sequenceDiagramsName")
    void testErrorCreateSequenceDiagrams(String name, Boolean guardCondition) {
        SequenceDiagramRuleException exception = assertThrows(
                SequenceDiagramRuleException.class,
                () -> new SequenceDiagram(name, guardCondition));
        
        assertEquals(exception.getMessage(), BaseElement.NAME_ERROR);
    }
}
