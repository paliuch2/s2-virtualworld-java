package pl.kamil.po2.roslina;

import pl.kamil.po2.Organizm;
import pl.kamil.po2.Polozenie;
import pl.kamil.po2.Roslina;
import pl.kamil.po2.Swiat;

import java.awt.*;

public class Guarana extends Roslina{
    private String nazwa;


    public Guarana(Swiat swiat) {
        super(swiat, 0, 0, Color.RED);
        this.nazwa = "Guarana";
    }

    @Override
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public Organizm sklonuj(Polozenie p) {
        return new Guarana(swiat);
    }

    @Override
    public boolean kolizja(Organizm atakujacy) {

        this.setZywy(false);
        atakujacy.setSila(atakujacy.getSila()+3);
        this.swiat.komentator.info_o_zwiekszeniu_sily(atakujacy,this);
        return true;
    }

    @Override
    public char getSymbol()
    {
        return 'G';
    }

}
