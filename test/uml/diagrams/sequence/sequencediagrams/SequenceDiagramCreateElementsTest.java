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
import uml.diagrams.sequence.sequencediagrams.fragments.Fragment;
import uml.diagrams.sequence.sequencediagrams.messages.MessageAsync;
import uml.diagrams.sequence.sequencediagrams.messages.MessageSync;
import uml.diagrams.sequence.sequencediagrams.messages.Reply;

public class SequenceDiagramCreateElementsTest {
    public static Collection<Object[]> sequenceDiagrams()
            throws MessageFormatException, SequenceDiagramRuleException {
        return Arrays.asList(new Object[][] {
                {
                    "diagram1",
                    true,
                    Arrays.asList(
                            new MessageSync("messagesync", 0.5f, new Lifeline("origem"), new Lifeline("destino")),
                            new Fragment("fragment1")
                    ),
                    2
                },
                {
                    "diagram2",
                    false,
                    Arrays.asList(
                            new Fragment("fragment1"),
                            new MessageSync("messagesync", 0.5f, new Lifeline("origem"), new Lifeline("destino")),
                            new MessageAsync("messageasync", 0.99f, new Lifeline("origem"), new Lifeline("destino"))
                    ),
                    3
                },
                {
                    "diagram3",
                    true,
                    Arrays.asList(
                            new MessageAsync("messageasync", 0.99f, new Lifeline("origem"), new Lifeline("destino")),
                            new Fragment("fragment1"),
                            new MessageSync("messagesync", 0.5f, new Lifeline("origem"), new Lifeline("destino")),
                            new Reply("reply", 0.0f, new Lifeline("origem"), new Lifeline("destino")),
                            new Fragment("fragment2")
                    ),
                    5
                }
        });
    }

    @ParameterizedTest
    @MethodSource("sequenceDiagrams")
    void testGetSequenceDiagramsToString(String name, Boolean guard,
            List<ISequenceDiagramElement> elements, int expectedMessageSize)
            throws SequenceDiagramRuleException {
        SequenceDiagram diagram = new SequenceDiagram(name, guard);

        for (ISequenceDiagramElement element : elements) {
            diagram.addElement(element);
        }

        List<ISequenceDiagramElement> returnedElements = diagram.getElements();

        assertEquals(expectedMessageSize, returnedElements.size());
        
        for (int i = 0; i < returnedElements.size(); i++) {
            assertEquals(elements.get(i), returnedElements.get(i));
        }
    }
}
