package uml.diagrams.activity.entities.mergenode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class MergeNodeConstructorNameTest {
	
	private MergeNode mergeNode;

	public static Collection mergeNodeNames() {
		return Arrays.asList(new Object[][] {
	        { "name" },
	        { "mergeNode" },
	        { "abcd123" },
	        { "22" }
		});
	}
	
	@ParameterizedTest
	@MethodSource("mergeNodeNames")
	void testGetFinalNodeName(String name) throws ActivityDiagramRuleException {
		mergeNode = new MergeNode(name);

		assertEquals(name, mergeNode.getName());
	}

}
