package uml.diagrams.activity.entities.finalnode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class FinalNodeConstructorNameTest {
	
 	public static Collection<Object[]> finalNodeNames() {
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
		FinalNode initialNode = new FinalNode(name);

		assertEquals(name, initialNode.getName());
	}

}
