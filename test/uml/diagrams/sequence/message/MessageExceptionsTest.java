package uml.diagrams.sequence.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import uml.diagrams.sequence.exceptions.MessageFormatException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.lifelines.Lifeline;

public class MessageExceptionsTest {

    public static Collection<Object[]> messageParms() throws SequenceDiagramRuleException {
        return Arrays.asList(new Object[][] {
            {
                "", 0.5f, new Lifeline("origem"), new Lifeline("destino"),
                Message.INVALID_NAME_ERROR_MESSAGE
            },
            {
                null, 0.5f, new Lifeline("origem"), new Lifeline("destino"),
                Message.INVALID_NAME_ERROR_MESSAGE
            },
            {
                "name", null, new Lifeline("origem"), new Lifeline("destino"),
                Message.INVALID_PROB_VALUE_ERROR_MESSAGE
            },
            {
                "name", -0.1f, new Lifeline("origem"), new Lifeline("destino"),
                Message.INVALID_PROB_VALUE_ERROR_MESSAGE
            },
            {
                "name", 1.1f, new Lifeline("origem"), new Lifeline("destino"),
                Message.INVALID_PROB_VALUE_ERROR_MESSAGE
            },
            {
                "name", 0.5f, null, new Lifeline("destino"),
                Message.INVALID_SOURCE
            },
            {
                "name", 1f, new Lifeline("origem"), null,
                Message.INVALID_TARGET
            }
        });
    }

    @ParameterizedTest
    @MethodSource("messageParms")
    void testMessageFormatException(String name, Float prob, Lifeline source, Lifeline target, String messageError) {
        MessageFormatException exception = assertThrows(
                MessageFormatException.class,
                () -> new Message(name, prob, source, target));

        assertEquals(messageError, exception.getMessage());
    }
}
