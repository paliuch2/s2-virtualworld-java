package pl.kamil.po2.roslina;

import pl.kamil.po2.*;

import java.awt.*;

public class WilczeJagody extends RoslinaTrujaca {
    private String nazwa;
    private static final int SILA_JAGODY = 99;
    private static final Color PURPLE = new Color(153,0,153);


    public WilczeJagody(Swiat swiat) {
        super(swiat, SILA_JAGODY, 0, PURPLE);
        this.nazwa = "Wilcze jagody";
    }

    @Override
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public Organizm sklonuj(Polozenie p) {
        return new WilczeJagody(swiat);
    }

    @Override
    public char getSymbol()
    {
        return 'J';
    }
}
