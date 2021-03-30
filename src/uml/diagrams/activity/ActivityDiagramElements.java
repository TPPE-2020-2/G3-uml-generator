package uml.diagrams.activity;

import uml.diagrams.activity.entities.StartNode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.FinalNode;

public class ActivityDiagramElements {
	
	private StartNode startNode;
	private List<ActivityNode> activityNodesList = new ArrayList<ActivityNode>();
	private List<FinalNode> finalNodeList = new ArrayList<FinalNode>();
	
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
	
	public ActivityNode getActivityNode(String nodeName) {
		return findActivityNode(elem -> elem.getName() == nodeName);
	}
	
	public void removeActivityNode(String nodeName) {
		ActivityNode node = findActivityNode(elem -> elem.getName() == nodeName);
		
		if (node != null) {
			activityNodesList.removeIf(elem -> elem.getName() == nodeName);
		}
	}
	
	private ActivityNode findActivityNode(Predicate<ActivityNode> filter) {
		return activityNodesList
			.stream()
			.filter(filter)
			.findFirst()
			.orElse(null);
	}
	
	public void addFinalNode(FinalNode node) {
		this.finalNodeList.add(node);
	}
	
	public List<FinalNode> getFinalNodes() {
		return finalNodeList;
	}
	
	
	
	private boolean hasAlLeastOneActivityNode() {
		return activityNodesList.size() > 0;
	}
}
