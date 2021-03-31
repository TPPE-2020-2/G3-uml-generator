package uml.diagrams.activity;

import java.util.ArrayList;
import java.util.Collection;
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

public class ActivityDiagramTransitions {
	private List<Transition> transitionList;
	
	public ActivityDiagramTransitions() {
		transitionList = new ArrayList<Transition>();
	}
	
	public List<Transition> getTransitions() {
		return transitionList;
	}
	
	public void addTransition(String name, float prob, BaseNode source, BaseNode target) throws ActivityDiagramRuleException {
		Transition transition = new Transition();
		
		transition.setName(name);
		transition.setProb(prob);
		transition.setSource(source);
		transition.setTarget(target);
		
		validateTransitionInsert(source, target);
		
		transitionList.add(transition);
	}
	
	public void removeTransition(String name) {
		transitionList.removeIf(elem -> elem.getName() == name);
	}
	
	private void validateMultipleStartNodes(BaseNode source, BaseNode target) throws ActivityDiagramRuleException {
		Transition currentStartTransition = findTransition(elem -> elem.getSource().getClass() == StartNode.class);
		
		if (currentStartTransition != null) 
			throw new ActivityDiagramRuleException("Um diagrama de atividades deve ter somente um start node!");
		
		if (target.getClass() != ActivityNode.class)
			throw new ActivityDiagramRuleException("Um diagrama de atividades deve se conectar somente à um activity!");
	}
	
	private void validateActivityTransitions(BaseNode source, BaseNode target) throws ActivityDiagramRuleException {
		Transition currentActivityTransition = findTransition(
				elem -> elem.getSource().getClass() == ActivityNode.class && elem.getSource().getName() == source.getName());
		
		if (currentActivityTransition != null)
			throw new ActivityDiagramRuleException("Não é possível ter mais de uma transição para um mesmo activity node");
	}
	
	private void validateMultipleDecisionNodesTarget(BaseNode source, BaseNode target) throws ActivityDiagramRuleException {
		Transition currentDecisionTransition = findTransition(
				elem -> elem.getTarget().getClass() == DecisionNode.class && elem.getTarget().getName() == target.getName());
		
		if (currentDecisionTransition != null)
			throw new ActivityDiagramRuleException("Não é possível um mesmo decision node ser target de múltiplos elements; Use um merge node");
	}
	
	private void validateMergeTransitions(BaseNode source, BaseNode target) throws ActivityDiagramRuleException {
		Transition currentMergeTransition = findTransition(
				elem -> elem.getSource().getClass() == MergeNode.class && elem.getSource().getName() == source.getName());
		
		if (currentMergeTransition != null)
			throw new ActivityDiagramRuleException("Não é possível um mesmo mrege node ser possuir multiplos targes; Use um decision node");
	}
	
	private void validateTransitionInsert(BaseNode source, BaseNode target) throws ActivityDiagramRuleException {
		if (source == null || target == null)
			throw new ActivityDiagramRuleException("Não é possível inserir valores nulos!");
		
		if (target.getClass() == StartNode.class)
			throw new ActivityDiagramRuleException("Um start node não pode estar numa transição como target");
		if (target.getClass() == DecisionNode.class)
			validateMultipleDecisionNodesTarget(source,target);
		if (source.getClass() == StartNode.class)
			validateMultipleStartNodes(source, target);
		
		if (source.getClass() == ActivityNode.class)
			validateActivityTransitions(source, target);
		if (source.getClass() == MergeNode.class)
			validateMergeTransitions(source, target);
		if (source.getClass() == FinalNode.class)
			throw new ActivityDiagramRuleException("Um final node não pode se conectar com outro elemento como um source");
	}

	private Transition findTransition(Predicate<Transition> filter) {
		return transitionList
			.stream()
			.filter(filter)
			.findFirst()
			.orElse(null);
	}
	
	@Override
	public String toString() {
		String transitionsString = "";
		
		for (Transition temp : transitionList)
			transitionsString += "\n" + temp.toString();
		
		return "<ActivityDiagramTransitions>" +
					transitionsString +
				"\n" + "</ActivityDiagramTransitions>";
	}
}
