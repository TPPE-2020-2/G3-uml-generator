package uml.diagrams.activity.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class FinalNodeTest {
	private FinalNode finalNode;
	
	private final String NAME = "endNode";
	
	@Test
	void testGetFinalNodeName() throws ActivityDiagramRuleException {
		finalNode = new FinalNode(NAME);
		
		assertEquals(NAME, finalNode.getName());
	}

}
