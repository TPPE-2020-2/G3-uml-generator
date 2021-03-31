package uml.diagrams.activity.diagramtransitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.ActivityDiagramTransitions;
import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityDiagramTransitionsToStringTest {
	
	private ActivityDiagramTransitions diagramTransitions;
	
	private final static String NAME = "transition";
	private final static Float PROB = 0.5f;
	
	private static StartNode startNode;
	private static ActivityNode activityNode;
	private static DecisionNode decisionNode;
	private static MergeNode mergeNode;
	private static FinalNode finalNode;
	
	@BeforeAll
	public static void initialSetup() throws ActivityDiagramRuleException {
		startNode = new StartNode("start");
		activityNode = new ActivityNode("activity");
		decisionNode = new DecisionNode("decision");
		mergeNode = new MergeNode("merge");
		finalNode = new FinalNode("final");
	}
	
	@BeforeEach
	public void setup() {
		diagramTransitions = new ActivityDiagramTransitions();
	}
	
	public static Collection validScenarios() throws ActivityDiagramRuleException {
		return Arrays.asList(new Object[][] {
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, activityNode, finalNode)), 
	        	"<ActivityDiagramTransitions>" +
	        			"<Transition name=\"" +NAME +"\" " + "prob=\"" + PROB + "\" " + "source=\"" +  
	        				activityNode.getName() + "\" target=\"" + finalNode.getName() + "\" />" +
	        	 "</ActivityDiagramTransitions>"},
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, activityNode, finalNode),
	        		new TransitionTempHolder(NAME + "2", PROB, mergeNode, finalNode)),
	        		"<ActivityDiagramTransitions>" +
	        			"<Transition name=\"" +NAME +"\" " + "prob=\"" + PROB + "\" " + "source=\"" +
        					activityNode.getName() + "\" target=\"" + finalNode.getName() + "\" />" +
    					"<Transition name=\"" +NAME +"2\" " + "prob=\"" + PROB + "\" " + "source=\"" +
    						mergeNode.getName() + "\" target=\"" + finalNode.getName() + "\" />" +
		        	"</ActivityDiagramTransitions>" 
	        },
	        { Arrays.asList(
	        		new TransitionTempHolder(NAME, PROB, activityNode, mergeNode),
	        		new TransitionTempHolder(NAME + "2", PROB, new ActivityNode("activity2"), mergeNode),
	        		new TransitionTempHolder(NAME + "3", PROB, decisionNode, mergeNode)),
	        	"<ActivityDiagramTransitions>" + 
        			"<Transition name=\"" +NAME +"\" " + "prob=\"" + PROB + "\" " + "source=\"" +
    					activityNode.getName() + "\" target=\"" + mergeNode.getName() + "\" />" +
					"<Transition name=\"" +NAME +"2\" " + "prob=\"" + PROB + "\" " + "source=\"" +
						 "activity2\" target=\"" + mergeNode.getName() + "\" />" + 
					"<Transition name=\"" +NAME +"3\" " + "prob=\"" + PROB + "\" " + "source=\"" +
						decisionNode.getName() + "\" target=\"" + mergeNode.getName() + "\" />" +
	        	"</ActivityDiagramTransitions>"
	        },
		});
	}
	
	@ParameterizedTest
	@MethodSource("validScenarios")
	public void testToStringTransitions(List<TransitionTempHolder> values, String expectedXMLRepresentation) throws ActivityDiagramRuleException {
		for (TransitionTempHolder transition : values) 
			diagramTransitions.addTransition(transition.getName(), transition.getProb(), transition.getSource(), transition.getTarget());
		
		
		assertEquals(expectedXMLRepresentation, diagramTransitions.toString());
	}
}
