package uml.diagrams.sequence.messages;

import uml.diagrams.sequence.exceptions.MessageFormatException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.lifelines.Lifeline;

public class Reply extends Message {

    public Reply(String name, Float prob, Lifeline source, Lifeline target)
            throws MessageFormatException, SequenceDiagramRuleException {
        super(name, prob, source, target);
    }
    
    @Override
    public String toString() {
        return "<Reply " + 
                "name=\"" + this.getName() + "\" " + 
                "prob=\"" + this.prob + "\" " + 
                "source=\"" + this.getSource().getName() + "\" " +
                "target=\"" + this.getTarget().getName() + "\" " +
                "/>";
    }
}
