package uml.diagrams.activity.entities.mergenode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class MergeNodeToStringTest {

	private MergeNode mergeNode;

 	public static Collection<Object[]> mergeNodeNames() {
		return Arrays.asList(new Object[][] {
	        { "name", "<MergeNode name=\"name\" />" },
	        { "mergeNode", "<MergeNode name=\"mergeNode\" />" },
	        { "abcd123", "<MergeNode name=\"abcd123\" />" },
	        { "22", "<MergeNode name=\"22\" />" }
		});
	}

	@ParameterizedTest
	@MethodSource("mergeNodeNames")
	void testGetStartNodeName(String name, String expectedValue) throws ActivityDiagramRuleException {
		mergeNode = new MergeNode(name);

		assertEquals(mergeNode.toString(), expectedValue);
	}

}
