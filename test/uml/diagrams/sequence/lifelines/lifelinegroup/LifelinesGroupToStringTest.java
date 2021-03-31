package uml.diagrams.sequence.lifelines.lifelinegroup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.lifelines.LifelinesGroup;

public class LifelinesGroupToStringTest {

    public static Collection<Object[]> lifelinesGroupParams() {
        return Arrays.asList(new Object[][] {
                { Arrays.asList("lifeline1"),
                    "<Lifelines>" +
                        "<Lifeline name=\"lifeline1\" />" +
                    "</Lifelines>"},
                { Arrays.asList("lifeline1", "lifeline2"),
                    "<Lifelines>" +
                        "<Lifeline name=\"lifeline1\" />" +
                        "<Lifeline name=\"lifeline2\" />" +
                    "</Lifelines>" },
                { Arrays.asList("lifeline1", "lifeline2", "lifeline3"),
                    "<Lifelines>"+
                        "<Lifeline name=\"lifeline1\" />" +
                        "<Lifeline name=\"lifeline2\" />" +
                        "<Lifeline name=\"lifeline3\" />" +
                    "</Lifelines>"
                }
        });
    }

    @ParameterizedTest
    @MethodSource("lifelinesGroupParams")
    void testGetLifelinesGroupToString(List<String> lifelinesNames, String expectedString)
            throws SequenceDiagramRuleException {
        LifelinesGroup lifelinesGroup = new LifelinesGroup();

        for (String lifelineName : lifelinesNames) {
            lifelinesGroup.addLifeline(new Lifeline(lifelineName));
        }

        assertEquals(expectedString, lifelinesGroup.toString());
    }
}
