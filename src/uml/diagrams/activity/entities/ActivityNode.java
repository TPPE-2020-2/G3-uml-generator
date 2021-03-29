package uml.diagrams.activity.entities;


import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public abstract class ActivityNode {
	protected String name;
	
	public ActivityNode(String name) throws ActivityDiagramRuleException {
		if (name != null && !name.trim().isEmpty())
			this.name = name;
		else
			throw new ActivityDiagramRuleException("O nome não pode ser vazio");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
