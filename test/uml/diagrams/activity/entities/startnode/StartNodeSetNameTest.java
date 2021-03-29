package uml.diagrams.activity.entities.startnode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class StartNodeSetNameTest {
	private StartNode initialNode;
	private String name;
	private final String INITIAL_NAME = "NAME";
	
	@BeforeEach
	public void setup() throws ActivityDiagramRuleException {
		initialNode = new StartNode(INITIAL_NAME);
	}
	
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
		initialNode.setName(name);

		assertEquals(name, initialNode.getName());
	}

}
