package uml.diagrams.sequence;

import java.util.ArrayList;
import java.util.List;

import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.fragments.Fragments;
import uml.diagrams.sequence.fragments.Optional;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.lifelines.LifelinesGroup;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;

public class SequenceDiagramsGroup extends BaseGroup {
    
    private LifelinesGroup lifelinesGroup;
    private Fragments fragments;
    private List<SequenceDiagram> sequenceDiagrams;
    
    public SequenceDiagramsGroup () {
        lifelinesGroup = new LifelinesGroup();
        fragments = new Fragments();
        sequenceDiagrams = new ArrayList<>();
    }

    public void addLifeline(Lifeline lifeline) {
        lifelinesGroup.addLifeline(lifeline);
    }

    public LifelinesGroup getLifelines() {
        return lifelinesGroup;
    }

    public void addOptional(Optional optional)
            throws SequenceDiagramRuleException {
        fragments.addOptional(optional);        
    }

    public Fragments getFragments() {
        return fragments;
    }

    public void addSequenceDiagram(SequenceDiagram sequenceDiagram) {
        sequenceDiagrams.add(sequenceDiagram);
    }

    public SequenceDiagram getSequenceDiagram(String sequenceDiagramName) {
        return findNode(sequenceDiagrams, elem -> elem.getName().equals(sequenceDiagramName));
    }
    
    public List<SequenceDiagram> getSequenceDiagrams() {
		return sequenceDiagrams;
	}

	public void setSequenceDiagrams(List<SequenceDiagram> sequenceDiagrams) {
		this.sequenceDiagrams = sequenceDiagrams;
	}

	@Override
    public String toString() {
        String sequenceDiagramsString = "";
        
        for (SequenceDiagram diagram: sequenceDiagrams)
            sequenceDiagramsString += diagram.toString();
        
        return  "<SequenceDiagrams>" +
                    lifelinesGroup.toString() +
                    fragments.toString() +
                    sequenceDiagramsString +
                "</SequenceDiagrams>";
    }
}
