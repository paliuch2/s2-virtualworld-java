package pl.kamil.po2.zwierze;
import pl.kamil.po2.*;
import pl.kamil.po2.roslina.Mlecz;

import java.awt.*;


public class Owca extends Zwierze {

    private String nazwa;
    private static final int SILA_OWCA = 4;
    private static final int INICJATYWA_OWCA = 4;

    public Owca (Swiat swiat) {
        super(swiat, SILA_OWCA, INICJATYWA_OWCA, Color.WHITE);
        this.nazwa = "Owca";
    }

    @Override
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public Organizm sklonuj(Polozenie p) {
        return new Owca(swiat);
    }

    @Override
    protected boolean zgodnoscGatunkow(Organizm inny) {
        return inny instanceof Owca;
    }

    @Override
    public char getSymbol()
    {
        return 'O';
    }
}
