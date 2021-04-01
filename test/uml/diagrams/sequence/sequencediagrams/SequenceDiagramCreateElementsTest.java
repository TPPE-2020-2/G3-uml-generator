package uml.diagrams.sequence.sequencediagrams;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.MessageFormatException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.messages.Message;
import uml.diagrams.sequence.messages.MessageAsync;
import uml.diagrams.sequence.messages.MessageSync;
import uml.diagrams.sequence.messages.Reply;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;

public class SequenceDiagramCreateElementsTest {
    public static Collection<Object[]> sequenceDiagrams()
            throws MessageFormatException, SequenceDiagramRuleException {
        return Arrays.asList(new Object[][] {
                {
                    "diagram1",
                    true,
                    Arrays.asList(
                            new MessageSync("messagesync", 0.5f, new Lifeline("origem"), new Lifeline("destino"))
                    ),
                    1
                },
                {
                    "diagram2",
                    false,
                    Arrays.asList(
                            new MessageSync("messagesync", 0.5f, new Lifeline("origem"), new Lifeline("destino")),
                            new MessageAsync("messageasync", 0.99f, new Lifeline("origem"), new Lifeline("destino"))
                    ),
                    2
                },
                {
                    "diagram3",
                    true,
                    Arrays.asList(
                            new MessageAsync("messageasync", 0.99f, new Lifeline("origem"), new Lifeline("destino")),
                            new MessageSync("messagesync", 0.5f, new Lifeline("origem"), new Lifeline("destino")),
                            new Reply("reply", 0.0f, new Lifeline("origem"), new Lifeline("destino"))
                    ),
                    3
                }
        });
    }

    @ParameterizedTest
    @MethodSource("sequenceDiagrams")
    void testGetSequenceDiagramsToString(String name, Boolean guard,
            List<Message> messages, int expectedMessageSize)
            throws SequenceDiagramRuleException {
        SequenceDiagram diagram = new SequenceDiagram(name, guard);

        for (Message message : messages) {
            diagram.addMessage(message);
        }
        
        List<Message> returnedMessages = diagram.getMessages();
        
        assertEquals(expectedMessageSize, returnedMessages.size());
        
        for (int i = 0; i < returnedMessages.size(); i++) {
            assertEquals(messages.get(i), returnedMessages.get(i));
        }
    }
}
