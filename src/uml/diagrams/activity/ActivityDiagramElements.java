package uml.diagrams.activity;

import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityDiagramElements {
	private StartNode startNode;

	public StartNode getStartNode() throws ActivityDiagramRuleException {
		return new StartNode("defaultName");
	}

	public void setStartNode(StartNode startNode) {
		this.startNode = startNode;
	}
	
	
}
