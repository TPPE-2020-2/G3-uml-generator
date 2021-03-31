package uml.diagrams.sequence;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;

public class SequenceDiagrams extends BaseElement {

	public SequenceDiagrams(String name) throws SequenceDiagramRuleException {
		super(name);
		
	}
	
    @Override
    public String toString() {
        return "<SequenceDiagrams name=\"" + this.name + "\" />";
    }

}
