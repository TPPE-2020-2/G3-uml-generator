package uml.diagrams.sequence.fragments;

import uml.diagrams.sequence.BaseElement;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;

public class Optional extends BaseElement {
	private SequenceDiagram representedBy;
	
	public Optional(String name, SequenceDiagram representedBy) throws SequenceDiagramRuleException {
		super(name);
		this.representedBy = representedBy;
	}
	
	public void setRepresentedBy(SequenceDiagram representedBy) {
		this.representedBy = representedBy;
	}
	
	public SequenceDiagram getRepresentedBy() {
		return representedBy;
	}
	
	@Override
	public String toString() {
		return "<Optional " +
			"name=\"" + name + "\" " + 
			"representedBy=\"" + representedBy.getName() + "\"/>";
	}
}
