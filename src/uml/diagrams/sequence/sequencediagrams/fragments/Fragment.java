package uml.diagrams.sequence.sequencediagrams.fragments;

import uml.diagrams.sequence.BaseElement;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;

public class Fragment extends BaseElement {

    public Fragment(String name) throws SequenceDiagramRuleException {
        super(name);
    }

    @Override
    public String toString() {
        return "<Fragment " + "name=\"" + name + "\" " + "/>";
    }
}
