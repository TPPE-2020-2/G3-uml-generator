package uml.diagrams.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import uml.diagrams.sequence.SequenceDiagramsGroup;
import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.MessageFormatException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.fragments.Optional;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;
import uml.diagrams.sequence.sequencediagrams.fragments.Fragment;
import uml.diagrams.sequence.sequencediagrams.messages.Message;

public class SequenceDiagramsGroupXMLTest {
    private final static String DEFAULT_NAME = "default";
    private final static Float DEFAULT_PROB = 0.5f;

    private SequenceDiagramsGroup sequenceDiagramsGroup;

    @BeforeEach
    public void setup() {
        sequenceDiagramsGroup = new SequenceDiagramsGroup();
    }

    @Test
    public void testGenerateXML() throws SequenceDiagramRuleException,
        EmptyOptionalFragmentException, MessageFormatException,
        UnsupportedEncodingException, ParserConfigurationException,
        SAXException, IOException, TransformerException {
        
        SequenceDiagram sequenceDiagram1 = new SequenceDiagram("diagram1", true);
        SequenceDiagram sequenceDiagram2 = new SequenceDiagram("diagram2", false);
        Lifeline lifeline1 = new Lifeline(DEFAULT_NAME);
        Lifeline lifeline2 = new Lifeline(DEFAULT_NAME);
        Message message1 = new Message("message1", DEFAULT_PROB, lifeline1, lifeline1);
        Message message2 = new Message("message2", DEFAULT_PROB, lifeline2, lifeline1);
        Message message3 = new Message("message3", DEFAULT_PROB, lifeline2, lifeline1);
        Optional optional1 = new Optional("fragment1", sequenceDiagram1);
        Optional optional2 = new Optional("fragment3", sequenceDiagram2);
        Optional optional3 = new Optional("fragment3", sequenceDiagram2);
        Fragment fragment1 = new Fragment(optional1);
        Fragment fragment2 = new Fragment(optional2);
        Fragment fragment3 = new Fragment(optional3);
        
        sequenceDiagram1.addElement(message1);
        sequenceDiagram1.addElement(fragment1);
        sequenceDiagram1.addElement(message2);
        sequenceDiagram1.addElement(fragment2);

        sequenceDiagram2.addElement(fragment3);
        sequenceDiagram2.addElement(message3);
        
        sequenceDiagramsGroup.addLifeline(lifeline1);
        sequenceDiagramsGroup.addOptional(optional1);
        sequenceDiagramsGroup.addOptional(optional3);
        sequenceDiagramsGroup.addSequenceDiagram(sequenceDiagram1);
        sequenceDiagramsGroup.addSequenceDiagram(sequenceDiagram2);

        String path = System.getProperty("user.dir") + "/teste_sequence_diagrams.xml";
        
        XMLUtils.generateXML(sequenceDiagramsGroup.toString(), path);
        
        File file = new File(path);

        boolean fileExists = file.exists() && file.isFile();
        
        file.delete();
        
        assertTrue(fileExists);
    }
}
