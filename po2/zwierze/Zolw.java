package pl.kamil.po2.zwierze;
import pl.kamil.po2.*;
import pl.kamil.po2.roslina.Mlecz;

import java.awt.*;

public class Zolw extends Zwierze {
    private String nazwa;
    private static final int SILA_ZOLW = 2;
    private static final int INICJATYWA_ZOLW = 1;
    private static final int MAX_SILA_ODPARCIA = 5;


    public Zolw (Swiat swiat) {
        super(swiat, SILA_ZOLW, INICJATYWA_ZOLW, Color.DARK_GRAY);
        this.nazwa = "Żółw";
    }

    @Override
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public Organizm sklonuj(Polozenie p) {
        return new Zolw(swiat);
    }

    @Override
    public void akcja()
    {
       int los = this.swiat.losowe.nextInt(4);
        if (los == 0)
        {
            super.akcja();
        }
    }

    @Override
    public boolean kolizja(Organizm atakujacy)
    {
        if (atakujacy.getSila()>=MAX_SILA_ODPARCIA)
        {
           this.swiat.komentator.info_o_walce(atakujacy, this);
            this.setZywy(false);
            return true;
        } else {
            this.swiat.komentator.info_o_odparciu_ataku(this, atakujacy);
            return false;
        }

    }

    @Override
    protected boolean zgodnoscGatunkow(Organizm inny) {
        return inny instanceof Zolw;
    }

    @Override
    public char getSymbol()
    {
        return 'Z';
    }

}
