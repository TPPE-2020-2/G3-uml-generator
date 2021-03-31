package uml.diagrams.sequence.message;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.message.Message;
import uml.diagrams.sequence.sequencediagram.SequenceDiagram;

public class MessageToStringTest {
	
    public static Collection<Object[]> messageParms() {
        return Arrays.asList(new Object[][] {
            {"name", 0.5f, 
            	"<Message name=\"name\" prob=\"0.5\" source=\"origem\" target=\"destino\" />"},
        });
    }
    
    @ParameterizedTest
    @MethodSource("messageParms")
    void testGetMessageToString(String name, Float prob, String expectedString) throws SequenceDiagramRuleException {
    	Lifeline source = new Lifeline("origem");
    	Lifeline target = new Lifeline("destino");
    	Message diagram = new Message(name, prob, source, target);

        assertEquals(expectedString, diagram.toString());
    }

}
