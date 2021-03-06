package uml.diagrams.activity.entities;


import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public abstract class BaseNode {
	protected String name;
	
	public BaseNode(String name) throws ActivityDiagramRuleException {
		validateName(name);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws ActivityDiagramRuleException {
		validateName(name);
		this.name = name;
	}
	
	private void validateName(String name) throws ActivityDiagramRuleException {
		if (name == null || name.trim().isEmpty())
			throw new ActivityDiagramRuleException("O nome não pode ser vazio");
	}
	
	@Override
	public String toString() {
		return  "<" + this.getClass().getSimpleName() + " name=\"" + this.getName() + "\" />";
	}
}
