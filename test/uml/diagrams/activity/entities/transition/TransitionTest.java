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
	
	public static Collection<Object[]> toStringValues() throws ActivityDiagramRuleException {
		return Arrays.asList(new Object[][] {
	        { "teste1", 0.1f, new ActivityNode("source"), new MergeNode("targetMerge"),
	        	"<Transition name=\"teste1\" prob=\"0.1\" source=\"source\" target=\"targetMerge\" />"},
	        { "teste2", 0.2f, new ActivityNode("source"), new DecisionNode("targetDecision"),
	        	"<Transition name=\"teste2\" prob=\"0.2\" source=\"source\" target=\"targetDecision\" />"},
	        { "teste3", 0.3f, new ActivityNode("source"), new ActivityNode("targetActivity"),
        		"<Transition name=\"teste3\" prob=\"0.3\" source=\"source\" target=\"targetActivity\" />"},
	        { "teste4", 0.4f, new ActivityNode("source"), new FinalNode("targetFinal"),
    			"<Transition name=\"teste4\" prob=\"0.4\" source=\"source\" target=\"targetFinal\" />"},
	        { "teste5", 0.5f, new StartNode("source"), new ActivityNode("targetActivity"),
				"<Transition name=\"teste5\" prob=\"0.5\" source=\"source\" target=\"targetActivity\" />"},
		});
	}
	
	@ParameterizedTest
	@MethodSource("toStringValues")
	public void testToStringValues(String name, Float propb, BaseNode source, BaseNode target, String expected) 
		throws ActivityDiagramRuleException {
		transition.setName(name);
		transition.setProb(propb);
		transition.setSource(source);
		transition.setTarget(target);
		
		assertEquals(expected, transition.toString());
	}

}
