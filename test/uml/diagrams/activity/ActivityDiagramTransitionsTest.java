package uml.diagrams.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.diagramtransitions.TransitionTempHolder;
import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.entities.Transition;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityDiagramTransitionsTest {
	private ActivityDiagram activityDiagram;
	private final String DEFAULT_NAME = "default";
	
	private final static String NAME = "transition";
	private final static Float PROB = 0.5f;
	
	private static ActivityNode activityNode;
	private static DecisionNode decisionNode;
	private static MergeNode mergeNode;
	private static FinalNode finalNode;
	
	@BeforeAll
	public static void initialSetup() throws ActivityDiagramRuleException {
		activityNode = new ActivityNode("activity");
		decisionNode = new DecisionNode("decision");
		mergeNode = new MergeNode("merge");
		finalNode = new FinalNode("final");
	}
	
	
	@BeforeEach
	public void setup() throws ActivityDiagramRuleException {
		activityDiagram = new ActivityDiagram(DEFAULT_NAME);
	}

	public static Collection<Object[]> transitionsValues() throws ActivityDiagramRuleException {
		return Arrays.asList(new Object[][] {
			{ Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, activityNode, finalNode)), 1},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, activityNode, finalNode),
	        		new TransitionTempHolder(NAME + "2", PROB, mergeNode, finalNode)), 2},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, activityNode, mergeNode),
	        		new TransitionTempHolder(NAME + "2", PROB, new ActivityNode("activity2"), mergeNode),
	        		new TransitionTempHolder(NAME + "3", PROB, decisionNode, mergeNode)), 3},
		});
	}
	
	@ParameterizedTest
	@MethodSource("transitionsValues")
	void testGetActivityNodeName(List<TransitionTempHolder> transitions, int expectedTransitionsNumber)
			throws ActivityDiagramRuleException {
		for (TransitionTempHolder temp : transitions) {
			Transition tempTransition = new Transition();
			
			tempTransition.setName(temp.getName());
			tempTransition.setProb(temp.getProb());
			tempTransition.setSource(temp.getSource());
			tempTransition.setTarget(temp.getTarget());
			
			activityDiagram.addTransition(tempTransition);
		}

		assertEquals(expectedTransitionsNumber, activityDiagram.getActivityDiagramTransitions().getTransitions().size());
	}
	
	@ParameterizedTest
	@MethodSource("transitionsValues")
	void testGetActivityNodeNameOverLoad(List<TransitionTempHolder> transitions, int expectedTransitionsNumber) 
			throws ActivityDiagramRuleException {
		for (TransitionTempHolder temp : transitions) {			
			activityDiagram.addTransition(temp.getName(), temp.getProb(), temp.getSource(), temp.getTarget());
		}

		assertEquals(expectedTransitionsNumber, activityDiagram.getActivityDiagramTransitions().getTransitions().size());
	}
}
