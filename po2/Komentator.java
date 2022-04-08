package pl.kamil.po2;

public class Komentator {

    private String komentarz;

    public void info_o_rozprzestrzenieniu (Organizm rozprzestrzeniajacy)
    {
        this.komentarz += "Organizm "+rozprzestrzeniajacy.getNazwa()+" rozprzestrzenił się! \n";
    }

    public void info_o_walce (Organizm wygrany, Organizm przegrany)
    {
        this.komentarz += wygrany.getNazwa()+" pokonał w walce: "+przegrany.getNazwa()+" \n";
    }

    public void info_o_odparciu_ataku(Organizm odpierajacy, Organizm atakujacy) {

        this.komentarz += odpierajacy.getNazwa()+" odpiera atak organizmu: "+atakujacy.getNazwa()+" \n";
    }

    public void info_o_zwiekszeniu_sily (Organizm zwiekszajacy, Organizm niszczony)
    {
        this.komentarz += zwiekszajacy.getNazwa()+" zjadł organizm "+niszczony.getNazwa()+" i zwiększył swoją siłę do "+zwiekszajacy.getSila()+"\n";
    }

    public void info_o_zjedzeniu (Organizm atakujacy, Organizm zjedzony)
    {
        this.komentarz += atakujacy.getNazwa()+" zjadł roślinę o nazwie: "+zjedzony.getNazwa()+" \n";
    }

    public void info_o_zjedzeniu_i_smierci (Organizm atakujacy, Organizm zjedzony)
    {
        this.komentarz += atakujacy.getNazwa()+" zjadł trującą roślinę o nazwie: "+zjedzony.getNazwa()+" i umiera! \n";
    }

    public void info_o_rozmnozeniu (Organizm rozmnozony)
    {
        this.komentarz += "Organizm "+rozmnozony.getNazwa()+" rozmnożył się! \n";
    }

    public void info_o_uruchomieniu_mocy ()
    {
        this.komentarz += "URUCHOMIONO CAŁOPALENIE! \n";
    }

    public void info_o_wyniku_calopalenia(Organizm niszczony)
    {
        this.komentarz += "Organizm "+niszczony.getNazwa()+" został zniszczony przy użyciu całopalenia! \n";
    }

    public void info_o_dzialaniu_barszczu(Organizm atakujacy, Organizm niszczony)
    {
        this.komentarz += "Organizm "+niszczony.getNazwa()+" znajduje się w pobliżu organizmu "+atakujacy.getNazwa()+" i umiera! \n";
    }

    public void info_o_braku_mocy (int x)
    {
        this.komentarz += "Uruchomienie mocy nie jest możliwe przez "+x+" tur \n";
    }

   public String wypisz ()
   {
       return komentarz;
   }

    public void wyczysc()
    {
        this.komentarz="";
    }

}
