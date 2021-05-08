package uml.diagrams.activity;

import java.util.ArrayList;
import java.util.List;

import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.entities.StartNode;

public class ActivityDiagramElementsToStringGenerator {
	private String activityString = "";
	private String mergeNodeString = "";
	private String decisionNodeString = "";
	private String finalNodeString = "";
	
	private StartNode startNode;
	private List<ActivityNode> activityNodesList = new ArrayList<ActivityNode>();
	private List<FinalNode> finalNodeList = new ArrayList<FinalNode>();
	private List<DecisionNode> decisionNodeList = new ArrayList<DecisionNode>();
	private List<MergeNode> mergeNodeList = new ArrayList<MergeNode>();
	
	public ActivityDiagramElementsToStringGenerator(ActivityDiagramElements activityDiagramElements) {
		this.startNode = activityDiagramElements.getStartNode();
		this.activityNodesList = activityDiagramElements.getActivityNodes();
		this.finalNodeList = activityDiagramElements.getFinalNodes();
		this.decisionNodeList = activityDiagramElements.getDecisionNodes();
		this.mergeNodeList = activityDiagramElements.getMergeNodes();
	}
	
	public String compute() {
		for (ActivityNode activityNode: activityNodesList)
			activityString += activityNode.toString();
		
		for (DecisionNode decisionNode: decisionNodeList)
			decisionNodeString += decisionNode.toString();
		
		for (MergeNode mergeNode: mergeNodeList)
			mergeNodeString += mergeNode.toString();
		
		for (FinalNode finalNode: finalNodeList)
			finalNodeString += finalNode.toString();
		
		return "<ActivityDiagramElements>" +
				startNode.toString() + 
				activityString +
				decisionNodeString +
				mergeNodeString +
				finalNodeString +
			 "</ActivityDiagramElements>";
	}
}
