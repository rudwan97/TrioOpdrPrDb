package GUI;

import Account.Account;
import Account.AccountRepository;
import Aflevering.*;
import Connection.SqlConnection;
import Film.Film;
import Film.FilmRepository;
import Profiel.Profiel;
import Profiel.ProfielRepository;
import Serie.Serie;
import Serie.SerieRepository;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

import static javax.swing.SwingConstants.CENTER;

public class UserInterface implements Runnable {

    private JFrame frame;

    String home = "Beginscherm";
    String overzicht1 = "Overzicht1";
    String overzicht2 = "Overzicht2";
    String overzicht3 = "Overzicht3";
    String overzicht4 = "Overzicht4";
    String overzicht5 = "Overzicht5";
    String overzicht6 = "Overzicht6";
    String overzicht7 = "Overzicht7";

    int extraWindowWidth = 100;

    @Override
    public void run() {
        frame = new JFrame("Netflix Statistix");
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.getContentPane().setBackground(Color.red);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {

        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        // statisch frame aan de bovenkant ookwel de navigatie.
        JTabbedPane tabbedPane = new JTabbedPane();
        frame.add(tabbedPane, BorderLayout.NORTH);

        // Statisch JLabel voor de onderkant van de applicatie.
        JLabel labelDown = new JLabel("Netflix Statistix + Kevin van Loon + 23IVT1A1 + 2017-2018");
        frame.add(labelDown, BorderLayout.SOUTH);

        //Maakt alle overzichten aan door de bijbehorende functies aan te roepen,
        //en zet de achtergrond kleur op grijs.
        JPanel card1 = makeCard1();
        card1.setBackground(Color.darkGray);
        JPanel card2 = makeCard2();
        card2.setBackground(Color.DARK_GRAY);
        JPanel card3 = makeCard3();
        card3.setBackground(Color.DARK_GRAY);
        JPanel card4 = makeCard4();
        card4.setBackground(Color.DARK_GRAY);
        JPanel card5 = makeCard5();
        card5.setBackground(Color.DARK_GRAY);
        JPanel card6 = makeCard6();
        card6.setBackground(Color.DARK_GRAY);
        JPanel card7 = makeCard7();
        card7.setBackground(Color.DARK_GRAY);
        //JPanel card8 = makeCard8();

        //Voegt de tabbladen toe aan de TabbedPane.
        tabbedPane.addTab(home, card1);
        tabbedPane.addTab(overzicht1, card2);
        tabbedPane.addTab(overzicht2, card3);
        tabbedPane.addTab(overzicht3, card4);
        tabbedPane.addTab(overzicht4, card5);
        tabbedPane.addTab(overzicht5, card6);
        tabbedPane.addTab(overzicht6, card7);
       // tabbedPane.addTab(overzicht7, card8);

        //Zet de achtergrond kleuren van de tabbladen op wit.
        tabbedPane.setBackgroundAt(0, Color.WHITE);
        tabbedPane.setBackgroundAt(1, Color.WHITE);
        tabbedPane.setBackgroundAt(2, Color.WHITE);
        tabbedPane.setBackgroundAt(3, Color.WHITE);
        tabbedPane.setBackgroundAt(4, Color.WHITE);
        tabbedPane.setBackgroundAt(5, Color.WHITE);
        tabbedPane.setBackgroundAt(6, Color.WHITE);

        //Voegt de TabbedPane met alle overzichten toe aan de container.
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
        card1.add(label, BorderLayout.CENTER);
        return card1;
    }


    public JPanel makeCard2() {
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");
        JPanel card2 = new JPanel();

        card2.setLayout(new BoxLayout(card2, BoxLayout.Y_AXIS));
        JLabel info = new JLabel("Alle accounts met slechts een profiel");
        info.setForeground(Color.WHITE);

        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        card2.add(info);

        //Account met slecht een profiel
        AccountRepository accountRepository = new AccountRepository(connection);
        List<Account> accountList = new ArrayList<Account>();
        accountList = accountRepository.accountsWithOneProfile();
        for (Account t : accountList) {
            JLabel label2 = new JLabel();
            label2.setAlignmentY(Component.CENTER_ALIGNMENT);
            label2.setAlignmentX(Component.CENTER_ALIGNMENT);
            label2.setText(t.toString());
            label2.setForeground(Color.WHITE);
            card2.add(label2);
        }
        if (accountList.size() == 0){
            JLabel label3 = new JLabel("Er zijn op dit moment geen accounts met 1 profiel!");
            label3.setAlignmentY(Component.CENTER_ALIGNMENT);
            label3.setAlignmentX(Component.CENTER_ALIGNMENT);
            label3.setForeground(Color.WHITE);
            card2.add(label3);
        }
        return card2;
    }


    public JPanel makeCard3() {
        JPanel card3 = new JPanel();
        card3.setLayout(new BoxLayout(card3, BoxLayout.Y_AXIS));

        JLabel label2 = new JLabel("Kies in het menu hieronder een profiel om te kijken welk profiel welke films heeft bekeken!");
        JComboBox accountNames = makeNameComboboxForProfiles();
        accountNames.setName("accountNames");
        accountNames.setSelectedIndex(-1);
        JLabel label = new JLabel();
        JTextArea area = new JTextArea("Nog geen profiel gekozen!");
       // area.setFont(new Font("TimesRoman", Font.PLAIN, 30));

        area.setAlignmentX(Component.CENTER_ALIGNMENT);
        area.setAlignmentY(Component.CENTER_ALIGNMENT);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        accountNames.addActionListener(new Listener(accountNames, area));

        accountNames.setMaximumSize(new Dimension(9099,50));
        area.setForeground(Color.WHITE);
        area.setBackground(Color.darkGray);
        label2.setForeground(Color.WHITE);

        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        accountNames.setRenderer(dlcr);

        if (area.getText().equals("[]") || area.getText().equals("") || area.getText() == null){
            label.setText("Nog geen profiel gekozen!");
        }

        card3.add(label2);
        card3.add(accountNames);
        card3.add(area);

        return card3;
    }

    public JPanel makeCard4() {
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        JPanel card4 = new JPanel();
        card4.setLayout(new BoxLayout(card4, BoxLayout.Y_AXIS));
        Film film = null;

        FilmRepository filmRepository = new FilmRepository(connection);
        film = filmRepository.filmUnder16WithLongestDuration();


        JLabel twoLabel = new JLabel();
        twoLabel.setText("De film met de langste tijdsduur en onder 16 jaar is");
        JLabel twoLabel2 = new JLabel();
        twoLabel2.setText(film.getTitle() + " met een tijdsduur van " + film.getDuration() + " en leeftijdsindicatie van " + film.getRecommendedAge());

        twoLabel.setForeground(Color.WHITE);
        twoLabel2.setForeground(Color.WHITE);

        twoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        twoLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        twoLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        twoLabel2.setAlignmentY(Component.CENTER_ALIGNMENT);

        card4.add(twoLabel);
        card4.add(twoLabel2);

        if (film.getTitle().equals("Der Untergang")){
            ImageIcon image = new ImageIcon("C:\\Users\\Kevin van Loon\\Downloads\\DatabaseRepositoryExample (1)\\Test\\src\\GUI\\Undergang.jpg");
            JLabel label = new JLabel(image);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setAlignmentY(Component.CENTER_ALIGNMENT);
            card4.add(label);
        }

        return card4;
    }

    public JPanel makeCard5() {
        JPanel card5 = new JPanel();
        card5.setLayout(new BoxLayout(card5, BoxLayout.Y_AXIS));

        JComboBox filmTitles = makeFilmTitleCombobox();

        filmTitles.setSelectedIndex(-1);
        filmTitles.setName("filmTitles");
        JLabel help = new JLabel("Klik in het dropdown menu op een film !");
        JLabel field = new JLabel("Nog geen film gekozen!");
        help.setAlignmentX(Component.CENTER_ALIGNMENT);
        help.setAlignmentY(Component.CENTER_ALIGNMENT);
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setAlignmentY(Component.CENTER_ALIGNMENT);

        filmTitles.setMaximumSize(new Dimension(9099,50));

        filmTitles.addActionListener(new Listener(filmTitles, field));

        help.setForeground(Color.WHITE);
        field.setForeground(Color.WHITE);

        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        filmTitles.setRenderer(dlcr);

        card5.add(help);
        card5.add(filmTitles);
        card5.add(field);

        return card5;
    }

    public JPanel makeCard6() {
        JPanel card6 = new JPanel();

        card6.setLayout(new BoxLayout(card6,BoxLayout.Y_AXIS));
        JComboBox episodeTitleList = makeEpisodeTitleCombobox();
        episodeTitleList.setSelectedIndex(-1);
        episodeTitleList.setName("episodeTitleList");

        JTextArea area = new JTextArea("Nog geen aflevering gekozen");
        area.setBackground(Color.DARK_GRAY);
        area.setForeground(Color.WHITE);
        area.setAlignmentX(Component.CENTER_ALIGNMENT);
        area.setAlignmentY(Component.CENTER_ALIGNMENT);

        episodeTitleList.addActionListener(new Listener(episodeTitleList, area));

        JLabel info = new JLabel("Kies een serie om het gemiddelde percentage van de gekeken afleveringen te bekijken!");
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.setAlignmentY(Component.CENTER_ALIGNMENT);
        info.setForeground(Color.WHITE);


        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        episodeTitleList.setRenderer(dlcr);

        card6.add(info);
        card6.add(episodeTitleList);
        card6.add(area);


        return card6;
    }

    public JPanel makeCard7() {
        JPanel card7 = new JPanel();

        card7.setLayout(new BoxLayout(card7, BoxLayout.Y_AXIS));
        JComboBox profileNames = makeNameComboboxForProfiles();
        profileNames.setName("profileNames");
        JComboBox serieNames = makeEpisodeTitleCombobox();
        serieNames.setName("1");

        JTextArea resultArea = new JTextArea("Nog geen serie met profiel gekozen!");
        resultArea.setBackground(Color.DARK_GRAY);
        resultArea.setForeground(Color.WHITE);

        JLabel info = new JLabel("Kies eerst een serie en daarna een profiel om te kijken welke afleveringen het profiel heeft gekeken van de gekozen serie!");
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.setAlignmentY(Component.CENTER_ALIGNMENT);
        info.setForeground(Color.WHITE);

        JLabel serie = new JLabel("Kies een serie!");
        serie.setAlignmentX(Component.CENTER_ALIGNMENT);
        serie.setAlignmentY(Component.CENTER_ALIGNMENT);
        serie.setForeground(Color.WHITE);

        JLabel profile = new JLabel("Kies nu een profiel!");
        profile.setAlignmentX(Component.CENTER_ALIGNMENT);
        profile.setAlignmentY(Component.CENTER_ALIGNMENT);
        profile.setForeground(Color.WHITE);

        profileNames.setSelectedIndex(-1);
        serieNames.setSelectedIndex(-1);
        profileNames.addActionListener(new Listener(profileNames, resultArea, serieNames));

        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        profileNames.setRenderer(dlcr);
        serieNames.setRenderer(dlcr);

        card7.add(info);
        card7.add(serie);
        card7.add(serieNames);
        card7.add(profile);
        card7.add(profileNames);

        card7.add(resultArea);
        return card7;

    }

//    public JPanel makeCard8(){
//        JPanel card8 = new JPanel();
//        JComboBox series2 = makeEpisodeTitleCombobox();
//        series2.setName("5");
//        JLabel label = new JLabel("Nog geen serie gekozen");
//
//        series2.addActionListener(new Listener(series2, label));
//        card8.add(label);
//        card8.add(series2);
//        return card8;
//    }

    public JComboBox makeNameComboboxForProfiles() {
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        ProfielRepository profielRepository = new ProfielRepository(connection);

        ArrayList<Profiel> profiles = profielRepository.readAll();
        ArrayList<String> profielNames = new ArrayList<String>();

        for (Profiel c : profiles) {
            profielNames.add(c.getProfileName());
        }

        String[] array = new String[profiles.size()];

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
        List<Film> films = filmRepository.readAll();
        ArrayList<String> filmStrings = new ArrayList<>();

        for (Film c : films) {
            filmStrings.add(c.getTitle());
        }

        String[] filmArray = new String[films.size()];

        for (int i = 0; i < filmStrings.size(); i++) {
            filmArray[i] = filmStrings.get(i);
        }

        JComboBox filmTitleList = new JComboBox(filmArray);

        return filmTitleList;
    }

    //Maakt een combo box van serie namen
    public JComboBox makeEpisodeTitleCombobox(){
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        SerieRepository serieRepository = new SerieRepository(connection);
        List<Serie> episodes = serieRepository.readAll();
        List<String> episodeStrings = new ArrayList<>();

        for (Serie a : episodes){
            episodeStrings.add(a.getSerieName());
        }

        String[] episodesArray = new String[episodes.size()];

        for (int i = 0; i < episodeStrings.size(); i++){
            episodesArray[i] = episodeStrings.get(i);
        }

        JComboBox episodeTitleList = new JComboBox(episodesArray);
        return episodeTitleList;
    }
}

