package uml.diagrams.sequence.exceptions;

public class EmptyGuardConditionException extends Exception {
    
    private static final long serialVersionUID = 1L;

    public EmptyGuardConditionException(String errorMessage) {
        super(errorMessage);
    }
}
