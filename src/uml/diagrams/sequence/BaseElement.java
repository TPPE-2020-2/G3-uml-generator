package uml.diagrams.sequence;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;

public class BaseElement {
    
    public static final String NAME_ERROR = "O nome não pode ser vazio";

    protected String name;
    
    public BaseElement(String name) throws SequenceDiagramRuleException {
        validateName(name);

        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    private void validateName(String name) throws SequenceDiagramRuleException {
        if (name == null || name.trim().isEmpty()) {            
            throw new SequenceDiagramRuleException(NAME_ERROR);
        }
    }
}
