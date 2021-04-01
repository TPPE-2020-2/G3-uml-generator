package uml.diagrams.sequence.sequencediagrams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;

public class SequenceDiagramEmptyGuardTest {
    
    public static Collection<Object[]> sequenceDiagramsName() {
        return Arrays.asList(new Object[][] {
            {"name", null }
        });
    }
    
    @ParameterizedTest
    @MethodSource("sequenceDiagramsName")
    void testErrorEmptyGuard(String name, Boolean guardCondition) {
        SequenceDiagramRuleException exception = assertThrows(
                SequenceDiagramRuleException.class,
                () -> new SequenceDiagram(name, guardCondition));
        
        assertEquals(SequenceDiagram.INVALID_GUARD_CONDITION_VALUE_ERROR_MESSAGE , exception.getMessage());
    }
}
