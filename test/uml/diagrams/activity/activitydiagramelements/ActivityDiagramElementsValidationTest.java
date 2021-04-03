package uml.diagrams.activity.activitydiagramelements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uml.diagrams.activity.ActivityDiagramElements;
import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityDiagramElementsValidationTest {
	private ActivityDiagramElements activityDiagramElements;
	private final static String NO_START_NODE_ERROR_MESSAGE = "O diagrama precisa ter um StartNode!";
	private final static String NO_ACTIVITY_NODE_ERROR_MESSAGE = "O diagrama precisa ter pelo menos um Activity!";
	private final static String NO_FINAL_NODE_ERROR_MESSAGE = "O diagrama precisa ter pelo menos um FinalNode!";
	
	@BeforeEach
	public void setup() {
		activityDiagramElements = new ActivityDiagramElements();
	}
	
	@Test
	public void testErrorWhenNoStartNode() throws ActivityDiagramRuleException {
		activityDiagramElements.addActivityNode(new ActivityNode("activity"));
		activityDiagramElements.addFinalNode(new FinalNode("final"));
		
		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class, 
				() -> activityDiagramElements.validateDiagramElements());
		
		assertEquals(NO_START_NODE_ERROR_MESSAGE, exception.getMessage());
	}
	
	@Test
	public void testErrorWhenStartNodeIsNull() throws ActivityDiagramRuleException {
		activityDiagramElements.setStartNode(null);
		activityDiagramElements.addActivityNode(new ActivityNode("activity"));
		activityDiagramElements.addFinalNode(new FinalNode("final"));
		
		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class, 
				() -> activityDiagramElements.validateDiagramElements());
		
		assertEquals(NO_START_NODE_ERROR_MESSAGE, exception.getMessage());
	}
	
	@Test
	public void testErrorWhenNoActivityNode() throws ActivityDiagramRuleException {
		activityDiagramElements.setStartNode(new StartNode("start"));
		activityDiagramElements.addFinalNode(new FinalNode("final"));
		
		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class, 
				() -> activityDiagramElements.validateDiagramElements());
		
		assertEquals(NO_ACTIVITY_NODE_ERROR_MESSAGE, exception.getMessage());
	}
	
	@Test
	public void testErrorWhenNoFInalNode() throws ActivityDiagramRuleException {
		activityDiagramElements.setStartNode(new StartNode("start"));
		activityDiagramElements.addActivityNode(new ActivityNode("activity"));
		
		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class, 
				() -> activityDiagramElements.validateDiagramElements());
		
		assertEquals(NO_FINAL_NODE_ERROR_MESSAGE, exception.getMessage());
	}
}
