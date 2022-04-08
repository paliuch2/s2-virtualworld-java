package pl.kamil.po2.zwierze;
import pl.kamil.po2.*;
import pl.kamil.po2.roslina.Mlecz;

import java.awt.*;


public class Antylopa extends Zwierze{
    private String nazwa;
    private static final int SILA_ANTYLOPA = 4;
    private static final int INICJATYWA_ANTYLOPA = 4;
    private static final Color BROWN = new Color(102,51,0);

    public Antylopa (Swiat swiat) {
        super(swiat, SILA_ANTYLOPA, INICJATYWA_ANTYLOPA, BROWN);
        this.nazwa = "Antylopa";
    }

    @Override
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public Organizm sklonuj(Polozenie p) {
        return new Antylopa(swiat);
    }

   @Override
    public void akcja() {
        for (int i=0; i<2; i++)
        {
            super.akcja();
            if (this.getZywy() == false )
            break;
        }
    }

    @Override
    public boolean kolizja(Organizm atakujacy) {

        Polozenie stare = new Polozenie (getPolozenie());
        int x = this.swiat.losowe.nextInt(2);
        if (x==0)
        {
            Polozenie nowe = new Polozenie (sprawdzOkoliczneiObierzKierunek(0));

            if ((nowe.x == stare.x) && (nowe.y == stare.y)) x=1;
            else {
                this.setPolozenie(nowe);
                atakujacy.setPolozenie(stare);
                //this->swiat->dodajKomunikat("Antylopa ucieka przed " + atakujacy->getNazwa() + "!\n");
            }
        }
        if (x==1)
        {
            return super.kolizja(atakujacy);
        }
        return false;
    }

    @Override
    protected boolean zgodnoscGatunkow(Organizm inny) {
        return inny instanceof Antylopa;
    }

    @Override
    public char getSymbol()
    {
        return 'A';
    }



}
