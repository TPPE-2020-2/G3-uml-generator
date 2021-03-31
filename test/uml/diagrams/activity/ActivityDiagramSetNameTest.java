package uml.diagrams.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;

public class ActivityDiagramSetNameTest {
	private ActivityDiagram activityDiagram;
	private final String DEFAULT_NAME = "default";
	
	
	@BeforeEach
	public void setup() throws ActivityDiagramRuleException {
		activityDiagram = new ActivityDiagram(DEFAULT_NAME);
	}

	public static Collection activityDiagramNames() {
		return Arrays.asList(new Object[][] {
	        { "name" },
	        { "activityNode" },
	        { "abcd123" },
	        { "22" }
		});
	}
	
	@ParameterizedTest
	@MethodSource("activityDiagramNames")
	void testGetActivityNodeName(String name) throws ActivityDiagramRuleException {
		activityDiagram.setName(name);

		assertEquals(name, activityDiagram.getName());
	}
}
