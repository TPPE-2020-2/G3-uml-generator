package uml.diagrams.sequence.sequencediagrams.fragments;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.fragments.Optional;
import uml.diagrams.sequence.sequencediagrams.ISequenceDiagramElement;

public class Fragment implements ISequenceDiagramElement {
    static final String INVALID_OPTIONAL = "Optional associado invalido";
    
    private Optional optional;

    public Fragment(Optional optional) throws SequenceDiagramRuleException {
        setOptional(optional);
    }
    
    public void setOptional(Optional optional) throws SequenceDiagramRuleException {
        if (optional == null) {
            throw new SequenceDiagramRuleException(INVALID_OPTIONAL);
        }
        
        this.optional = optional;
    }
    
    public String getName() {
        return optional.getName();
    }

    @Override
    public String toString() {
        return "<Fragment " + "name=\"" + optional.getName() + "\" " + "/>";
    }
}
