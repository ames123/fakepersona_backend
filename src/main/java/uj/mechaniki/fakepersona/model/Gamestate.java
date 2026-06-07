package uj.mechaniki.fakepersona.model;

public enum Gamestate {
    JOIN("JOIN"),
    ORDERING("ORDERING"),
    POSITION("POSITION"),
    DEDUCTION("DEDUCTION"),
    END("END"),
    ;

    public final String label;

    Gamestate(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
