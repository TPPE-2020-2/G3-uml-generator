package uml.diagrams.sequence.fragments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;

public class FragmentsSettersAndGettersTest {

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

        return Arrays.asList(new Object[][] {
            {Arrays.asList(new Optional("fragment1", sequenceDiagram1)), 1 },
            {Arrays.asList(
                    new Optional("fragment1", sequenceDiagram1),
                    new Optional("fragment2", sequenceDiagram2)),
                2
            },
            {Arrays.asList(
                    new Optional("fragment1", sequenceDiagram1),
                    new Optional("fragment2", sequenceDiagram2),
                    new Optional("fragment3", sequenceDiagram3)),
                3
            }
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
    void testFailtGetAndSetValuesWithNullOptional() throws SequenceDiagramRuleException {
        SequenceDiagramRuleException exception = assertThrows(SequenceDiagramRuleException.class,
                () -> fragments.addOptional(null));

        assertEquals(Fragments.NULL_OPTIONAL_VALUE, exception.getMessage());
    }
}
