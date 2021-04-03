package uml.diagrams.sequence.sequencediagrams.fragments;

import uml.diagrams.sequence.BaseElement;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.sequencediagrams.ISequenceDiagramElement;

public class Fragment extends BaseElement implements ISequenceDiagramElement {

    public Fragment(String name) throws SequenceDiagramRuleException {
        super(name);
    }

    @Override
    public String toString() {
        return "<Fragment " + "name=\"" + name + "\" " + "/>";
    }
}
