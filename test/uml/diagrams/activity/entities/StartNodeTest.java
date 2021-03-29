package uml.diagrams.activity.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class StartNodeTest {
	private final String NAME = "startNode";
	private final String NAME_2 = "startNode2";
	
	private StartNode initialNode;
	
	@Test
	void testGetStartNodeName() throws ActivityDiagramRuleException {
		initialNode = new StartNode(NAME);

		assertEquals(NAME, initialNode.getName());
	}
	
	@Test
	void testSetStartNodeName() throws ActivityDiagramRuleException {
		initialNode = new StartNode(NAME);
		
		initialNode.setName(NAME_2);
		
		assertEquals(NAME_2, initialNode.getName());
	}
	
	@Test
	void testErrorWhenEmptyName() {
		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> new StartNode(""));
		
		assertEquals(exception.getMessage(), "O nome não pode ser vazio");
	}
	
	@Test
	void testErrorWhenEmptyNameOnSetter() throws ActivityDiagramRuleException {
		initialNode = new StartNode(NAME);
		
		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> initialNode.setName(""));
		
		assertEquals(exception.getMessage(), "O nome não pode ser vazio");
	}
	
	@Test
	void testErrorWhenNullNameOnSetter() throws ActivityDiagramRuleException {
		initialNode = new StartNode(NAME);
		
		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class,
				() -> initialNode.setName(null));
		
		assertEquals(exception.getMessage(), "O nome não pode ser vazio");
	}
}
