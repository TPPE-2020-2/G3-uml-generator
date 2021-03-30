package uml.diagrams.activity.entities.activitynode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class ActivityNodeConstructorNameTest {

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
		ActivityNode activityNode = new ActivityNode(name);

		assertEquals(name, activityNode.getName());
	}

}
