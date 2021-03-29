package uml.diagrams.activity.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FinalNodeTest {
	private FinalNode finalNode;

	@BeforeEach
	void setUp() throws Exception {
		finalNode = new FinalNode();
	}
	
	@Test
	void testClassInstance() {
		assertNotNull(finalNode);
	}

}
