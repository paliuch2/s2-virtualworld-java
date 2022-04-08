package pl.kamil.po2;

import java.awt.*;

import static pl.kamil.po2.Kierunki.*;

public abstract class Zwierze extends Organizm {

    public Zwierze(Swiat swiat, int sila, int inicjatywa, Color color) {
        super(swiat, sila, inicjatywa, color);
    }
    protected static final int PROG_PELNOLETNIOSCI = 18;

    @Override
    public void akcja()
    {
        int los = this.swiat.losowe.nextInt(4);
        Polozenie docelowe = new Polozenie(this.getPolozenie());
        boolean moved = false;

        while (moved == false) {
            if (los == 0 && docelowe.x > 0) { docelowe.x--; moved = true; }
            if (los == 1 && docelowe.y > 0) { docelowe.y--; moved = true; }
            if (los == 2 && docelowe.x < swiat.getWidth() - 1) { docelowe.x++; moved = true; }
            if (los == 3 && docelowe.y < swiat.getHeight() - 1) { docelowe.y++; moved = true; }
            los = this.swiat.losowe.nextInt(4);
        }
        Organizm na_docelowym = this.swiat.getOrganizm(docelowe);

        sprawdzDoceloweIPrzenies(na_docelowym, docelowe);

    }

    public void sprawdzDoceloweIPrzenies(Organizm na_docelowym, Polozenie docelowe)
    {
        if (na_docelowym != null)
        {
            if (zgodnoscGatunkow(na_docelowym) == true) {
                if (this.getWiek() > PROG_PELNOLETNIOSCI && na_docelowym.getWiek() > PROG_PELNOLETNIOSCI)
                {
                    stworzPotomka(getPolozenie());
                }
            }
            else
            {
                this.swiat.move(getPolozenie(), docelowe);
            }
        }
        else
        {
            this.swiat.move(getPolozenie(), docelowe);
        }
    }


    @Override
    public boolean kolizja(Organizm atakujacy)
    {
       if (atakujacy.getSila() >= this.getSila())
       {
           this.swiat.komentator.info_o_walce(atakujacy,this);
           this.setZywy(false);
           return true;
       }
       else
       {
           this.swiat.komentator.info_o_walce(this,atakujacy);
           atakujacy.setZywy(false);
           return false;
       }
    }

    protected boolean stworzPotomka(Polozenie rodzic)
    {
        Polozenie potomek = new Polozenie (this.sprawdzOkoliczneiObierzKierunek(0));

        if (!(rodzic.x == potomek.x && rodzic.y == potomek.y))
        {
            Organizm nowy = sklonuj(rodzic);
         //   nowy.setPolozenie(potomek);
            this.swiat.komentator.info_o_rozmnozeniu(this);
            this.swiat.dodajOrganizm(nowy, potomek);
            return true;
        }
        return false;
    }

    protected abstract boolean zgodnoscGatunkow(Organizm inny);

    protected Polozenie sprawdzOkoliczneiObierzKierunek(int silaWiekszaNiz) {

        Organizm sasiedztwo[] = { null, null, null, null };
        boolean dostepne_pole[] = { true, true, true, true };
        getSasiedztwo(sasiedztwo);
        Polozenie docelowe = new Polozenie (this.getPolozenie());

        for (int i = 0; i < 4; i++) {
            if (sasiedztwo[i] != null) {
                if (sasiedztwo[i].getSila() > silaWiekszaNiz) { dostepne_pole[i] = false; }
            }
        }

        if (docelowe.x < 1) dostepne_pole[LEWY.getValue()] = false;
        if (docelowe.y < 1) dostepne_pole[GORNY.getValue()] = false;
        if (docelowe.x >= swiat.getWidth() - 1) dostepne_pole[PRAWY.getValue()] = false;
        if (docelowe.y >= swiat.getHeight() - 1) dostepne_pole[DOLNY.getValue()] = false;

        boolean istniejeWolne = false;
        for (int i = 0; i < 4; i++) {
            if (dostepne_pole[i] == true) {
                istniejeWolne = true;
                break;
            }
        }
        if (istniejeWolne == false) return getPolozenie();

        boolean moved = false;
        int r = this.swiat.losowe.nextInt(4);
        while (moved == false) {
            if (istniejeWolne == true && dostepne_pole[r] == true) {
                if (r == 0) {
                    docelowe.x--;
                    moved = true;
                }
                if (r == 1) {
                    docelowe.y--;
                    moved = true;
                }
                if (r == 2) {
                    docelowe.x++;
                    moved = true;
                }
                if (r == 3) {
                    docelowe.y++;
                    moved = true;
                }
            }
            r = this.swiat.losowe.nextInt(4);
        }

        return docelowe;
    }

}
