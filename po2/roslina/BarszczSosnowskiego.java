package pl.kamil.po2.roslina;

import pl.kamil.po2.*;
import pl.kamil.po2.zwierze.CyberOwca;
import pl.kamil.po2.zwierze.Lis;

import java.awt.*;

public class BarszczSosnowskiego extends RoslinaTrujaca {
    private String nazwa;
    private static final int SILA_BARSZCZ = 10;


    public BarszczSosnowskiego(Swiat swiat) {
        super(swiat, SILA_BARSZCZ, 0, Color.BLUE);
        this.nazwa = "Barszcz Sosnowskiego";
    }

    @Override
    public void akcja()
    {
        super.akcja();
        Organizm sasiedztwo[] = {null, null, null, null};
        getSasiedztwo(sasiedztwo);

        for (int i = 0; i < 4; i++) {
            if (sasiedztwo[i] != null) {
                if (sasiedztwo[i] instanceof Zwierze && !(sasiedztwo[i] instanceof CyberOwca)) {
                    this.swiat.getOrganizm(sasiedztwo[i].getPolozenie()).setZywy(false);
                    this.swiat.komentator.info_o_dzialaniu_barszczu(this,sasiedztwo[i]);
                    this.swiat.zabij(sasiedztwo[i].getPolozenie());
                }
            }
        }

    }

    @Override
    public Organizm sklonuj(Polozenie p) {
        return new BarszczSosnowskiego(swiat);
    }

    @Override
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public boolean kolizja(Organizm atakujacy)
    {
        if (!(atakujacy instanceof CyberOwca))
        {
            super.kolizja(atakujacy);
        }
        else
        {
            this.swiat.komentator.info_o_zjedzeniu(atakujacy,this);
            this.setZywy(false);
            return true;
        }
        return true;
    }

    @Override
    public char getSymbol()
    {
        return 'B';
    }
}
