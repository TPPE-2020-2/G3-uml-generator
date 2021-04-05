package uml.diagrams.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;
import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.MessageFormatException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.fragments.Optional;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;
import uml.diagrams.sequence.sequencediagrams.fragments.Fragment;
import uml.diagrams.sequence.sequencediagrams.messages.Message;

public class SequenceDiagramsGroupCreateElementsTest {
    private final static String DEFAULT_NAME = "default";
    private final static Float DEFAULT_PROB = 0.5f;

    private static Lifeline lifeline;
    private static SequenceDiagram sequenceDiagram1;
    private static SequenceDiagram sequenceDiagram2;
    private static Optional optional;
    private static Fragment fragment;
    private static Message message;

    private SequenceDiagramsGroup sequenceDiagramsGroup;

    @BeforeEach
    public void setup() {
        sequenceDiagramsGroup = new SequenceDiagramsGroup();
    }

    @BeforeAll
    public static void initialSetup() throws ActivityDiagramRuleException,
        SequenceDiagramRuleException, MessageFormatException, EmptyOptionalFragmentException {
        lifeline = new Lifeline(DEFAULT_NAME);
        sequenceDiagram1 = new SequenceDiagram("diagram1", true);
        sequenceDiagram2 = new SequenceDiagram("diagram2", false);
        sequenceDiagram1.addElement(fragment);
        optional = new Optional("fragment", sequenceDiagram1);
        fragment = new Fragment(optional);
        message = new Message(DEFAULT_NAME, DEFAULT_PROB, lifeline, lifeline);
    }

    @Test
    public void testAddLifeline() {
        sequenceDiagramsGroup.addLifeline(lifeline);

        assertEquals(
                lifeline,
                sequenceDiagramsGroup.getLifelines().getLifeline(lifeline.getName())
        );
    }
    
    @Test
    public void testAddOptional() throws SequenceDiagramRuleException {
        sequenceDiagramsGroup.addOptional(optional);

        assertEquals(
                optional,
                sequenceDiagramsGroup.getFragments().getOptional(optional.getName())
        );
    }

    @Test
    public void testAddSequenceDiagramEmpty() throws SequenceDiagramRuleException {
        sequenceDiagramsGroup.addSequenceDiagram(sequenceDiagram1);

        assertEquals(
                sequenceDiagram1,
                sequenceDiagramsGroup.getSequenceDiagram(sequenceDiagram1.getName())
        );
    }
    
    @Test
    public void testAddSequenceDiagramWithMessage() throws SequenceDiagramRuleException {
        sequenceDiagram1.addElement(message);
        sequenceDiagramsGroup.addSequenceDiagram(sequenceDiagram1);

        assertEquals(
                sequenceDiagram1,
                sequenceDiagramsGroup.getSequenceDiagram(sequenceDiagram1.getName())
        );
    }
    
    @Test
    public void testAddTwoSequenceDiagram() throws SequenceDiagramRuleException {
        sequenceDiagram1.addElement(message);
        sequenceDiagramsGroup.addSequenceDiagram(sequenceDiagram1);
        sequenceDiagramsGroup.addSequenceDiagram(sequenceDiagram2);

        assertEquals(
                sequenceDiagram1,
                sequenceDiagramsGroup.getSequenceDiagram(sequenceDiagram1.getName())
        );
        
        assertEquals(
                sequenceDiagram2,
                sequenceDiagramsGroup.getSequenceDiagram(sequenceDiagram2.getName())
        );
    }
}
