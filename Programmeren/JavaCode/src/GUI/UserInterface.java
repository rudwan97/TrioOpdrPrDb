package GUI;

import Account.Account;
import Account.AccountRepository;
import Connection.SqlConnection;
import Film.Film;
import Film.FilmRepository;
import Profiel.Profiel;
import Profiel.ProfielRepository;
import Serie.Serie;
import Serie.SerieRepository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class UserInterface implements Runnable {

    private JFrame frame;
    private String SqlDatabaseLink = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;";

    @Override
    public void run() {

        //Zet de titel van het Jframe in
        frame = new JFrame("Netflix Statistix");
        //Stelt de grote van eht Jframe in
        frame.setPreferredSize(new Dimension(1000, 600));
        //Jframe installing (Background color etc.)
        frame.getContentPane().setBackground(Color.red);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {

        SqlConnection connection = new SqlConnection();
        connection.connectDatabase(SqlDatabaseLink);

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

        String home = "Beginscherm";
        String overzicht1 = "Overzicht1";
        String overzicht2 = "Overzicht2";
        String overzicht3 = "Overzicht3";
        String overzicht4 = "Overzicht4";
        String overzicht5 = "Overzicht5";
        String overzicht6 = "Overzicht6";

        //Voegt de tabbladen toe aan de TabbedPane.
        tabbedPane.addTab(home, card1);
        tabbedPane.addTab(overzicht1, card2);
        tabbedPane.addTab(overzicht2, card3);
        tabbedPane.addTab(overzicht3, card4);
        tabbedPane.addTab(overzicht4, card5);
        tabbedPane.addTab(overzicht5, card6);
        tabbedPane.addTab(overzicht6, card7);


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

    //maakt card1 (Beginscherm)
    public JPanel makeCard1() {
        //Voegt het netflix logo toe aan het beginscherm
        JPanel card1 = new JPanel();
        ImageIcon image = new ImageIcon("src\\GUI\\betflix.png");
        JLabel label = new JLabel(image);
        card1.add(label, BorderLayout.CENTER);

        return card1;
    }


    //maakt card2 (Overzicht1)
    public JPanel makeCard2() {
        //Maakt connectie met database
        //Maakt een nieuw jpanel en stelt een layout in
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase(SqlDatabaseLink);
        JPanel card2 = new JPanel();
        card2.setLayout(new BoxLayout(card2, BoxLayout.Y_AXIS));

        //Pagina header en instellingen
        JLabel info = new JLabel("Alle accounts met slechts een profiel");
        info.setForeground(Color.WHITE);
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.setFont(new Font("TimesRoman", Font.PLAIN, 19));
        card2.add(info);

        //Haalt alle accounts met maar 1 profiel uit de database
        AccountRepository accountRepository = new AccountRepository(connection);
        List<Account> accountList = new ArrayList<Account>();
        accountList = accountRepository.accountsWithOneProfile();
        for (Account t : accountList) {
            //Maakt een nieuw jlabel aan als er een account gevonden in
            JLabel label2 = new JLabel();
            //Zorgt ervoor dat de tekst in het midden staat en wit is
            label2.setAlignmentY(Component.CENTER_ALIGNMENT);
            label2.setAlignmentX(Component.CENTER_ALIGNMENT);
            label2.setText(t.toString());
            label2.setForeground(Color.WHITE);
            card2.add(label2);
        }
        //Als er geen accoutn gevonden is dan weergeeft de label de onderstaande tekst
        if (accountList.size() == 0){
            JLabel label3 = new JLabel("Er zijn op dit moment geen accounts met 1 profiel!");
            label3.setAlignmentY(Component.CENTER_ALIGNMENT);
            label3.setAlignmentX(Component.CENTER_ALIGNMENT);
            label3.setForeground(Color.WHITE);
            card2.add(label3);
        }

        return card2;
    }


    //Maakt card3 (Overzicht2)
    public JPanel makeCard3() {

        //Maakt een nieuw jpanel en stelt een layout in
        JPanel card3 = new JPanel();
        card3.setLayout(new BoxLayout(card3, BoxLayout.Y_AXIS));


        //Pagina header
        JLabel label2 = new JLabel("Kies in het menu hieronder een profiel om te kijken welk profiel welke films heeft bekeken!");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setForeground(Color.WHITE);

        //Combobox van profielnamen en stelt een naam in voor de combobox en zet de geleselecteerde index op -1 zodat er niks instaat
        JComboBox accountNames = makeNameComboboxForProfiles();
        accountNames.setName("accountNames");
        accountNames.setSelectedIndex(-1);
        JLabel label = new JLabel();

        //TextArea waar het resultaat inkomt
        JTextArea area = new JTextArea("Nog geen profiel gekozen!");
        area.setForeground(Color.WHITE);
        area.setBackground(Color.darkGray);

        //Voegt actionlistener toe aan de combobox
        accountNames.addActionListener(new Listener(accountNames, area));
        accountNames.setMaximumSize(new Dimension(9099,50));

        //Zorgt ervoor dat de tekst van de combobox in het midden staat
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        accountNames.setRenderer(dlcr);

        //Als er nog geen profiel gekozen is dan wordt de onderstaande tekst weergegeven
        if (area.getText().equals("[]") || area.getText().equals("") || area.getText() == null){
            label.setText("Nog geen profiel gekozen!");
        }

        //voegt de components toe
        card3.add(label2);
        card3.add(accountNames);
        card3.add(area);

        return card3;
    }

    //Maakt card4 (Overzicht3)
    public JPanel makeCard4() {
        //Maakt connectie met database
        //Maakt een nieuw jpanel en stelt een layout in
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase(SqlDatabaseLink);
        JPanel card4 = new JPanel();
        card4.setLayout(new BoxLayout(card4, BoxLayout.Y_AXIS));

        //Maakt een nieuwe film en filmrepository aan
        Film film = null;
        FilmRepository filmRepository = new FilmRepository(connection);

        //Film is nu de film met de langste tijdsduur en een leeftijdsindicatie van onder de 16 jaar
        film = filmRepository.filmUnder16WithLongestDuration();

        //Pagina header
        JLabel header = new JLabel("", SwingConstants.CENTER);
        header.setText("<html>De film met de langste tijdsduur en onder 16 jaar is<br><br></html>");
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setAlignmentY(Component.CENTER_ALIGNMENT);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("TimesRoman", Font.PLAIN, 19));

        //Label waar het resultaat inkomt
        JLabel result = new JLabel();
        result.setText(film.getTitle() + " met een tijdsduur van " + film.getDuration() + " en leeftijdsindicatie van " + film.getRecommendedAge());
        result.setForeground(Color.WHITE);
        result.setAlignmentX(Component.CENTER_ALIGNMENT);
        result.setAlignmentY(Component.CENTER_ALIGNMENT);

        card4.add(header);
        card4.add(result);

        //Als de filmm der untergang is dan wordt er een plaatje van het boek tegevoegd
        if (film.getTitle().equals("Der Untergang")){
            ImageIcon image = new ImageIcon("src\\GUI\\Undergang.jpg");
            JLabel label = new JLabel(image);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setAlignmentY(Component.CENTER_ALIGNMENT);
            card4.add(label);
        }

        return card4;
    }

    //Maakt card5 (Overzicht5)
    public JPanel makeCard5() {
        //Maakt een nieuw jpanel en stelt een layout in
        JPanel card5 = new JPanel();
        card5.setLayout(new BoxLayout(card5, BoxLayout.Y_AXIS));

        //Maakt een nieuwe combobox aan met filmtitels en zet de geselecteerde index op -1 zodat de box leeg is.
        JComboBox filmTitles = makeFilmTitleCombobox();
        filmTitles.setSelectedIndex(-1);
        filmTitles.setName("filmTitles");
        filmTitles.setMaximumSize(new Dimension(9099,50));

        //Pagina header
        JLabel help = new JLabel("Klik in het dropdown menu op een film !");
        help.setAlignmentX(Component.CENTER_ALIGNMENT);
        help.setAlignmentY(Component.CENTER_ALIGNMENT);
        help.setForeground(Color.WHITE);

        //Hier komt het resultaat in
        JLabel field = new JLabel("Nog geen film gekozen!");
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setAlignmentY(Component.CENTER_ALIGNMENT);
        field.setForeground(Color.WHITE);

        //Voegt actionlistener toe
        filmTitles.addActionListener(new Listener(filmTitles, field));

        //Zorgt ervoor dat de tekst van de box in het midden staat
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        filmTitles.setRenderer(dlcr);

        //Voegt de componenten toe
        card5.add(help);
        card5.add(filmTitles);
        card5.add(field);

        return card5;
    }

    //Maakt card6 (Overzicht5)
    public JPanel makeCard6() {

        //Maakt een nieuw jpanel en stelt een layout in
        JPanel card6 = new JPanel();
        card6.setLayout(new BoxLayout(card6,BoxLayout.Y_AXIS));

        //Maakt een combox box van de serie namen
        JComboBox episodeTitleList = makeSerieTitlesCombobox();
        episodeTitleList.setSelectedIndex(-1);
        episodeTitleList.setName("episodeTitleList");

        //Pagina header
        JLabel info = new JLabel("Kies een serie om het gemiddelde percentage van de gekeken afleveringen te bekijken!");
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.setAlignmentY(Component.CENTER_ALIGNMENT);
        info.setForeground(Color.WHITE);

        //Hier komt het resultaat in
        JTextArea area = new JTextArea("Nog geen aflevering gekozen");
        area.setBackground(Color.DARK_GRAY);
        area.setForeground(Color.WHITE);
        area.setAlignmentX(Component.CENTER_ALIGNMENT);
        area.setAlignmentY(Component.CENTER_ALIGNMENT);

        //Voegt actionlistener toe
        episodeTitleList.addActionListener(new Listener(episodeTitleList, area));

        //Zorgt ervoor dat de tekst in de combobox in het midden staat
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        episodeTitleList.setRenderer(dlcr);

        //Voegt de componenten toe
        card6.add(info);
        card6.add(episodeTitleList);
        card6.add(area);


        return card6;
    }

    //Maakt card7 (Overzicht6)
    public JPanel makeCard7() {
        //Maakt connectie met database
        //Maakt een nieuw jpanel en stelt een layout in
        JPanel card7 = new JPanel();
        card7.setLayout(new BoxLayout(card7, BoxLayout.Y_AXIS));

        //Maakt een combobox van profielname en serienamen
        JComboBox profileNames = makeNameComboboxForProfiles();
        profileNames.setName("profileNames");
        JComboBox serieNames = makeSerieTitlesCombobox();
        serieNames.setName("1");
        profileNames.setSelectedIndex(-1);
        serieNames.setSelectedIndex(-1);

        //Pagina header
        JLabel info = new JLabel("Kies eerst een serie en daarna een profiel om te kijken welke afleveringen het profiel heeft gekeken van de gekozen serie!");
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.setAlignmentY(Component.CENTER_ALIGNMENT);
        info.setForeground(Color.WHITE);

        //De textarea waar het resultaat inkomt
        JTextArea resultArea = new JTextArea("Nog geen serie met profiel gekozen!");
        resultArea.setBackground(Color.DARK_GRAY);
        resultArea.setForeground(Color.WHITE);

        //Tekst boven de combobox van serie
        JLabel serie = new JLabel("Kies een serie!");
        serie.setAlignmentX(Component.CENTER_ALIGNMENT);
        serie.setAlignmentY(Component.CENTER_ALIGNMENT);
        serie.setForeground(Color.WHITE);

        //Tekst boven de combobox van profiel
        JLabel profile = new JLabel("Kies nu een profiel!");
        profile.setAlignmentX(Component.CENTER_ALIGNMENT);
        profile.setAlignmentY(Component.CENTER_ALIGNMENT);
        profile.setForeground(Color.WHITE);

        //Voegt actionlistener toe
        profileNames.addActionListener(new Listener(profileNames, resultArea, serieNames));

        //Zorgt ervoor dat de tekst van de combobox in het midden staat
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        profileNames.setRenderer(dlcr);
        serieNames.setRenderer(dlcr);

        //Voegt de componenten toe
        card7.add(info);
        card7.add(serie);
        card7.add(serieNames);
        card7.add(profile);
        card7.add(profileNames);
        card7.add(resultArea);

        return card7;

    }

    //Maakt een combobox van profielnamen
    public JComboBox makeNameComboboxForProfiles() {
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase(SqlDatabaseLink);

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

    //Maakt een combobox van film titlels
    public JComboBox makeFilmTitleCombobox() {
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase(SqlDatabaseLink);

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
    public JComboBox makeSerieTitlesCombobox(){
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase(SqlDatabaseLink);

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

