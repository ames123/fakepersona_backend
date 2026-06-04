package uj.mechaniki.fakepersona.model;

public enum Action {
    FREE_TIME("FREE_TIME"),
    READ("READ"),
    CLEAN("CLEAN"),
    TV("TV"),
    COOK("COOK"),
    GARDEN("GARDEN"),
    SPORT("SPORT"),
    WRITE("WRITE"),
    EAT("EAT"),
    WASH("WASH"),
    SLEEP("SLEEP"),
    ;

    public final String label;

    private Action(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
