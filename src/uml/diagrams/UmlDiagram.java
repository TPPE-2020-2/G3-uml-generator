package uml.diagrams;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.lifelines.Lifeline;

public abstract class UmlDiagram {
	public static void main(String[] args) throws SequenceDiagramRuleException {
		Lifeline source = new Lifeline("origem");
		Lifeline target = new Lifeline("destino");
	}
}

