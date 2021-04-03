package uml.diagrams.sequence.exceptions;

public class MessageFormatException extends Exception {

    private static final long serialVersionUID = 1L;

    public MessageFormatException(String errorMessage) {
        super(errorMessage);
    }
}
