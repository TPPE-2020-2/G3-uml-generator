package uml.diagrams.sequence.lifelines;

public class Lifeline {

    private String name;

    public Lifeline(String name) {
        if (name instanceof String) {            
            this.name = name;
        } else {
            this.name = "";
        }
    }

    public String getName() {
        return name;
    }
}
