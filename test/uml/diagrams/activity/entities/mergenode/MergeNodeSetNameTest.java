package uml.diagrams.activity.entities.mergenode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class MergeNodeSetNameTest {

	private final String INITIAL_NAME = "NAME";
	private MergeNode mergeNode;
	
	@BeforeEach
	public void setup() throws ActivityDiagramRuleException {
		mergeNode = new MergeNode(INITIAL_NAME);
	}

	public static Collection<Object[]> mergeNodeNames() {
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
		mergeNode.setName(name);

		assertEquals(name, mergeNode.getName());
	}

}
