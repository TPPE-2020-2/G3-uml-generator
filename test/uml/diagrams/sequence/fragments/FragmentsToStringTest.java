package uml.diagrams.sequence.fragments;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;
import uml.diagrams.sequence.sequencediagrams.fragments.Fragment;

public class FragmentsToStringTest {

	private Fragments fragments;

	@BeforeEach
	public void setup() throws SequenceDiagramRuleException {
		fragments = new Fragments();
	}

	public static Collection<Object[]> optionalParams()
	        throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
        SequenceDiagram sequenceDiagram1 = new SequenceDiagram("diagram1", true);
        SequenceDiagram sequenceDiagram2 = new SequenceDiagram("diagram2", false);
        SequenceDiagram sequenceDiagram3 = new SequenceDiagram("diagram3", true);
        Fragment fragment1 = new Fragment(new Optional("fragment1", sequenceDiagram1));
        Fragment fragment2 = new Fragment(new Optional("fragment2", sequenceDiagram2));
        Fragment fragment3 = new Fragment(new Optional("fragment3", sequenceDiagram3));
        
        sequenceDiagram1.addElement(fragment2);

        sequenceDiagram2.addElement(fragment1);
        sequenceDiagram2.addElement(fragment3);

        sequenceDiagram3.addElement(fragment1);
        sequenceDiagram3.addElement(fragment2);
        sequenceDiagram3.addElement(fragment3);
	    
		return Arrays.asList(new Object[][] {
			{ new ArrayList<Optional>(), "<Fragments></Fragments>"},
			{ Arrays.asList(
			        new Optional("fragment1", sequenceDiagram1)), 
			"<Fragments><Optional name=\"fragment1\" representedBy=\"diagram1\"/></Fragments>"},
			{ Arrays.asList(
			        new Optional("fragment1", sequenceDiagram1), 
			        new Optional("fragment2", sequenceDiagram2)), 
			"<Fragments>"
			+ "<Optional name=\"fragment1\" representedBy=\"diagram1\"/>"
			+ "<Optional name=\"fragment2\" representedBy=\"diagram2\"/>"
			+ "</Fragments>"},
			{ Arrays.asList(
			        new Optional("fragment1", sequenceDiagram1), 
			        new Optional("fragment2", sequenceDiagram2),
			        new Optional("fragment3", sequenceDiagram3)),
			"<Fragments>"
			+ "<Optional name=\"fragment1\" representedBy=\"diagram1\"/>"
			+ "<Optional name=\"fragment2\" representedBy=\"diagram2\"/>"
			+ "<Optional name=\"fragment3\" representedBy=\"diagram3\"/>"
			+ "</Fragments>" }
		});
	}

	@ParameterizedTest
	@MethodSource("optionalParams")
	void testGetAndSetValuesWithSuccess(List<Optional> optionals, String expectedToString)
			throws SequenceDiagramRuleException {
		for (Optional optional : optionals)
			fragments.addOptional(optional);
		assertEquals(expectedToString, fragments.toString());
	}
}
