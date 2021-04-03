package uml.diagrams.activity.entities.finalnode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class FinalNodeToStringTest {

	private FinalNode finalNode;

 	public static Collection<Object[]> finalNodeNames() {
		return Arrays.asList(new Object[][] {
	        { "name", "<FinalNode name=\"name\" />" },
	        { "finalNode", "<FinalNode name=\"finalNode\" />" },
	        { "abcd123", "<FinalNode name=\"abcd123\" />" },
	        { "22", "<FinalNode name=\"22\" />" }
		});
	}

	@ParameterizedTest
	@MethodSource("finalNodeNames")
	void testGetStartNodeName(String name, String expectedValue) throws ActivityDiagramRuleException {
		finalNode = new FinalNode(name);

		assertEquals(finalNode.toString(), expectedValue);
	}

}
