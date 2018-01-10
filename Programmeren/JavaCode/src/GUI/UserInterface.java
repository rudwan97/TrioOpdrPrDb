package GUI;

import Account.Account;
import Account.AccountRepository;
import Connection.SqlConnection;
import Film.Film;
import Film.FilmRepository;
import Profiel.Profiel;
import Profiel.ProfielRepository;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class UserInterface implements Runnable {

    private JFrame frame;


    String overzicht1 = "Overzicht1";
    String overzicht2 = "Overzicht2";
    String overzicht3 = "Overzicht3";
    String overzicht4 = "Overzicht4";
    String overzicht5 = "Overzicht5";
    String overzicht6 = "Overzicht6";
    int extraWindowWidth = 100;

    public UserInterface() {

    }

    @Override
    public void run() {
        frame = new JFrame("Netflix Statistix");
        frame.setPreferredSize(new Dimension(1000, 600));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {

        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        // statisch frame aan de bovenkant ookwel de navigatie
        JTabbedPane tabbedPane = new JTabbedPane();
        frame.add(tabbedPane, BorderLayout.NORTH);

        // Statisch JLabel voor de onderkant van de applicatie
        JLabel labelDown = new JLabel("Netflix Statistix Namen + Klas + Schooljaar");
        frame.add(labelDown, BorderLayout.SOUTH);

        JPanel card1 = makeCard1();
        // JPanel voor overzicht2
        JPanel card2 = makeCard2();

        JPanel card3 = makeCard3();

        JPanel card4 = makeCard4();

        JPanel card5 = makeCard5();

        JPanel card6 = new JPanel();

        tabbedPane.addTab(overzicht1, card1);
        tabbedPane.addTab(overzicht2, card2);
        tabbedPane.addTab(overzicht3, card3);
        tabbedPane.addTab(overzicht4, card4);
        tabbedPane.addTab(overzicht5, card5);
        tabbedPane.addTab(overzicht6, card6);

        container.add(tabbedPane);
    }


    public JFrame getFrame() {
        return frame;
    }

    public JPanel makeCard1() {
        // maakt een nieuwe JPanel aan
        JPanel card1 = new JPanel();
        // voegt de netflix image toe
        ImageIcon image = new ImageIcon("C:\\Users\\Kevin van Loon\\Downloads\\DatabaseRepositoryExample (1)\\Test\\src\\GUI\\betflix.png");
        JLabel label = new JLabel(image);
        card1.add(label);
        return card1;
    }

    //
    public JPanel makeCard2() {
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");
        JPanel card2 = new JPanel();

        card2.setLayout(new BoxLayout(card2, BoxLayout.PAGE_AXIS));
        card2.add(new JLabel("Alle accounts met slechts een profiel"));
        //Account met slecht een profiel
        AccountRepository accountRepository = new AccountRepository(connection);
        ArrayList<Account> accountList = new ArrayList<Account>();
        accountList = accountRepository.accountsWithOneProfile();
        for (Account t : accountList) {
            JLabel label2 = new JLabel();
            label2.setText(t.toString());
            card2.add(label2);
        }
        return card2;
    }


    public JPanel makeCard3() {
        JPanel card3 = new JPanel();
        card3.setLayout(new BorderLayout());
        JComboBox accountNames = makeNameComboboxForProfiles();
        accountNames.setName("accountNames");

        JLabel label = new JLabel();
        accountNames.addActionListener(new Listener(accountNames, label));
        card3.add(accountNames, BorderLayout.NORTH);
        card3.add(label, BorderLayout.CENTER);

        return card3;
    }

    public JPanel makeCard4() {
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        JPanel card4 = new JPanel();
        Film film = null;

        FilmRepository filmRepository = new FilmRepository(connection);
        film = filmRepository.filmUnder16WithLongestDuration();

        JLabel twoLabel = new JLabel();
        twoLabel.setText("De film met de langste tijdsduur en onder 16 jaar is");
        JLabel twoLabel2 = new JLabel();
        twoLabel2.setText(film.getTitle() + " met een tijdsduur van " + film.getDuration() + " en leeftijdsindicatie van " + film.getRecommendedAge());
        card4.add(twoLabel);
        card4.add(twoLabel2);

        return card4;
    }

    public JPanel makeCard5() {
        JPanel card5 = new JPanel();
        card5.setLayout(new BorderLayout());
        JComboBox filmTitles = makeFilmTitleCombobox();
        filmTitles.setName("filmTitles");
        JLabel field = new JLabel("Klik in het dropdown menu op een film !");
        filmTitles.addActionListener(new Listener(filmTitles, field));
        card5.add(filmTitles, BorderLayout.NORTH);
        card5.add(field, BorderLayout.AFTER_LAST_LINE);

        return card5;
    }

    public JPanel makeCard6() {
        JPanel card6 = new JPanel();
        return card6;
    }

    public JComboBox makeNameComboboxForProfiles() {
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        ProfielRepository profielRepository = new ProfielRepository(connection);

        ArrayList<Profiel> profiles = profielRepository.readAll();
        ArrayList<String> profielNames = new ArrayList<String>();

        for (Profiel c : profiles) {
            profielNames.add(c.getProfileName());
        }

        String[] array = new String[15];

        for (int i = 0; i < profielNames.size(); i++) {
            array[i] = profielNames.get(i);
        }

        JComboBox profileNameList = new JComboBox(array);

        return profileNameList;
    }

    public JComboBox makeFilmTitleCombobox() {
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        FilmRepository filmRepository = new FilmRepository(connection);
        ArrayList<Film> films = filmRepository.readAll();
        ArrayList<String> filmStrings = new ArrayList<>();

        for (Film c : films) {
            filmStrings.add(c.getTitle());
        }

        String[] filmArray = new String[25];

        for (int i = 0; i < filmStrings.size(); i++) {
            filmArray[i] = filmStrings.get(i);
        }

        JComboBox filmTitleList = new JComboBox(filmArray);

        return filmTitleList;
    }
}

