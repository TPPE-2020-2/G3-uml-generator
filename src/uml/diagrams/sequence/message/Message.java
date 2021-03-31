package uml.diagrams.sequence.message;

import uml.diagrams.sequence.exceptions.*;
import uml.diagrams.sequence.lifelines.Lifeline;

public class Message {
	private final String INVALID_NAME_ERROR_MESSAGE = "O nome n�o pode ser vazio ou nulo";
	private final String INVALID_PROB_VALUE_ERROR_MESSAGE = "A prob deve ser um valor real entre 0 e 1";
	
	private String name;
	private Float prob = 0.0f;
	private Lifeline source;
	private Lifeline target;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) throws SequenceDiagramRuleException {
		if (name == null || name.trim().isEmpty())
			throw new SequenceDiagramRuleException(INVALID_NAME_ERROR_MESSAGE);
		this.name = name;
	}
	
	public Message(String name, Float prob, Lifeline source, Lifeline target) {
		super();
		this.name = name;
		this.prob = prob;
		this.source = source;
		this.target = target;
	}

	public Float getProb() {
		return prob;
	}
	public void setProb(Float prob) throws SequenceDiagramRuleException {
		if (prob > 1.0f || prob < 0.0f)
			throw new SequenceDiagramRuleException(INVALID_PROB_VALUE_ERROR_MESSAGE);
			
		this.prob = prob;
	}
	
	public Lifeline getSource() {
		return source;
	}
	
	public void setSource(Lifeline source) {
		this.source = source;
	}
	
	public Lifeline getTarget() {
		return target;
	}
	
	public void setTarget(Lifeline target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "<Message " + 
				"name=\"" + this.getName() + "\" " + 
				"prob=\"" + this.prob + "\" " + 
				"source=\"" + this.getSource().getName() + "\" " +
				"target=\"" + this.getTarget().getName() + "\" " +
				"/>";				
	}
	
	

}
