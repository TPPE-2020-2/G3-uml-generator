package uml.diagrams.activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.BaseNode;
import uml.diagrams.activity.entities.StartNode;
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
		
		validateTransitionInsert(source, target);
		
		transitionList.add(transition);
	}
	
	private void validateMultipleStartNodes(BaseNode source, BaseNode target) throws ActivityDiagramRuleException {
		Transition currentStartTransition = findTransition(elem -> elem.getSource().getClass() == StartNode.class);
		
		if (currentStartTransition != null) 
			throw new ActivityDiagramRuleException("Um diagrama de atividades deve ter somente um start node!");
		
		if (target.getClass() != ActivityNode.class)
			throw new ActivityDiagramRuleException("Um diagrama de atividades deve se conectar somente à um activity!");
	}
	
	private void validateTransitionInsert(BaseNode source, BaseNode target) throws ActivityDiagramRuleException {
		
		if (source.getClass() == StartNode.class)
			validateMultipleStartNodes(source, target);
	}
	
	private Transition findTransition(Predicate<Transition> filter) {
		return transitionList
			.stream()
			.filter(filter)
			.findFirst()
			.orElse(null);
	}
}
