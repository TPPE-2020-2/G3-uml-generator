package uml.diagrams.activity;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
	
	public <T extends BaseNode> ActivityDiagram addNodeElement(T node) {
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
		
		return this;
	}
	
	public ActivityDiagram addTransition(Transition transition) throws ActivityDiagramRuleException {
		activityDiagramTransitions.addTransition(transition);
		
		return this;
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
		validateNodesTransitions(transitions, ActivityNode.class);
		validateNodesTransitions(transitions, DecisionNode.class);
		validateNodesTransitions(transitions, MergeNode.class);
		validadeFinalNodesTransitions(transitions);
	}
	
	private void validadeFinalNodesTransitions(List<Transition> transitions) throws ActivityDiagramRuleException {
		List<Transition> finalNodeTransitions = filterTransitionsByPredicate(transitions, 
				(elem) -> elem.getTarget().getClass() == FinalNode.class);
		
		for (FinalNode tempFinalNode: activityDiagramElements.getFinalNodes()) {
			if (findTransitionByPredicate(finalNodeTransitions, elem -> elem.getTarget().getName() == tempFinalNode.getName()) == null)
				throw new ActivityDiagramRuleException("O final node <> não possui uma transição para ele!".replace("<>", tempFinalNode.getName()));
		}
		
	}

	@SuppressWarnings("rawtypes")
    private void validateNodesTransitions(List<Transition> transitions, Class typeClass) throws ActivityDiagramRuleException {
		List<Transition> sourceTransitions = filterTransitionsByPredicate(transitions, 
					(elem) -> elem.getSource().getClass() == typeClass);
		
		List<Transition> targetTransitions = filterTransitionsByPredicate(transitions, 
				(elem) -> elem.getTarget().getClass() == typeClass);
		
		if (typeClass == ActivityNode.class)
			validateFowardAndBackwardNode(
					this.activityDiagramElements.getActivityNodes(), 
					sourceTransitions, targetTransitions, typeClass);
		else if (typeClass == DecisionNode.class)
			validateFowardAndBackwardNode(
					this.activityDiagramElements.getDecisionNodes(), 
					sourceTransitions, targetTransitions, typeClass);
		else if (typeClass == MergeNode.class)
			validateFowardAndBackwardNode(
					this.activityDiagramElements.getMergeNodes(), 
					sourceTransitions, targetTransitions, typeClass);
	}
	
	@SuppressWarnings("rawtypes")
    private <T extends BaseNode> void validateFowardAndBackwardNode(
			List<T> nodes, List<Transition> activitySourceTransitions, List<Transition> activityTargetTransitions, Class className) 
				throws ActivityDiagramRuleException {
		for (T tempNode: nodes) {
			if (findTransitionByPredicate(activitySourceTransitions, elem -> elem.getSource().getName() == tempNode.getName()) == null)
				throw new ActivityDiagramRuleException("<1> <2> não está ligado à nenhum elemento!"
						.replace("<1>", className.getSimpleName())
						.replace("<2>", tempNode.getName()));
			
			if (findTransitionByPredicate(activityTargetTransitions, elem -> elem.getTarget().getName() == tempNode.getName()) == null)
				throw new ActivityDiagramRuleException("<1> <2> não é ligado por nenhum elemento pai!"
						.replace("<1>", className.getSimpleName())
						.replace("<2>", tempNode.getName()));
		}
	}
	
	private void validateStartNodeTransitionExistence(List<Transition> transitions) throws ActivityDiagramRuleException {	
		if (countTransitionsByPredicate(transitions, (elem) -> elem.getSource().getClass() == StartNode.class) == 0)
			throw new ActivityDiagramRuleException("Transição para o StartNode não encontrada!");
	}
	
	private Transition findTransitionByPredicate(List<Transition> transitions, Predicate<Transition> filter) {
		return transitions
				.stream()
				.filter(filter)
				.findFirst()
				.orElse(null);
	}
	
	private List<Transition> filterTransitionsByPredicate(List<Transition> transitions, Predicate<Transition> filter) {
		return transitions
				.stream()
				.filter(filter)
				.collect(Collectors.toList());
	}
	
	private long countTransitionsByPredicate(List<Transition> transitions, Predicate<Transition> filter) {
		return transitions
			.stream()
			.filter(filter)
			.count();
	}
	
	@Override
	public String toString() {
		return "<ActivityDiagram name=\"" + name + "\">" +
				activityDiagramElements.toString() +
				activityDiagramTransitions.toString() +
			"</ActivityDiagram>";
	}
	
	
}
