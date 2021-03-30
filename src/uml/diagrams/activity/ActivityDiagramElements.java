package uml.diagrams.activity;

import uml.diagrams.activity.entities.StartNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.BaseNode;
import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.entities.MergeNode;

public class ActivityDiagramElements {
	
	private StartNode startNode;
	private List<ActivityNode> activityNodesList = new ArrayList<ActivityNode>();
	private List<FinalNode> finalNodeList = new ArrayList<FinalNode>();
	private List<DecisionNode> decisionNodeList = new ArrayList<DecisionNode>();
	
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
		return findNode(activityNodesList, elem -> elem.getName() == nodeName);
	}
	
	public void removeActivityNode(String nodeName) {
		ActivityNode node = findNode(activityNodesList, elem -> elem.getName() == nodeName);
		
		if (node != null) {
			activityNodesList.removeIf(elem -> elem.getName() == nodeName);
		}
	}
	
	private <T> T findNode(Collection<T> collection, Predicate<T> filter) {
		return collection
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
	
	public FinalNode getFinalNode(String nodeName) {
		return findNode(finalNodeList, elem -> elem.getName() == nodeName);
	}
	
	public void removeFinalNode(String nodeName) {
		FinalNode node = findNode(finalNodeList, elem -> elem.getName() == nodeName);
		
		if (node != null) {
			finalNodeList.removeIf(elem -> elem.getName() == nodeName);
		}
	}
	
	public void addDecisionNode(DecisionNode node) {
		this.decisionNodeList.add(node);
	}
	
	public List<DecisionNode> getDecisionNodes() {
		return decisionNodeList;
	}
	
	public DecisionNode getDecisionNode(String nodeName) {
		return findNode(decisionNodeList, elem -> elem.getName() == nodeName);
	}
	
	public void removeDecisionNode(String nodeName) {
		DecisionNode node = findNode(decisionNodeList, elem -> elem.getName() == nodeName);
		
		if (node != null) {
			decisionNodeList.removeIf(elem -> elem.getName() == nodeName);
		}
	}
	
	private boolean hasAlLeastOneActivityNode() {
		return activityNodesList.size() > 0;
	}
}
