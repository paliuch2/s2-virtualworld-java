package pl.kamil.po2.zwierze;
import pl.kamil.po2.*;
import pl.kamil.po2.roslina.Mlecz;

import java.awt.*;


public class Wilk extends Zwierze {

    private String nazwa;
    private static final int SILA_WILK = 9;
    private static final int INICJATYWA_WILK = 5;

    public Wilk (Swiat swiat) {
        super(swiat, SILA_WILK, INICJATYWA_WILK, Color.GRAY);
        this.nazwa = "Wilk";
    }

    @Override
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public Organizm sklonuj(Polozenie p) {
        return new Wilk(swiat);
    }

    @Override
    protected boolean zgodnoscGatunkow(Organizm inny) {
        return inny instanceof Wilk;
    }

    @Override
    public char getSymbol()
    {
        return 'W';
    }
}
