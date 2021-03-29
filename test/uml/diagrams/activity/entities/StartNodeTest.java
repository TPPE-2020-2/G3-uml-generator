package uml.diagrams.activity.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uml.diagrams.*;

class StartNodeTest {
	private StartNode initialNode;

	@BeforeEach
	void setUp() throws Exception {
		initialNode = new StartNode();
	}

	@Test
	void testClassInstance() {
		assertNotNull(initialNode);
	}
	
	@Test
	void testClassInstanceWithName() {
		String name = "startNode";
		
		StartNode initialNode = new StartNode(name);
		
		assertNotNull(initialNode);
		assertEquals(name, initialNode.getName());
	}
	
	@Test
	void testSetClassName() {
		String name = "startNode";
		
		StartNode initialNode = new StartNode(name);
		
		assertNotNull(initialNode);
		assertEquals(name, initialNode.getName());
	}
}
