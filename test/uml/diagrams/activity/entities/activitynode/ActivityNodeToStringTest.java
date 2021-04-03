package uml.diagrams.activity.entities.activitynode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class ActivityNodeToStringTest {

	private ActivityNode activityNode;

 	public static Collection<Object[]> activityNodeNames() {
		return Arrays.asList(new Object[][] {
	        { "name", "<Activity name=\"name\" />" },
	        { "activityNode", "<Activity name=\"activityNode\" />" },
	        { "abcd123", "<Activity name=\"abcd123\" />" },
	        { "22", "<Activity name=\"22\" />" }
		});
	}

	@ParameterizedTest
	@MethodSource("activityNodeNames")
	void testGetStartNodeName(String name, String expectedValue) throws ActivityDiagramRuleException {
		activityNode = new ActivityNode(name);

		assertEquals(activityNode.toString(), expectedValue);
	}
}
