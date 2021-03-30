package uml.diagrams.activity.entities.transition;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.BaseNode;
import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.entities.Transition;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class TransitionTest {

	private Transition transition;
	
	@BeforeEach
	public void setup() {
		transition = new Transition();
	}
	
	public static Collection<String[]> transitionNames() {
		return Arrays.asList(new String[][] {
	        { "defaultName" },
	        { "transition" },
	        { "triangulacao" } 
		});
	}
	
	@ParameterizedTest
	@MethodSource("transitionNames")
	public void testGetNameOfTransition(String name) throws ActivityDiagramRuleException {
		transition.setName(name);
		
		assertEquals(name, transition.getName());
	}
	
	public static Collection<String[]> emptyValues() {
		return Arrays.asList(new String[][] {
	        { "" },
	        { null }
		});
	}
	
	@ParameterizedTest
	@MethodSource("emptyValues")
	public void testValidateEmptyOrNullName(String name) {
		final String message = "O nome não pode ser vazio ou nulo";
		
		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class, 
				() -> transition.setName(name));
		
		assertEquals(message, exception.getMessage());
	}
	
	public static Collection<Float[]> probValues() {
		return Arrays.asList(new Float[][] {
	        { 1.0f },
	        { 0.0f },
	        { 0.5f }
		});
	}
	
	@ParameterizedTest
	@MethodSource("probValues")
	public void testGetProbValues(Float value) throws ActivityDiagramRuleException {
		transition.setProb(value);
		
		assertEquals(value, transition.getProb());
	}
	
	public static Collection<Float[]> probInvalidValues() {
		return Arrays.asList(new Float[][] {
	        { 1.1f },
	        { -0.1f },
	        { 10.5f },
	        { -10.5f },
		});
	}
	
	@ParameterizedTest
	@MethodSource("probInvalidValues")
	public void testSetProbInvalidValues(Float value) {
		final String message = "A prob deve ser um valor real entre 0 e 1";
		
		ActivityDiagramRuleException exception = assertThrows(
				ActivityDiagramRuleException.class, 
				() -> transition.setProb(value));
		
		assertEquals(message, exception.getMessage());
	}
	
	public static Collection<BaseNode[]> baseNodeValues() throws ActivityDiagramRuleException {
		return Arrays.asList(new BaseNode[][] {
	        { new MergeNode("source1") },
	        { new DecisionNode("source2") },
	        { new ActivityNode("source3") },
	        { new FinalNode("source4") },
	        { new StartNode("source5") },
		});
	}
	
	@ParameterizedTest
	@MethodSource("baseNodeValues")
	public void testGetSources(BaseNode source) throws ActivityDiagramRuleException {
		transition.setSource(source);
		
		assertEquals(source, transition.getSource());
	}
	
	@ParameterizedTest
	@MethodSource("baseNodeValues")
	public void testGetTargets(BaseNode target) throws ActivityDiagramRuleException {
		transition.setTarget(target);
		
		assertEquals(target, transition.getTarget());
	}

}
