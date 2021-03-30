package uml.diagrams.activity.entities.transition;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uml.diagrams.activity.entities.Transition;

class TransitionGetNameTest {

	private Transition transition;
	private final String NAME = "defaultName";
	
	@BeforeEach
	public void setup() {
		transition = new Transition();
	}
	
	@Test
	public void testGetNameOfTransition() {
		transition.setName(NAME);
		
		assertEquals(NAME, transition.getName());
	}

}
