package uml.diagrams.activity.entities.transition;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.Transition;

class TransitionGetNameTest {

	private Transition transition;
	
	@BeforeEach
	public void setup() {
		transition = new Transition();
	}
	
	public static Collection transitionNames() {
		return Arrays.asList(new Object[][] {
	        { "defaultName" },
	        { "transition" },
	        { "triangulacao" } 
		});
	}
	
	@ParameterizedTest
	@MethodSource("transitionNames")
	public void testGetNameOfTransition(String name) {
		transition.setName(name);
		
		assertEquals(name, transition.getName());
	}

}
