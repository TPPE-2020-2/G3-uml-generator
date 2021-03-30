package uml.diagrams.sequence.lifelines.lifeline;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.lifelines.Lifeline;

public class LifelineToStringTest {

    public static Collection<Object[]>lifelineParams() {
        return Arrays.asList(new Object[][] {
            {"name", "<Lifeline name=\"name\" />"},
            {"test123", "<Lifeline name=\"test123\" />"},
            {"a", "<Lifeline name=\"a\" />"},
            {"1", "<Lifeline name=\"1\" />"}
        });
    }
    
    @ParameterizedTest
    @MethodSource("lifelineParams")
    void testGetLifelineToString(String name, String expectedString) throws SequenceDiagramRuleException {
        Lifeline lifeline = new Lifeline(name);

        assertEquals(expectedString, lifeline.toString());
    }
}
