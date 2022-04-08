package pl.kamil.po2.zwierze;


import pl.kamil.po2.*;
import pl.kamil.po2.roslina.BarszczSosnowskiego;
import java.awt.*;
import java.util.List;


public class CyberOwca extends Zwierze {

    private String nazwa;
    private static final int SILA_CYBER = 11;
    private static final int INICJATYWA_CYBER = 4;

    public CyberOwca(Swiat swiat) {
         super(swiat, SILA_CYBER, INICJATYWA_CYBER, Color.CYAN);
         this.nazwa = "Cyberowca";
    }

    @Override
    public void akcja()
    {
        Organizm najblizszy_barszcz = null;
        Polozenie owca = new Polozenie(getPolozenie());
        int najmniejszy_dyst = swiat.getHeight()*swiat.getWidth();

        List<Organizm> org = swiat.getListaOrganizmow();

        for (Organizm o : org)
        {
            if (o instanceof BarszczSosnowskiego)
            {

                Polozenie barszcz = new Polozenie(o.getPolozenie());
                int x = Math.abs(owca.x-barszcz.x);
                int y = Math.abs(owca.y-barszcz.y);
                int dystans = x+y;
                if (dystans < najmniejszy_dyst)
                {
                    najblizszy_barszcz = o;

                    najmniejszy_dyst = dystans;
                }
            }
        }

        if(najblizszy_barszcz != null) {
            Polozenie najblizszy = new Polozenie(najblizszy_barszcz.getPolozenie());

            boolean[] dostepne_pole = {false, false, false, false};
            if (najblizszy.x < owca.x) {
                dostepne_pole[Kierunki.LEWY.getValue()] = true;
            }
            if (najblizszy.y < owca.y) {
                dostepne_pole[Kierunki.GORNY.getValue()] = true;
            }
            if (najblizszy.x > owca.x) {
                dostepne_pole[Kierunki.PRAWY.getValue()] = true;
            }
            if (najblizszy.y > owca.y) {
                dostepne_pole[Kierunki.DOLNY.getValue()] = true;
            }

            int los = this.swiat.losowe.nextInt(4);
            Polozenie docelowe = new Polozenie(this.getPolozenie());
            boolean moved = false;

            while (moved == false) {
                if (los == Kierunki.LEWY.getValue() && dostepne_pole[Kierunki.LEWY.getValue()] == true) {
                    docelowe.x--;
                    moved = true;
                }
                if (los == Kierunki.GORNY.getValue() && dostepne_pole[Kierunki.GORNY.getValue()] == true) {
                    docelowe.y--;
                    moved = true;
                }
                if (los == Kierunki.PRAWY.getValue() && dostepne_pole[Kierunki.PRAWY.getValue()] == true) {
                    docelowe.x++;
                    moved = true;
                }
                if (los == Kierunki.DOLNY.getValue() && dostepne_pole[Kierunki.DOLNY.getValue()] == true) {
                    docelowe.y++;
                    moved = true;
                }
                los = this.swiat.losowe.nextInt(4);
            }
            Organizm na_docelowym = this.swiat.getOrganizm(docelowe);

            sprawdzDoceloweIPrzenies(na_docelowym, docelowe);
        }
        else
        {
            super.akcja();
        }

    }

    @Override
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public Organizm sklonuj(Polozenie p) {
        return new CyberOwca(swiat);
    }

    @Override
    protected boolean zgodnoscGatunkow(Organizm inny) {
        return inny instanceof CyberOwca;
    }

    @Override
    public char getSymbol()
    {
        return 'C';
    }
}
