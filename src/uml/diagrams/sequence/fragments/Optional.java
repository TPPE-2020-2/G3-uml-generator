package uml.diagrams.sequence.fragments;

import uml.diagrams.sequence.BaseElement;
import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;

public class Optional extends BaseElement {
    
    static final String REPRESENTED_BY_ERROR = "É necessário informar o diagrama de sequência associado";
    
	private SequenceDiagram representedBy;
	
	public Optional(String name, SequenceDiagram representedBy)
	        throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
		super(name);
		setRepresentedBy(representedBy);
	}
	
	public void setRepresentedBy(SequenceDiagram representedBy)
	        throws EmptyOptionalFragmentException {
	    if (representedBy == null) {
	        throw new EmptyOptionalFragmentException(REPRESENTED_BY_ERROR);
	    }
	    
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
