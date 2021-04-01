package uml.diagrams.sequence.exceptions;

public class EmptyOptionalFragmentException extends Exception {

    private static final long serialVersionUID = 1L;

    public EmptyOptionalFragmentException(String errorMessage) {
        super(errorMessage);
    }
}
