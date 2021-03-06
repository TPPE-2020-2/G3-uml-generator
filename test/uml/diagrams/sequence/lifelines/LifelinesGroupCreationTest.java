package uml.diagrams.sequence.lifelines;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;

public class LifelinesGroupCreationTest {
    
    public static Collection<Object[]> lifelinesGroupParams() {
        return Arrays.asList(new Object[][] {
            {Arrays.asList("lifeline1"), 1},
            {Arrays.asList("lifeline1", "lifeline2"), 2},
            {Arrays.asList("lifeline1", "lifeline2", "lifeline3"), 3},
        });
    }
    
    @ParameterizedTest
    @MethodSource("lifelinesGroupParams")
    void testCreateLifelinesGroup (List<String> lifelinesNames, int lifelinesAmount)
            throws SequenceDiagramRuleException {
        LifelinesGroup lifelinesGroup = new LifelinesGroup();
        
        for (String lifelineName : lifelinesNames) {
            lifelinesGroup.addLifeline(new Lifeline(lifelineName));
        }

        List<Lifeline> lifelines =  lifelinesGroup.getLifelines();

        assertEquals(lifelinesAmount, lifelines.size());
        
        for (int i = 0;  i < lifelines.size(); i++) {
            assertEquals(lifelinesNames.get(i), lifelines.get(i).getName());
        }
    }
}
