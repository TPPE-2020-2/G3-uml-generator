package uml.diagrams.sequence;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;

public class SequenceDiagrams {
	private final String INVALID_NAME_ERROR_MESSAGE = "O nome não pode ser vazio ou nulo";
	private final String INVALID_GUARD_CONDITION_VALUE_ERROR_MESSAGE = "A condição de guarda não pode ser vazia ou nula";
	
	private String name;
	private Boolean guardCondition;
	

	public SequenceDiagrams(String name, Boolean guardCondition) {
		super();
		this.name = name;
		this.guardCondition = guardCondition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws SequenceDiagramRuleException {
		if (name == null || name.trim().isEmpty())
			throw new SequenceDiagramRuleException(INVALID_NAME_ERROR_MESSAGE);
		this.name = name;
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
		return "<SequenceDiagram " + 
				"name=\"" + this.getName() + "\" " + 
				"guardCondition=\"" + this.getGuardCondition() + "\" " +
				"/>";				
	}
}
