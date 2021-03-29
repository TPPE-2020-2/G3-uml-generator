package uml.diagrams.activity.entities.finalnode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class FinalNodeSetNameTest {
	
	private FinalNode finalNode;
	private final String INITIAL_NAME = "NAME";
	
	@BeforeEach
	public void setup() throws ActivityDiagramRuleException {
		finalNode = new FinalNode(INITIAL_NAME);
	}
	
 	public static Collection finalNodeNames() {
		return Arrays.asList(new Object[][] {
	        { "name" },
	        { "finalNode" },
	        { "abcd123" },
	        { "22" }
		});
	}
	
	@ParameterizedTest
	@MethodSource("finalNodeNames")
	void testGetFinalNodeName(String name) throws ActivityDiagramRuleException {
		finalNode.setName(name);

		assertEquals(name, finalNode.getName());
	}
}
