package pl.kamil.po2.zwierze;

import pl.kamil.po2.*;

import java.awt.*;

public class Czlowiek extends Zwierze {
    private String nazwa;
    private int turaOstatniegoUzyciaMocy; // JAKI WIEK MIAL CZLOWIEK PODCZAS OSTATNIEGO UZYCIA MOCY
    private int mozliweNastepneUzycieMocy; // WIEK CZLOWIEKA UMOZLIWIAJACY MU UZYCIE MOCY
    private static final int SILA_CZLOWIEK = 5;
    private static final int INICJATYWA_CZLOWIEK = 4;
    private static final int TURA_UZYCIA_MOCY = 5;

    public Czlowiek(Swiat swiat) {
        super(swiat, SILA_CZLOWIEK, INICJATYWA_CZLOWIEK, Color.BLACK);
        this.nazwa = "Człowiek";
        this.turaOstatniegoUzyciaMocy = -TURA_UZYCIA_MOCY;
        this.mozliweNastepneUzycieMocy = 0;
    }


    @Override
    public void akcja() {

        Kierunki ruch = this.swiat.getKierunek();
        Polozenie p = new Polozenie(getPolozenie());
        boolean moc = this.swiat.getUruchomienieMocy();

       if (moc && getWiek()>=mozliweNastepneUzycieMocy)
       {
           this.turaOstatniegoUzyciaMocy = this.getWiek();
           this.mozliweNastepneUzycieMocy = this.getWiek()+2*TURA_UZYCIA_MOCY;
           this.swiat.komentator.info_o_uruchomieniu_mocy();
       }
       else if (moc)
       {
           int za_ile = mozliweNastepneUzycieMocy - this.getWiek();
           this.swiat.komentator.info_o_braku_mocy(za_ile);

       }

       this.swiat.setUruchomienieMocy(false);
       int czy_uzyc_mocy = this.getWiek() - turaOstatniegoUzyciaMocy;


       if (czy_uzyc_mocy < TURA_UZYCIA_MOCY)
        {
            Calopalenie();
        }

        if (ruch != null) {
            switch (ruch) {
                case LEWY:
                    if (p.x > 0) {
                        p.x--;
                        break;
                    }
                case GORNY:
                    if (p.y > 0) {
                        p.y--;
                        break;
                    }
                case PRAWY:
                    if (p.x < this.swiat.getWidth() - 1) {
                        p.x++;
                        break;
                    }
                case DOLNY:
                    if (p.y < this.swiat.getHeight() - 1) {
                        p.y++;
                        break;
                    }

            }
            this.swiat.move(getPolozenie(), p);
            this.swiat.ruch = null;
        }

        if (czy_uzyc_mocy < TURA_UZYCIA_MOCY)
        {
            Calopalenie();
        }

    }


    public void Calopalenie()
    {
        Organizm []sasiedztwo = { null, null, null, null };
        getSasiedztwo(sasiedztwo);

        for (int i = 0; i < 4; i++) {
            if (sasiedztwo[i] != null) {
                this.swiat.getOrganizm(sasiedztwo[i].getPolozenie()).setZywy(false);
                this.swiat.komentator.info_o_wyniku_calopalenia(sasiedztwo[i]);
                this.swiat.zabij(sasiedztwo[i].getPolozenie());
            }
        }

    }

    @Override
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public Organizm sklonuj(Polozenie p) {
        return null;
    }

    @Override
    protected boolean zgodnoscGatunkow(Organizm inny) {
        return false;
    }

    public int ostatniaMoc ()
    {
        return turaOstatniegoUzyciaMocy;
    }

    public int nastepnaMoc ()
    {
        return mozliweNastepneUzycieMocy;
    }

    public void setOstatniaMoc (int tura)
    {
        this.turaOstatniegoUzyciaMocy = tura;
    }

    public void setNastepnaMoc (int tura)
    {
        this.mozliweNastepneUzycieMocy = tura;
    }

    @Override
    public char getSymbol()
    {
        return 'X';
    }
}


