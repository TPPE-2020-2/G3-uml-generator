package uml.diagrams.sequence.messages;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.MessageFormatException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.messages.Message;

class MessageCreationTest {

    public static Collection<Object[]> messageParms() {
        return Arrays.asList(new Object[][] {
            {
                "name", 0.5f, "origem", "destino"
            },
            {
                "name", 1f, "node1", "node2"
            },
            {
                "name", 0f, "1", "2"
            }
        });
    }

    @ParameterizedTest
    @MethodSource("messageParms")
    void testGetMessageToString(String name, Float prob, String sourceStr,
            String targetStr) throws SequenceDiagramRuleException, MessageFormatException {
        Lifeline source = new Lifeline(sourceStr);
        Lifeline target = new Lifeline(targetStr);
        Message message = new Message(name, prob, source, target);

        assertEquals(name, message.getName());
        assertEquals(prob, message.getProb(), 0.0);
        assertEquals(source, message.getSource());
        assertEquals(target, message.getTarget());
    }
}
