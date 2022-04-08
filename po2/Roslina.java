package pl.kamil.po2;

import java.awt.*;

public abstract class Roslina extends Organizm{


    private static final int SZANSA_NA_ROZMNOZENIE = 25;

    public Roslina(Swiat swiat, int sila, int inicjatywa, Color color) {
        super(swiat, sila, inicjatywa, color);
    }

    public void akcja() {
        int los = this.swiat.losowe.nextInt(4*SZANSA_NA_ROZMNOZENIE);
        Polozenie docelowe = new Polozenie(this.getPolozenie());

        if (los%SZANSA_NA_ROZMNOZENIE == 0)
        {
            if (los==0 && docelowe.x>0) { docelowe.x--; }
            if (los==SZANSA_NA_ROZMNOZENIE && docelowe.x < this.swiat.getWidth()-1) { docelowe.x++; }
            if (los==2*SZANSA_NA_ROZMNOZENIE && docelowe.y>0) { docelowe.y--; }
            if (los==3*SZANSA_NA_ROZMNOZENIE && docelowe.y < this.swiat.getHeight()-1) { docelowe.y++; }

            Organizm nowy = this.sklonuj(docelowe);
            if (this.swiat.getOrganizm(docelowe) == null)
            {
                this.swiat.dodajOrganizm(nowy,docelowe);
                this.swiat.komentator.info_o_rozprzestrzenieniu(this);
            }
        }

    }

    @Override
    public boolean kolizja(Organizm atakujacy)
    {
        this.setZywy(false);
        this.swiat.komentator.info_o_zjedzeniu(atakujacy,this);
        return true;
    }




}
