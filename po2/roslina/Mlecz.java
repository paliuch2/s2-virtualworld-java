package pl.kamil.po2.roslina;
import pl.kamil.po2.*;

import java.awt.*;

public class Mlecz extends Roslina{
    private String nazwa;


    public Mlecz(Swiat swiat) {
        super(swiat, 0, 0, Color.YELLOW);
        this.nazwa = "Mlecz";
    }

    @Override
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public Organizm sklonuj(Polozenie p) {
        return new Mlecz(swiat);
    }

    @Override
    public void akcja()
    {
        for (int i=0; i<3; i++)
        {
            super.akcja();
        }
    }

    @Override
    public char getSymbol()
    {
        return 'M';
    }
}
