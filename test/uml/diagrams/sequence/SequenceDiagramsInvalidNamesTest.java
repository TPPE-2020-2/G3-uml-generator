package uml.diagrams.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;


public class SequenceDiagramsInvalidNamesTest {
    public static Collection<Object[]> sequenceDiagramsName() {
        return Arrays.asList(new Object[][] {
            {""},
            {null}
        });
    }
    
    @ParameterizedTest
    @MethodSource("sequenceDiagramsName")
    void testErrorCreateSequenceDiagrams(String name) {
        SequenceDiagramRuleException exception = assertThrows(
                SequenceDiagramRuleException.class,
                () -> new SequenceDiagrams(name));
        
        assertEquals(exception.getMessage(), BaseElement.NAME_ERROR);
    }

}