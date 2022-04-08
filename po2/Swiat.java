package pl.kamil.po2;

import pl.kamil.po2.roslina.*;
import pl.kamil.po2.zwierze.*;

import javax.swing.*;
import java.util.*;

public class Swiat {

    private int x;
    private int y;
    private boolean uruchomienieMocy = false;
    private List<Organizm> organizmy = new LinkedList<>();
    public Kierunki ruch;
    public Organizm[][] world;
    public final Random losowe = new Random();
    public Komentator komentator = new Komentator();

    public Swiat(int x, int y) {
        this.x = x;
        this.y = y;
        world = new Organizm[x][y];
        InicjujSwiat();
    }

    private void InicjujSwiat() {
        int m = (int) Math.ceil(((double) x * (double) y) / 200.0);

        int[] ile = new int[11];
        for (int i = 0; i < 11; i++) {
            ile[i] = losowe.nextInt(m) + 2;
        }
        for (int i = 0; i < ile[0]; i++) {
            Polozenie p = losujPolozenie();
            dodajOrganizm(new Wilk(this), p);
        }
        for (int i = 0; i < ile[1]; i++) {
            Polozenie p = losujPolozenie();
            dodajOrganizm(new Owca(this), p);
        }
        for (int i = 0; i < ile[2]; i++) {
            Polozenie p = losujPolozenie();
            dodajOrganizm(new Lis(this), p);
        }
        for (int i = 0; i < ile[3]; i++) {
            Polozenie p = losujPolozenie();
            dodajOrganizm(new Antylopa(this), p);
        }
        for (int i = 0; i < ile[4]; i++) {
            Polozenie p = losujPolozenie();
            dodajOrganizm(new Zolw(this), p);
        }
        for (int i = 0; i < ile[5]; i++) {
            Polozenie p = losujPolozenie();
            dodajOrganizm(new Trawa(this), p);
        }
        for (int i = 0; i < ile[6]; i++) {
            Polozenie p = losujPolozenie();
            dodajOrganizm(new Mlecz(this), p);
        }
        for (int i = 0; i < ile[7]; i++) {
            Polozenie p = losujPolozenie();
            dodajOrganizm(new Guarana(this), p);
        }
        for (int i = 0; i < ile[8]; i++) {
            Polozenie p = losujPolozenie();
            dodajOrganizm(new WilczeJagody(this), p);
        }
        for (int i = 0; i < ile[9]; i++) {
            Polozenie p = losujPolozenie();
            dodajOrganizm(new BarszczSosnowskiego(this), p);
        }
        for (int i = 0; i < ile[10]; i++) {
            Polozenie p = losujPolozenie();
            dodajOrganizm(new CyberOwca(this), p);
        }
        Polozenie p = losujPolozenie();
        dodajOrganizm(new Czlowiek(this), p);

    }

    private void reinicjuj (int x, int y) {
        this.x = x;
        this.y = y;
        world = new Organizm[x][y];
        organizmy = new LinkedList<>();
        ruch = Kierunki.LEWY;

    }

