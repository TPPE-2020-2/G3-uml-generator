package uml.diagrams.sequence.fragments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;

public class FragmentsSettersAndGettersTest {

	private final static String DEFAULT_NAME = "default";
	private static SequenceDiagram sequenceDiagram;

	private Fragments fragments;

	@BeforeEach
	public void setup() throws SequenceDiagramRuleException {
		fragments = new Fragments();
	}

	@BeforeAll
	public static void init() throws SequenceDiagramRuleException {
		sequenceDiagram = new SequenceDiagram(DEFAULT_NAME, true);
	}

	public static Collection<Object[]> optionalParams()
	        throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
		return Arrays.asList(new Object[][] {
			{ Arrays.asList(
					new Optional(DEFAULT_NAME, sequenceDiagram)), 1},
			{ Arrays.asList(
					new Optional(DEFAULT_NAME, sequenceDiagram), 
					new Optional(DEFAULT_NAME, sequenceDiagram)), 2 },
			{ Arrays.asList(
					new Optional(DEFAULT_NAME, sequenceDiagram), 
					new Optional(DEFAULT_NAME, sequenceDiagram),
					new Optional(DEFAULT_NAME, sequenceDiagram)), 3 }
		});
	}

	@ParameterizedTest
	@MethodSource("optionalParams")
	void testGetAndSetValuesWithSuccess(List<Optional> optionals, int count)
			throws SequenceDiagramRuleException {
		for (Optional optional : optionals)
			fragments.addOptional(optional);
		assertEquals(count, fragments.getOptionals().size());
	}
	
	@Test
	void testFailtGetAndSetValuesWithNullOptional()
			throws SequenceDiagramRuleException {
		SequenceDiagramRuleException exception = assertThrows(SequenceDiagramRuleException.class, 
				() -> fragments.addOptional(null));

		assertEquals(Fragments.NULL_OPTIONAL_VALUE, exception.getMessage());
	}
}
