package uml.diagrams.activity;

import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

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
	private List<MergeNode> mergeNodeList = new ArrayList<MergeNode>();
	
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
	
	public void addMergeNode(MergeNode node) {
		this.mergeNodeList.add(node);
	}
	
	public List<MergeNode> getMergeNodes() {
		return mergeNodeList;
	}
	
	public MergeNode getMergeNode(String nodeName) {
		return findNode(mergeNodeList, elem -> elem.getName() == nodeName);
	}
	
	public void removeMergeNode(String nodeName) {
		MergeNode node = findNode(mergeNodeList, elem -> elem.getName() == nodeName);
		
		if (node != null) {
			mergeNodeList.removeIf(elem -> elem.getName() == nodeName);
		}
	}
	
	private boolean hasAtLeastOneActivityNode() {
		return activityNodesList.size() > 0;
	}
	
	private boolean hasAtLeastOneFinalNode() {
		return finalNodeList.size() > 0;
	}

	public void validateDiagramElements() throws ActivityDiagramRuleException {
		if (!hasAtLeastOneActivityNode()) 
			throw new ActivityDiagramRuleException("O diagrama precisa ter pelo menos um Activity!");
		
		if (!hasAtLeastOneFinalNode())
			throw new ActivityDiagramRuleException("O diagrama precisa ter pelo menos um FinalNode!");
		
		if (startNode == null)
			throw new ActivityDiagramRuleException("O diagrama precisa ter um StartNode!");
	}
	
	@Override
	public String toString() {
		String activityString = "";
		String mergeNodeString = "";
		String decisionNodeString = "";
		String finalNodeString = "";
		
		for (ActivityNode activityNode: activityNodesList)
			activityString += "\n" + activityNode.toString();
		
		for (DecisionNode decisionNode: decisionNodeList)
			decisionNodeString += "\n" + decisionNode.toString();
		
		for (MergeNode mergeNode: mergeNodeList)
			mergeNodeString += "\n" + mergeNode.toString();
		
		for (FinalNode finalNode: finalNodeList)
			finalNodeString += "\n" + finalNode.toString();
		
		return "<ActivityDiagramElements>" +
				"\n" + startNode.toString() + 
				activityString +
				decisionNodeString +
				mergeNodeString +
				finalNodeString +
			"\n" + "</ActivityDiagramElements>";
	}
}
