package uml.diagrams.sequence.sequencediagram;

import uml.diagrams.sequence.BaseElement;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;

public class SequenceDiagram extends BaseElement {
	private final String INVALID_GUARD_CONDITION_VALUE_ERROR_MESSAGE = "A condição de guarda não pode ser vazia ou nula";
	
	private Boolean guardCondition;
	

	public SequenceDiagram(String name, Boolean guardCondition) throws SequenceDiagramRuleException {
		super(name);
		this.guardCondition = guardCondition;
	}

	public Boolean getGuardCondition() {
		return guardCondition;
	}

	public void setGuardCondition(Boolean guardCondition) throws SequenceDiagramRuleException {
		if(guardCondition == null)
			throw new SequenceDiagramRuleException(INVALID_GUARD_CONDITION_VALUE_ERROR_MESSAGE);
		this.guardCondition = guardCondition;
	}
	
	@Override
	public String toString() {
		String representantion = "<SequenceDiagram " + 
				"name=\"" + this.getName() + "\" " + 
				"guardCondition=\"" + this.getGuardCondition() + "\" " +
				">";
		return representantion;
	}
}
