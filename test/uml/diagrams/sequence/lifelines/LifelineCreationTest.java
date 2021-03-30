package uml.diagrams.sequence.lifelines;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;

class LifelineCreationTest {

    public static Collection<Object[]> lifelineParams() {
        return Arrays.asList(new Object[][] {
            {"lifeline"},
            {"test123"},
            {"a"},
            {"1"}
        });
    }

    @ParameterizedTest
    @MethodSource("lifelineParams")
    void testCreateLifeline(String name) throws SequenceDiagramRuleException {
        Lifeline lifeline = new Lifeline(name);
        
        assertEquals(name, lifeline.getName());
    }
}
