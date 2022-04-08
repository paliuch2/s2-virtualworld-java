package pl.kamil.po2.zwierze;
import pl.kamil.po2.*;
import pl.kamil.po2.roslina.Mlecz;

import java.awt.*;

public class Lis extends Zwierze {
    private String nazwa;
    private static final int SILA_LIS = 3;
    private static final int INICJATYWA_LIS = 7;
    private static final Color ORANGE = new Color(255,102,0);

    public Lis (Swiat swiat) {
        super(swiat, SILA_LIS, INICJATYWA_LIS, ORANGE);
        this.nazwa = "Lis";
    }

    @Override
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public Organizm sklonuj(Polozenie p) {
        return new Lis(swiat);
    }

    @Override
    public void akcja() {
        Polozenie docelowe = new Polozenie (sprawdzOkoliczneiObierzKierunek(this.getSila()));
        Organizm na_docelowym = this.swiat.getOrganizm(docelowe);

        sprawdzDoceloweIPrzenies(na_docelowym,docelowe);
    }


    @Override
    protected boolean zgodnoscGatunkow(Organizm inny) {
            return inny instanceof Lis;
    }

    @Override
    public char getSymbol()
    {
        return 'L';
    }
}
