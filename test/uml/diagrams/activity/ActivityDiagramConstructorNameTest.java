package uml.diagrams.activity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

class ActivityDiagramConstructorNameTest {
	
	private ActivityDiagram activityDiagram;

	public static Collection<Object[]> activityDiagramNames() {
		return Arrays.asList(new Object[][] {
	        { "name" },
	        { "activity" },
	        { "abcd123" },
	        { "22" }
		});
	}
	
	@ParameterizedTest
	@MethodSource("activityDiagramNames")
	void testGetActivityNodeName(String name) throws ActivityDiagramRuleException {
		activityDiagram = new ActivityDiagram(name);

		assertEquals(name, activityDiagram.getName());
	}
}
