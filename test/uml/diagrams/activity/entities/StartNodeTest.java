package uml.diagrams.activity.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StartNodeTest {
	private final String NAME = "startNode";
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

		StartNode initialNode = new StartNode(NAME);

		assertEquals(NAME, initialNode.getName());
	}
	
	@Test
	void testSetNodeName() {
		
		initialNode.setName(NAME);
		
		assertEquals(NAME, initialNode.getName());
	}
}
