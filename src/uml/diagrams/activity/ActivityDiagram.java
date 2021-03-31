package uml.diagrams.activity;

import java.util.List;
import java.util.function.Predicate;

import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.BaseNode;
import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.entities.Transition;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityDiagram extends BaseNode {
	
	private static final int MIN_TRANSITION_VALUE = 2;
	
	private ActivityDiagramElements activityDiagramElements;
	private ActivityDiagramTransitions activityDiagramTransitions;
	
	public ActivityDiagram(String name) throws ActivityDiagramRuleException {
		super(name);
		this.activityDiagramElements = new ActivityDiagramElements();
		this.activityDiagramTransitions = new ActivityDiagramTransitions();
	}
	
	public <T extends BaseNode> void addNodeElement(T node) {
		if (node instanceof ActivityNode)
			this.activityDiagramElements.addActivityNode((ActivityNode) node);
		else if (node instanceof StartNode)
			this.activityDiagramElements.setStartNode((StartNode) node);
		else if (node instanceof DecisionNode)
			this.activityDiagramElements.addDecisionNode((DecisionNode) node);
		else if (node instanceof MergeNode)
			this.activityDiagramElements.addMergeNode((MergeNode) node);
		else
			this.activityDiagramElements.addFinalNode((FinalNode) node);
	}
	
	public void addTransition(Transition transition) throws ActivityDiagramRuleException {
		activityDiagramTransitions.addTransition(transition);
	}
	
	public <T extends BaseNode> void addTransition(String name, float prob, T source, T target) 
			throws ActivityDiagramRuleException {
		activityDiagramTransitions.addTransition(name, prob, source, target);
	}
	
	public ActivityDiagramElements getActivityDiagramElements() {
		return this.activityDiagramElements;
	}
	
	public ActivityDiagramTransitions getActivityDiagramTransitions() {
		return this.activityDiagramTransitions;
	}
	
	public void validateActivityDiagram() throws ActivityDiagramRuleException {
		activityDiagramElements.validateDiagramElements();
		
		List<Transition> transitions = activityDiagramTransitions.getTransitions();
		
		if (transitions.size() < MIN_TRANSITION_VALUE)
			throw new ActivityDiagramRuleException("É necessário no mínimo duas transições");
		
		// valida se o start node possui uma conexão
		validateStartNodeTransitionExistence(transitions);
	}
	
	private void validateStartNodeTransitionExistence(List<Transition> transitions) throws ActivityDiagramRuleException {
		StartNode node = activityDiagramElements.getStartNode();
		
		if (findTransitionCount(transitions, (elem) -> elem.getSource().getClass() == StartNode.class) == 0)
			throw new ActivityDiagramRuleException("Transição para o StartNode não encontrada!");
	}
	
	private long findTransitionCount(List<Transition> transitions, Predicate<Transition> filter) {
		return transitions
			.stream()
			.filter(filter)
			.count();
	}
}
