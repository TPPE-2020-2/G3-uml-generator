package uml.diagrams.sequence.lifelines;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class LifelineCreationTest {

    public static Collection<Object[]> lifelineParams() {
        return Arrays.asList(new Object[][] {
            {"lifeline", "lifeline" },
            {"test123", "test123"},
            {"", ""},
            {null, ""}
        });
    }

    @ParameterizedTest
    @MethodSource("lifelineParams")
    void testCreateLifeline(String name, String expectedName) {
        Lifeline lifeline = new Lifeline(name);
        
        assertEquals(expectedName, lifeline.getName());
    }
}
