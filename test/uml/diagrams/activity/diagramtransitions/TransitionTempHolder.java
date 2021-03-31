package uml.diagrams.activity.diagramtransitions;

import uml.diagrams.activity.entities.BaseNode;

public class TransitionTempHolder {
	public TransitionTempHolder(String name, float prob, BaseNode source, BaseNode target) {
		super();
		this.source = source;
		this.target = target;
		this.prob = prob;
		this.name = name;
	}
	
	private BaseNode source;
	private BaseNode target;
	private float prob;
	private String name;
	
	public BaseNode getSource() {
		return source;
	}
	
	public BaseNode getTarget() {
		return target;
	}
	
	public float getProb() {
		return prob;
	}
	
	public String getName() {
		return name;
	}	
}
