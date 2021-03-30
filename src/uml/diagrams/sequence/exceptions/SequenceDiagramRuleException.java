package uml.diagrams.sequence.exceptions;

public class SequenceDiagramRuleException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public SequenceDiagramRuleException(String errorMessage) {
        super(errorMessage);
    }
}
