package pl.kamil.po2;

public enum Kierunki {

    LEWY(0),
    GORNY(1),
    PRAWY(2),
    DOLNY(3);

    private int value;

    Kierunki(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

}