    private void sortujPoInicjatywie() {
        int length = organizmy.size();
        List<Organizm> przezyly = new LinkedList<>();

        for (Organizm z : organizmy) {
            if (z.getZywy()) {
                boolean added = false;
                for (Organizm o : przezyly) {
                    if (z.getInicjatywa() > o.getInicjatywa()) {
                        przezyly.add(przezyly.indexOf(o), z);
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    przezyly.add(z);
                }
            }
        }

        organizmy = przezyly;
    }


    public void WykonajTure() {

        if (this.ruch != null) {
            sortujPoInicjatywie();
            komentator.wyczysc();
            int length = organizmy.size();

            for (int i = 0; i < length; i++) {
                Organizm x = organizmy.get(i);
                if (x.getZywy() == true) {
                    x.akcja();
                    x.setWiek(x.getWiek() + 1);
                }
            }
        }

    }


    public void dodajOrganizm(Organizm tworzony, Polozenie p) {
        if (world[p.x][p.y] == null) {
            world[p.x][p.y] = tworzony;
            tworzony.setPolozenie(p);
            organizmy.add(tworzony);
        }
    }

    public Polozenie losujPolozenie() {
        int a, b;
        do {
            a = losowe.nextInt(x);
            b = losowe.nextInt(y);
            //  a=4;
            //   b=1;
        } while (world[a][b] != null);

        Polozenie tmp = new Polozenie();
        tmp.x = a;
        tmp.y = b;

        return tmp;
    }

    public void move(Polozenie from, Polozenie to) {
        Organizm ruszajacy_sie = world[from.x][from.y];
        world[from.x][from.y] = null;

        if (world[to.x][to.y] == null) {
            ruszajacy_sie.setPolozenie(to);
            world[to.x][to.y] = ruszajacy_sie;
        } else {
            Organizm oponent = world[to.x][to.y];
            boolean wynik_kolizji = oponent.kolizja(ruszajacy_sie);

            if (wynik_kolizji) {
                ruszajacy_sie.setPolozenie(to);
                world[to.x][to.y] = ruszajacy_sie;
            } else if (!wynik_kolizji && ruszajacy_sie.getZywy()) {
                world[to.x][to.y] = null;
                Polozenie a = oponent.getPolozenie();
                world[a.x][a.y] = oponent;
                Polozenie b = ruszajacy_sie.getPolozenie();
                world[b.x][b.y] = ruszajacy_sie;

            }
        }

    }

    public int getWidth() {
        return x;
    }

    public int getHeight() {
        return y;
    }

    public Organizm getOrganizm(Polozenie p) {
        return world[p.x][p.y];
    }

    public void zabij(Polozenie p) {
        world[p.x][p.y] = null;
    }

    public void setKierunek(Kierunki k) {
        this.ruch = k;
    }

    public Kierunki getKierunek() {
        return ruch;
    }

    public void setUruchomienieMocy(boolean u) {
        this.uruchomienieMocy = u;
    }

    public boolean getUruchomienieMocy() {
        return uruchomienieMocy;
    }

    public String zapisz() {
        String s="";
        s += x + "\n" + y + "\n";
        for (Organizm o : organizmy) {
            if (o.getZywy()) {
                s += o.getSymbol() + " " + o.getSila() + " " + o.getInicjatywa() + " " + o.getWiek() + " " + o.getPolozenie().x + " "+o.getPolozenie().y+" ";
                if (o instanceof Czlowiek) {
                    int last = ((Czlowiek) o).ostatniaMoc();
                    int next = ((Czlowiek) o).nastepnaMoc();
                        s += last + " " + next;
                }
                s += "\n";
            }

        }
        return s;
    }

    public void wczytaj(List<String> wczytane) {
        int width = Integer.parseInt(wczytane.get(0));
        int height = Integer.parseInt(wczytane.get(1));
        this.reinicjuj(width, height);


        for (int i = 2; i < wczytane.size(); ++i)
            {
               wczytajOrganizm(wczytane.get(i));
            }

    }

    private void wczytajOrganizm(String s){
        Organizm o = null;
        String holder[] = s.split(" ");
        char type = holder[0].charAt(0);

        switch (type){
            case 'B':
                o = new BarszczSosnowskiego(this);
                break;
            case 'G':
                o = new Guarana(this);
                break;
            case 'M':
                o = new Mlecz(this);
                break;
            case 'T':
                o = new Trawa(this);
                break;
            case 'J':
                o = new WilczeJagody(this);
                break;
            case 'A':
                o = new Antylopa(this);
                break;
            case 'C':
                o = new CyberOwca(this);
                break;
            case 'L':
                o = new Lis(this);
                break;
            case 'O':
                o = new Owca(this);
                break;
            case 'W':
                o = new Wilk(this);
                break;
            case 'Z':
                o = new Zolw(this);
                break;
            case 'X':
                o = new Czlowiek(this);
                ((Czlowiek)o).setOstatniaMoc(Integer.parseInt(holder[6]));
                ((Czlowiek)o).setNastepnaMoc(Integer.parseInt(holder[7]));
                ruch = null;
                break;
        }

        Polozenie w = new Polozenie();
        w.x = Integer.parseInt(holder[4]);
        w.y = Integer.parseInt(holder[5]);

      if (o != null)
          o.setSila(Integer.parseInt(holder[1]));
        o.setWiek(Integer.parseInt(holder[3]));
        o.setPolozenie(w);
        dodajOrganizm(o,w);
    }

    public void setWidth(int x)
    {
        this.x = x;
    }

    public void setHeight(int y)
    {
        this.y = y;
    }

    public List<Organizm> getListaOrganizmow () {
        return organizmy;
    }
}
