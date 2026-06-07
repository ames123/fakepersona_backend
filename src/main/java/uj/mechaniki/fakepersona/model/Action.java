package uj.mechaniki.fakepersona.model;

public enum Action {
    FREE_TIME("Czas wolny"),
    READ("Czytanie"),
    CLEAN("Ścieranie kurzu"),
    TV("Oglądanie"),
    COOK("Gotowanie"),
    GARDEN("Pielęgnacja roślin"),
    SPORT("Sport"),
    WRITE("Pisanie książki"),
    EAT("Jedzenie"),
    WASH("Mycie"),
    SLEEP("Odpoczynek"),
    TRAINING("Trening"),
    STOCK("Szukanie zapasów"),
    CHANGE("Przebieranie się"),
    DISINFECT("Dezynfekcja"),
    MUSIC("Słuchanie muzyki"),
    ACT("Próba roli"),
    STEAL("Kradzież"),
    CHECKUP("Badanie lekarskie"),
    GAME("Granie na PC"),
    EXPERIMENT("Eksperyment")
    ;

    public final String label;

    Action(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
