package uml.diagrams.sequence.lifelines;

import java.util.ArrayList;
import java.util.List;

public class LifelinesGroup {
    
    List<Lifeline> lifelines;
    
    public LifelinesGroup() {
        lifelines = new ArrayList<>();
    }

    public void addLifeline(Lifeline lifeline) {
        lifelines.add(lifeline);
    }
    
    public List<Lifeline> getLifelines() {
        return lifelines;
    }
}
