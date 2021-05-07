package uml.diagrams;

import java.util.List;

import uml.diagrams.sequence.SequenceDiagramsGroup;
import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.fragments.Optional;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;

public class SequenceDiagramsGroupController {
    private SequenceDiagramsGroup sequenceDiagramGroup;

    SequenceDiagramsGroupController() {
        sequenceDiagramGroup = new SequenceDiagramsGroup();
    }

    SequenceDiagram addSequenceDiagram(String name, Boolean guard)
            throws SequenceDiagramRuleException {
        SequenceDiagram diagram = createDefaultSequenceDiagram();

        diagram.setName(name);
        diagram.setGuardCondition(guard);

        sequenceDiagramGroup.addSequenceDiagram(diagram);

        return diagram;
    }

    SequenceDiagramsGroup getGeneratedDiagram() {
        return sequenceDiagramGroup;
    }
    
    String getGeneratedDiagramFormatted() {
        return sequenceDiagramGroup
                .toString()
                .replaceAll("(?<!\\/)><", ">\n<")
                .replaceAll("/><", "/>\n<");
    }

    SequenceDiagram getSequenceDiagramByIndex(int index) {
        try {
            return sequenceDiagramGroup.getSequenceDiagrams().get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    List<SequenceDiagram> getSequenceDiagrams() {
        return sequenceDiagramGroup.getSequenceDiagrams();
    }

    void addLifeline(String name) throws SequenceDiagramRuleException {
        Lifeline lifeline = new Lifeline(name);
        sequenceDiagramGroup.addLifeline(lifeline);
    }
    
    List<Lifeline> getLifelines() {
        return sequenceDiagramGroup.getLifelines().getLifelines();
    }
    
    Lifeline getLifelineByIndex(int index) {
        try {
            return sequenceDiagramGroup.getLifelines().getLifelines().get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    void addOptional(String name, SequenceDiagram sequenceDiagram)
            throws SequenceDiagramRuleException, EmptyOptionalFragmentException {
        Optional optional = new Optional(name, sequenceDiagram);
        sequenceDiagramGroup.addOptional(optional);
    }
    
    List<Optional> getOptionals() {
        return sequenceDiagramGroup.getFragments().getOptionals();
    }
    
    Optional getOptionalByIndex(int index) {
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
