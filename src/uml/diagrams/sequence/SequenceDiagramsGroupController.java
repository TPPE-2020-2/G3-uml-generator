package uml.diagrams.sequence;

import java.util.List;

import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.fragments.Optional;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;

public class SequenceDiagramsGroupController {
    private SequenceDiagramsGroup sequenceDiagramGroup;

    public SequenceDiagramsGroupController() {
        sequenceDiagramGroup = new SequenceDiagramsGroup();
    }

    public SequenceDiagram addSequenceDiagram(String name, Boolean guard)
            throws SequenceDiagramRuleException {
        SequenceDiagram diagram = createDefaultSequenceDiagram();

        diagram.setName(name);
        diagram.setGuardCondition(guard);

        sequenceDiagramGroup.addSequenceDiagram(diagram);

        return diagram;
    }

    public SequenceDiagramsGroup getGeneratedDiagram() {
        return sequenceDiagramGroup;
    }
    
    public String getGeneratedDiagramFormatted() {
        return sequenceDiagramGroup
                .toString()
                .replaceAll("(?<!\\/)><", ">\n<")
                .replaceAll("/><", "/>\n<");
    }

    public SequenceDiagram getSequenceDiagramByIndex(int index) {
        try {
            return sequenceDiagramGroup.getSequenceDiagrams().get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public List<SequenceDiagram> getSequenceDiagrams() {
        return sequenceDiagramGroup.getSequenceDiagrams();
    }

    public void addLifeline(String name) throws SequenceDiagramRuleException {
        Lifeline lifeline = new Lifeline(name);
        sequenceDiagramGroup.addLifeline(lifeline);
    }
    
    public List<Lifeline> getLifelines() {
        return sequenceDiagramGroup.getLifelines().getLifelines();
    }
    
    public Lifeline getLifelineByIndex(int index) {
        try {
            return sequenceDiagramGroup.getLifelines().getLifelines().get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void addOptional(String name, SequenceDiagram sequenceDiagram)
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
        Optional optional = new Optional(name, sequenceDiagram);
        sequenceDiagramGroup.addOptional(optional);
    }
    
    public List<Optional> getOptionals() {
        return sequenceDiagramGroup.getFragments().getOptionals();
    }
    
    public Optional getOptionalByIndex(int index) {
        try {            
            return sequenceDiagramGroup.getFragments().getOptionals().get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private SequenceDiagram createDefaultSequenceDiagram() throws SequenceDiagramRuleException {
        SequenceDiagram diagram = null;
        if (diagram == null) {
            diagram = new SequenceDiagram("default", true);
        }
        return diagram;
    }
}
