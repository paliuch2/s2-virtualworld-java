package pl.kamil.po2;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Swiat swiat = new Swiat (20,20);

        MapaSwiata mapa;
        mapa = new MapaSwiata(swiat);

        mapa.setVisible(true);
        mapa.rysuj(swiat);

    }
}
