package pl.kamil.po2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

import static pl.kamil.po2.Kierunki.*;

public class MapaSwiata extends JFrame implements KeyListener {

    private class Pole extends JPanel {

        private Color color;

        void setColor(Color c) {
            this.color = c;
        }

        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);
            g2d.fillRect(3, 3, 60, 60);
        }
    }

    private  JPanel wizualizacja = new JPanel();
    private JPanel obsluga = new JPanel();
    private JPanel legend = new JPanel();
    private JPanel komentarz = new JPanel();
    private final String KATALOG_ZAPISU= "save/";
    private final int HEIGHT = 1024;
    private final int WIDTH = 1024;
    public Swiat swiat;

    public MapaSwiata(Swiat swiat) {
        super("KAMIL PALUSZEWSKI 180194");
        this.swiat = swiat;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocation(200, 0);
        setLayout(new BorderLayout());

        wizualizacja.setLayout(new GridLayout(swiat.getHeight(), swiat.getWidth()));
        add(wizualizacja, BorderLayout.CENTER);

        add(obsluga, BorderLayout.PAGE_START);
        dodajPrzyciski(obsluga, swiat);

        legend.setLayout(new GridLayout(12, 2));
        legend.setPreferredSize(new Dimension(WIDTH/6, HEIGHT));
        add(legend, BorderLayout.LINE_END);

        add(komentarz, BorderLayout.PAGE_END);
        komentarz.setPreferredSize(new Dimension(WIDTH, HEIGHT/5));

        Color[] c = {Color.BLUE, Color.YELLOW, Color.RED, new Color(153, 0, 153), Color.GREEN, Color.WHITE, Color.GRAY, new Color(102, 51, 0), Color.DARK_GRAY, new Color(255, 102, 0), Color.CYAN, Color.BLACK};
        String[] s = {"BarszczSosnowskiego", "Mlecz", "Guarana", "WilczeJagody", "Trawa", "Owca", "Wilk", "Antylopa", "Zolw", "Lis", "CyberOwca", "Człowiek"};

        for (int i = 0; i < 12; i++) {
            Pole r = new Pole();
            r.setColor(c[i]);
            legend.add(r);
            legend.add(new JLabel(s[i]));
        }
    }

    private void dodajPrzyciski(JPanel obsluga, Swiat swiat) {
        JButton tura = (new JButton("Wykonaj ture"));
        obsluga.add(tura);
        JButton moc = (new JButton("Aktywuj moc"));
        obsluga.add(moc);
        JButton zapis = (new JButton("Zapisz stan"));
        obsluga.add(zapis);
        JButton odczyt = (new JButton("Wczytaj stan z pliku"));
        obsluga.add(odczyt);
        tura.addKeyListener(this);
        moc.addKeyListener(this);
        zapis.addKeyListener(this);
        odczyt.addKeyListener(this);

        moc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                swiat.setUruchomienieMocy(true);
                return;
            }
        });

        tura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                swiat.WykonajTure();
                setVisible(true);
                rysuj(swiat);
            }
        });


        zapis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                String name = JOptionPane.showInputDialog(zapis, "Podaj nazwę pliku zapisu", "Zapis świata", JOptionPane.PLAIN_MESSAGE);

                zapisz_do_pliku(name, swiat);
            }
        });

        odczyt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                String name = JOptionPane.showInputDialog(zapis, "Podaj ścieżkę do pliku", "Wczytaj świat", JOptionPane.PLAIN_MESSAGE);
                String sciezka = KATALOG_ZAPISU+name+".txt";

                try{
                    Path path = Paths.get(sciezka);

                    if (name != null) {
                        List<String> wczytane = Files.readAllLines(path, StandardCharsets.UTF_8);
                        swiat.wczytaj(wczytane);
                    }

            } catch (IOException e) {
                    e.printStackTrace();
                }
            }});

    }


    private void zapisz_do_pliku(String name, Swiat swiat) {
        String sciezka = KATALOG_ZAPISU+name+".txt";
        PrintWriter to_save = null;
        String s = swiat.zapisz();
        try {
            to_save = new PrintWriter(sciezka);
            to_save.print(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (to_save != null)
            {
                to_save.close();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.swiat.setKierunek(GORNY);
                break;
            case KeyEvent.VK_DOWN:
                this.swiat.setKierunek(DOLNY);
                break;
            case KeyEvent.VK_LEFT:
                this.swiat.setKierunek(LEWY);
                break;
            case KeyEvent.VK_RIGHT:
                this.swiat.setKierunek(PRAWY);
                break;
            default:
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void rysuj(Swiat swiat) {
        wizualizacja.removeAll();
        wizualizacja.repaint();
        wizualizacja.revalidate();
        wizualizacja.setLayout(new GridLayout(swiat.getHeight(), swiat.getWidth()));
        for (int iy = 0; iy < swiat.getHeight(); iy++) {
            for (int ix = 0; ix < swiat.getWidth(); ix++) {
                Pole r = new Pole();

                if (swiat.world[ix][iy] == null) {
                    r.setColor(Color.LIGHT_GRAY);
                } else {
                    r.setColor(swiat.world[ix][iy].getColor());
                }
                wizualizacja.add(r);
            }
        }
        komentarz.removeAll();
        komentarz.repaint();
        komentarz.revalidate();

        JTextArea txt = new JTextArea(this.swiat.komentator.wypisz());
        txt.setEditable(false);
        komentarz.add (txt);

        setVisible(true);
    }
}


