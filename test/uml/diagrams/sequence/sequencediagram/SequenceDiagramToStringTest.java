package uml.diagrams.sequence.sequencediagram;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.MessageFormatException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.message.Message;
import uml.diagrams.sequence.message.MessageAsync;
import uml.diagrams.sequence.message.MessageSync;
import uml.diagrams.sequence.message.Reply;

public class SequenceDiagramToStringTest {

    public static Collection<Object[]> sequenceDiagrams()
            throws MessageFormatException, SequenceDiagramRuleException {
        return Arrays.asList(new Object[][] {
                {
                    "diagram1",
                    true,
                    Arrays.asList(
                            new MessageSync("messagesync", 0.5f, new Lifeline("origem"), new Lifeline("destino"))
                    ),
                    "<SequenceDiagram name=\"diagram1\" guardCondition=\"true\">" +
                    "<MessageSync name=\"messagesync\" prob=\"0.5\" source=\"origem\" target=\"destino\" />" +
                    "</SequenceDiagram>"
                },
                {
                    "diagram2",
                    false,
                    Arrays.asList(
                            new MessageSync("messagesync", 0.5f, new Lifeline("origem"), new Lifeline("destino")),
                            new MessageAsync("messageasync", 0.99f, new Lifeline("origem"), new Lifeline("destino"))
                    ),
                    "<SequenceDiagram name=\"diagram2\" guardCondition=\"false\">" +
                    "<MessageSync name=\"messagesync\" prob=\"0.5\" source=\"origem\" target=\"destino\" />" +
                    "<MessageAsync name=\"messageasync\" prob=\"0.99\" source=\"origem\" target=\"destino\" />" +
                    "</SequenceDiagram>"
                },
                {
                    "diagram3",
                    true,
                    Arrays.asList(
                            new MessageAsync("messageasync", 0.99f, new Lifeline("origem"), new Lifeline("destino")),
                            new MessageSync("messagesync", 0.5f, new Lifeline("origem"), new Lifeline("destino")),
                            new Reply("reply", 0.0f, new Lifeline("origem"), new Lifeline("destino"))
                    ),
                    "<SequenceDiagram name=\"diagram3\" guardCondition=\"true\">" +
                    "<MessageAsync name=\"messageasync\" prob=\"0.99\" source=\"origem\" target=\"destino\" />" +
                    "<MessageSync name=\"messagesync\" prob=\"0.5\" source=\"origem\" target=\"destino\" />" +
                    "<Reply name=\"reply\" prob=\"0.0\" source=\"origem\" target=\"destino\" />" +
                    "</SequenceDiagram>"
                }
        });
    }

    @ParameterizedTest
    @MethodSource("sequenceDiagrams")
    void testGetSequenceDiagramsToString(String name, Boolean guard, List<Message> messages, String expectedString)
            throws SequenceDiagramRuleException {
        SequenceDiagram diagram = new SequenceDiagram(name, guard);

        for (Message message : messages) {
            diagram.addMessage(message);
        }
        
        assertEquals(expectedString, diagram.toString());
    }
}
