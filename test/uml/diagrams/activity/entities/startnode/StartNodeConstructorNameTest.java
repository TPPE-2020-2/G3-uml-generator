package uml.diagrams.activity.entities.startnode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class StartNodeConstructorNameTest {
	
	private String name;
	
 	public static Collection startNodeNames() {
		return Arrays.asList(new Object[][] {
	        { "name" },
	        { "initialNode" },
	        { "abcd123" },
	        { "22" }
		});
	}
	
	@ParameterizedTest
	@MethodSource("startNodeNames")
	void testGetStartNodeName(String name) throws ActivityDiagramRuleException {
		StartNode initialNode = new StartNode(name);

		assertEquals(name, initialNode.getName());
	}

}
