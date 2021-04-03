package uml.diagrams.sequence.lifelines;

import java.util.ArrayList;
import java.util.List;

import uml.diagrams.sequence.BaseGroup;

public class LifelinesGroup extends BaseGroup {
    
    List<Lifeline> lifelines;
    
    public LifelinesGroup() {
        lifelines = new ArrayList<>();
    }

    public void addLifeline(Lifeline lifeline) {
        if (lifeline != null) {
            lifelines.add(lifeline);
        }
    }
    
    public List<Lifeline> getLifelines() {
        return lifelines;
    }
    
    public Lifeline getLifeline(String lifelineName) {
        return findNode(lifelines, elem -> elem.getName() == lifelineName);
    }
    
    @Override
    public String toString() {
        String representantion = "<Lifelines>";
        for (Lifeline lifeline : lifelines) {
            representantion += lifeline.toString();
        }
        representantion += "</Lifelines>";
        
        return representantion;
    }
    
}
