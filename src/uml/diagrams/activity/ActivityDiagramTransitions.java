package uml.diagrams.activity;

import java.util.ArrayList;
import java.util.List;

import uml.diagrams.activity.entities.BaseNode;
import uml.diagrams.activity.entities.Transition;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityDiagramTransitions {
	private List<Transition> transitionList;
	
	public ActivityDiagramTransitions() {
		transitionList = new ArrayList<Transition>();
	}
	public void addTransition(String name, float prob, BaseNode source, BaseNode target) throws ActivityDiagramRuleException {
		Transition transition = new Transition();
		
		transition.setName(name);
		transition.setProb(prob);
		transition.setSource(source);
		transition.setTarget(target);
		
		transitionList.add(transition);
	}
}
