package uml.diagrams.activity;

import uml.diagrams.activity.entities.StartNode;

import java.util.ArrayList;
import java.util.List;

import uml.diagrams.activity.entities.ActivityNode;

public class ActivityDiagramElements {
	private StartNode startNode;
	private List<ActivityNode> activityNodesList = new ArrayList<ActivityNode>();

	public StartNode getStartNode() {
		return startNode;
	}

	public void setStartNode(StartNode startNode) {
		this.startNode = startNode;
	}
	
	public void addActivityNode(ActivityNode node) {
		this.activityNodesList.add(node);
	}
	
	public List<ActivityNode> getActivityNodes() {
		return activityNodesList;
	}
}
