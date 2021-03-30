package uml.diagrams.sequence.lifelines;

import uml.diagrams.sequence.BaseElement;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;

public class Lifeline extends BaseElement {

    public Lifeline(String name) throws SequenceDiagramRuleException {
        super(name);
    }
    
    @Override
    public String toString() {
        return "<Lifeline name=\"" + this.name + "\" />";
    }
}
