package pl.kamil.po2;

import java.awt.*;

public abstract class RoslinaTrujaca extends Roslina{



    public RoslinaTrujaca(Swiat swiat, int sila, int inicjatywa, Color color) {
        super(swiat, sila, inicjatywa, color);
    }


    @Override
    public boolean kolizja(Organizm atakujacy)
    {
        atakujacy.setZywy(false);
        this.setZywy(false); // jak zwierze zje nawet i rosline trujaca, to ginie i on i ta roslina
        this.swiat.zabij(this.getPolozenie());
        this.swiat.komentator.info_o_zjedzeniu_i_smierci(atakujacy,this);
        return false;
    }




}
