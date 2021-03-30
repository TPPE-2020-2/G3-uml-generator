package uml.diagrams.activity.entities;

import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class Transition {
	private final String INVALID_NAME_ERROR_MESSAGE = "O nome não pode ser vazio ou nulo";
	private String name;
	
	public void setName(String name) throws ActivityDiagramRuleException {
		validateNullOrEmpty(name, INVALID_NAME_ERROR_MESSAGE);
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	private void validateNullOrEmpty(String value, String errorMessage) 
			throws ActivityDiagramRuleException {
		if (value == null || value.trim().isEmpty())
			throw new ActivityDiagramRuleException(errorMessage);
	}
}
