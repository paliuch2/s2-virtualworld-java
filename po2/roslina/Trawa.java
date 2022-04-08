package pl.kamil.po2.roslina;
import pl.kamil.po2.*;

import java.awt.*;

public class Trawa extends Roslina{
    private String nazwa;


    public Trawa (Swiat swiat) {
        super(swiat, 0, 0, Color.GREEN);
        this.nazwa = "Trawa";
    }

    @Override
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public Organizm sklonuj(Polozenie p) {
        return new Trawa(swiat);
    }

    @Override
    public char getSymbol()
    {
        return 'T';
    }
}
