package uj.mechaniki.fakepersona.model;

public enum Gamestate {
    JOIN("JOIN"),
    TASK_ORDERING("TASK_ORDERING"),
    POSITION("POSITION"),
    DEDUCTION("DEDUCTION"),
    END("END"),
    FREE_TIME_SELECT("FREE_TIME_SELECT"),
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
