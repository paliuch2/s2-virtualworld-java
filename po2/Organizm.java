package pl.kamil.po2;

import java.awt.*;

import static pl.kamil.po2.Kierunki.*;

public abstract class Organizm {


    private int sila;
    private int inicjatywa;
    private final Color color;
    private boolean zywy;
    private int wiek;
   // private final String nazwa;
    private Polozenie polozenie;
    protected Swiat swiat;

    public Organizm(Swiat swiat, int sila, int inicjatywa, Color color) {
        this.swiat = swiat;
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.color = color;
        this.zywy = true;
        this.wiek = 0;
    }


    public int getSila() {
        return sila;
    }

    public int getInicjatywa() {
        return inicjatywa;
    }

    public Color getColor() {
        return color;
    }

    public boolean getZywy() {
        return zywy;
    }

    public int getWiek() {
        return wiek;
    }

    public Polozenie getPolozenie() {
        return polozenie;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    public void setWiek (int wiek) {
        this.wiek = wiek;
    }

    public void setPolozenie (Polozenie p) {
        this.polozenie = p;
    }

    public void setZywy (boolean zywy) {
        this.zywy = zywy;
    }

    public abstract String getNazwa();

    public abstract void akcja();

    public abstract boolean kolizja(Organizm atakujacy);

    public abstract Organizm sklonuj (Polozenie p);

    public abstract char getSymbol();

   public void getSasiedztwo(Organizm[] sasiedztwo) {

        if (polozenie.x > 0) {
            Polozenie nowe = new Polozenie(this.getPolozenie());
            nowe.x--;

            sasiedztwo[LEWY.getValue()] = this.swiat.getOrganizm(nowe); }

        if (polozenie.y > 0) {
            Polozenie nowe = new Polozenie(this.getPolozenie());
            nowe.y--;
            sasiedztwo[GORNY.getValue()] = this.swiat.getOrganizm(nowe);
        }

        if (polozenie.x < this.swiat.getWidth() - 1) {
            Polozenie nowe = new Polozenie(this.getPolozenie());
            nowe.x++;
            sasiedztwo[PRAWY.getValue()] = this.swiat.getOrganizm(nowe);
        }

        if (polozenie.y < this.swiat.getHeight() - 1) {
            Polozenie nowe = new Polozenie(this.getPolozenie());
            nowe.y++;
            sasiedztwo[DOLNY.getValue()] = this.swiat.getOrganizm(nowe);
        }

    }


}
