package uml.diagrams.activity.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import uml.diagrams.activity.ActivityDiagram;
import uml.diagrams.activity.entities.ActivityNode;
import uml.diagrams.activity.entities.BaseNode;
import uml.diagrams.activity.entities.DecisionNode;
import uml.diagrams.activity.entities.FinalNode;
import uml.diagrams.activity.entities.MergeNode;
import uml.diagrams.activity.entities.StartNode;
import uml.diagrams.activity.entities.Transition;
import uml.diagrams.activity.exceptions.ActivityDiagramRuleException;
import uml.diagrams.utils.XMLUtils;

public class XMLUtilsTest {
	
private ActivityDiagram activityDiagram;
	
	private final static String DEFAULT_NAME = "default";
	private static StartNode startNode;
	private static ActivityNode activityNode;
	private static DecisionNode decisionNode;
	private static MergeNode mergeNode;
	private static FinalNode finalNode;

	@BeforeEach
	public void setup() throws ActivityDiagramRuleException {
		activityDiagram = new ActivityDiagram(DEFAULT_NAME);
	}

	@BeforeAll
	public static void initialSetup() throws ActivityDiagramRuleException {
		startNode = new StartNode("start");
		activityNode = new ActivityNode("activity");
		decisionNode = new DecisionNode("decision");
		mergeNode = new MergeNode("merge");
		finalNode = new FinalNode("final");
	}

	private <T extends BaseNode> Transition createTransition(String name, float prob, T source, T target ) 
			throws ActivityDiagramRuleException {
		Transition tempTransition = new Transition();
		
		tempTransition.setName(name);
		tempTransition.setProb(prob);
		tempTransition.setSource(source);
		tempTransition.setTarget(target);
		
		return tempTransition;
	}

	@Test
	public void generateActivityDiagramXml() 
			throws ActivityDiagramRuleException, 
		UnsupportedEncodingException, 
		ParserConfigurationException, 
		SAXException, 
		IOException, 
		TransformerException {
		
		ActivityNode activityNode2 = new ActivityNode("activity2");
		ActivityNode activityNode3 = new ActivityNode("activity3");
		ActivityNode activityNode4 = new ActivityNode("activity4");

		activityDiagram
			.addNodeElement(startNode)
			.addNodeElement(activityNode)
			.addNodeElement(activityNode2)
			.addNodeElement(activityNode3)
			.addNodeElement(activityNode4)
			.addNodeElement(mergeNode)
			.addNodeElement(decisionNode)		
			.addNodeElement(finalNode);

		Transition transition1 = createTransition("transition1", 0.5f, startNode, activityNode);
		Transition transition2 = createTransition("transition2", 0.5f, activityNode, activityNode2);
		Transition transition3 = createTransition("transition3", 0.5f, activityNode2, activityNode3);
		Transition transition4 = createTransition("transition4", 0.5f, activityNode3, decisionNode);
		Transition transition5 = createTransition("transition5", 0.5f, decisionNode, activityNode4);
		Transition transition6 = createTransition("transition6", 0.5f, decisionNode, mergeNode);
		Transition transition7 = createTransition("transition7", 0.5f, activityNode4, mergeNode);
		Transition transition8 = createTransition("transition8", 0.5f, mergeNode, finalNode);

		activityDiagram
			.addTransition(transition1)
			.addTransition(transition2)
			.addTransition(transition3)
			.addTransition(transition4)
			.addTransition(transition5)
			.addTransition(transition6)
			.addTransition(transition7)
			.addTransition(transition8);
		
		activityDiagram.validateActivityDiagram();
		
		String path = System.getProperty("user.dir") + "/teste.xml";
		
		XMLUtils.generateXML(activityDiagram.toString(), path);
		
		File file = new File(path);

		boolean fileExists = file.exists() && file.isFile();
		
		file.delete();
		
		assertTrue(fileExists);
	}
}
