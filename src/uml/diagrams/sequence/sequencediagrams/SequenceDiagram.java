package uml.diagrams.sequence.sequencediagrams;

import java.util.ArrayList;
import java.util.List;

import uml.diagrams.sequence.BaseElement;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.sequencediagrams.messages.Message;

public class SequenceDiagram extends BaseElement {
	final static String INVALID_GUARD_CONDITION_VALUE_ERROR_MESSAGE = "A condição de guarda não pode ser vazia ou nula";
	private Boolean guardCondition;
	
	private List<Message> messages;
	
	public SequenceDiagram(String name, Boolean guardCondition) throws SequenceDiagramRuleException {
		super(name);
		messages = new ArrayList<>();

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
	
	public void addMessage(Message message) {
	    if (message != null) {	        
	        messages.add(message);
	    }
    }
	
	public List<Message> getMessages() {
	    return messages;
	}
	
	@Override
	public String toString() {
		String representantion = "<SequenceDiagram " + 
				"name=\"" + this.getName() + "\" " + 
				"guardCondition=\"" + this.getGuardCondition() +
				"\">";

		for (Message message : messages) {
            representantion += message.toString();
        }
		
		representantion += "</SequenceDiagram>";

		return representantion;
	}
}
