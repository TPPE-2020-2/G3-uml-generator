package uml.diagrams.sequence.fragments;

import java.util.ArrayList;
import java.util.List;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;

public class Fragments {
	
	public final static String NULL_OPTIONAL_VALUE = "O Optional não pode ser nulo";
	
	private List<Optional> optionals;
	
	public Fragments() {
		this.optionals = new ArrayList<Optional>();
	}
	
	public void addOptional(Optional optional) throws SequenceDiagramRuleException {
		if (optional == null)
			throw new SequenceDiagramRuleException(NULL_OPTIONAL_VALUE);
		this.optionals.add(optional);
	}
	
	public List<Optional> getOptionals() {
		return optionals;
	}
	
	@Override
	public String toString() {
		if (optionals.size() == 0)
			return "";
		
		String optionalsList = "";
		
		for (Optional temp: optionals)
			optionalsList += temp.toString();
		
		return "<Fragments>" + optionalsList + "</Fragments>";
	}
}
