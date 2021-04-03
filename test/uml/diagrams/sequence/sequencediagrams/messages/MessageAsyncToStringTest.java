package uml.diagrams.sequence.sequencediagrams.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.MessageFormatException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.lifelines.Lifeline;

public class MessageAsyncToStringTest {
    
    public static Collection<Object[]> messageParams() {
        return Arrays.asList(new Object[][] {
            {
                "name", 0.5f, "origem", "destino",
                "<MessageAsync name=\"name\" prob=\"0.5\" source=\"origem\" target=\"destino\" />"
            },
            {
                "name", 1f, "node1", "node2",
                "<MessageAsync name=\"name\" prob=\"1.0\" source=\"node1\" target=\"node2\" />"
            },
            {
                "name", 0f, "node1", "node2",
                "<MessageAsync name=\"name\" prob=\"0.0\" source=\"node1\" target=\"node2\" />"
            }
        });
    }

    @ParameterizedTest
    @MethodSource("messageParams")
    void testGetMessageToString(String name, Float prob, String sourceStr,
            String targetStr, String expectedString)
                    throws SequenceDiagramRuleException, MessageFormatException {
        Lifeline source = new Lifeline(sourceStr);
        Lifeline target = new Lifeline(targetStr);
        MessageAsync message = new MessageAsync(name, prob, source, target);

        assertEquals(expectedString, message.toString());
    }
}
