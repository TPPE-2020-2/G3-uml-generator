package uml.diagrams.sequence.lifelines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.BaseElement;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;

public class LifelineInvalidNamesTest {
    
    public static Collection<Object[]> lifelineParams() {
        return Arrays.asList(new Object[][] {
            {""},
            {null}
        });
    }
    
    @ParameterizedTest
    @MethodSource("lifelineParams")
    void testErrorCreateLifeline(String name) {
        SequenceDiagramRuleException exception = assertThrows(
                SequenceDiagramRuleException.class,
                () -> new Lifeline(name));
        
        assertEquals(exception.getMessage(), BaseElement.NAME_ERROR);
    }
}
