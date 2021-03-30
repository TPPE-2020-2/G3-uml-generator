package uml.diagrams.activity.entities;

import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityNode extends BaseNode {
	
	public ActivityNode(String name) throws ActivityDiagramRuleException {
		super(name);
	}
	
	@Override
	public String toString() {
		return  "<Activity name=\"" + this.getName() + "\" />";
	}
}
