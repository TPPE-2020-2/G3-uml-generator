package uml.diagrams.activity.entities;

import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class Transition {
	private final String INVALID_NAME_ERROR_MESSAGE = "O nome não pode ser vazio ou nulo";
	private final String INVALID_PROB_VALUE_ERROR_MESSAGE = "A prob deve ser um valor real entre 0 e 1";
	
	private String name;
	private Float prob;
	private BaseNode source;
	private BaseNode target;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) throws ActivityDiagramRuleException {
		if (name == null || name.trim().isEmpty())
			throw new ActivityDiagramRuleException(INVALID_NAME_ERROR_MESSAGE);
		this.name = name;
	}

	public Float getProb() {
		return prob;
	}

	public void setProb(Float prob) throws ActivityDiagramRuleException {
		if (prob > 1.0f || prob < 0.0f)
			throw new ActivityDiagramRuleException(INVALID_PROB_VALUE_ERROR_MESSAGE);
			
		this.prob = prob;
	}
	
	public BaseNode getSource() {
		return source;
	}
	
	public void setSource(BaseNode source) {
		this.source = source;
	}

	public BaseNode getTarget() {
		return target;
	}

	public void setTarget(BaseNode target) {
		this.target = target;
	}
	
	@Override
	public String toString() {
		return "<Transition " + 
				"name=\"" + this.getName() + "\" " + 
				"prob=\"" + this.prob + "\" " + 
				"source=\"" + this.getSource().getName() + "\" " +
				"target=\"" + this.getTarget().getName() + "\" " +
				"/>";				
	}
}
