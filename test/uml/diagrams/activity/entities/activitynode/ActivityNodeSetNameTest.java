package uml.diagrams.activity.entities.activitynode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class ActivityNodeSetNameTest {
	private ActivityNode activityNode;
	private final String DEFAULT_NAME = "default";
	
	
	@BeforeEach
	public void setup() throws ActivityDiagramRuleException {
		activityNode = new ActivityNode(DEFAULT_NAME);
	}

	public static Collection activityNodeNames() {
		return Arrays.asList(new Object[][] {
	        { "name" },
	        { "activityNode" },
	        { "abcd123" },
	        { "22" }
		});
	}
	
	@ParameterizedTest
	@MethodSource("activityNodeNames")
	void testGetActivityNodeName(String name) throws ActivityDiagramRuleException {
		activityNode.setName(name);

		assertEquals(name, activityNode.getName());
	}
}
