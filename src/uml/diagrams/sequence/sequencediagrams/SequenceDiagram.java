package uml.diagrams.sequence.sequencediagrams;

import java.util.ArrayList;
import java.util.List;

import uml.diagrams.sequence.BaseElement;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;

public class SequenceDiagram extends BaseElement {
	final static String INVALID_GUARD_CONDITION_VALUE_ERROR_MESSAGE = "A condição de guarda não pode ser vazia ou nula";
	private Boolean guardCondition;
	
	private List<ISequenceDiagramElement> elements;
	
	public SequenceDiagram(String name, Boolean guardCondition) throws SequenceDiagramRuleException {
		super(name);
		elements = new ArrayList<>();

		setGuardCondition(guardCondition);
	}

	public Boolean getGuardCondition() {
		return guardCondition;
	}

	public void setGuardCondition(Boolean guardCondition) throws SequenceDiagramRuleException {
		if(guardCondition == null)
			throw new SequenceDiagramRuleException(INVALID_GUARD_CONDITION_VALUE_ERROR_MESSAGE);
		this.guardCondition = guardCondition;
	}
	
	public void addElement(ISequenceDiagramElement element) {
	    if (element != null) {	        
	        elements.add(element);
	    }
    }
	
	public List<ISequenceDiagramElement> getElements() {
	    return elements;
	}
	
	public ISequenceDiagramElement getElement(ISequenceDiagramElement searchedElement) {
        for (ISequenceDiagramElement element : elements) {
            if (element.equals(searchedElement)) {
                return element;
            }
            else {
    			System.out.printf("element !== searchedElement = %s, %s\n", element, searchedElement);
            }
        }
        return null;
    }
	
	@Override
	public String toString() {
		String representantion = "<SequenceDiagram " + 
				"name=\"" + this.getName() + "\" " + 
				"guardCondition=\"" + this.getGuardCondition() +
				"\">";

		for (ISequenceDiagramElement message : elements) {
            representantion += message.toString();
        }
		
		representantion += "</SequenceDiagram>";

		return representantion;
	}
}
