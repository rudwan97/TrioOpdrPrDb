package GUI;

import Aflevering.*;
import Connection.SqlConnection;
import Film.Film;
import Film.FilmRepository;
import Profiel.Profiel;
import Profiel.ProfielRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Listener implements ActionListener {

    private JLabel field;
    private JComboBox box;
    private JComboBox secondBox;
    private JTextArea area;
    private String SqlDatabaseLink = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;";

    public Listener(JComboBox box, JLabel field) {
        this.box = box;
        this.field = field;
    }

    public Listener(JComboBox box, JTextArea area) {
        this.box = box;
        this.area = area;
    }

    public Listener(JComboBox box, JTextArea area, JComboBox secondBox) {
        this.box = box;
        this.area = area;
        this.secondBox = secondBox;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        //Als er op de combobox van profielNamen van overzicht 2 iets gekozen voert word deze if statement uitgevoerd
        if (this.box.getName().equals("accountNames")) {

            //Connect de database en haalt het geselecteerde item uit de combobox
            String s = (String) box.getSelectedItem();
            SqlConnection connection = new SqlConnection();
            connection.connectDatabase(SqlDatabaseLink);

            //Maakt een nieuwe filmrepository aan en maakt een list me alle films die door het gekozen account bekeken zijn.
            FilmRepository repository = new FilmRepository(connection);
            List<Film> films = repository.filmsFromChosenAccount(s);

            //Voegt het resultaat aan de textarea toe
            this.area.setText("");
            for (int i = 0; i < films.size(); i++) {
                this.area.append(" Film " + films.get(i).getTitle() + " van het genre " + films.get(i).getFilmType() + " is door " + s + " bekeken" + "\n");
                this.area.setBackground(Color.DARK_GRAY);
                this.area.setForeground(Color.WHITE);
            }

            //Als er niks gevonden is wordt dit weergegeven
            if (this.area.getText() == null || this.area.getText().equals("")) {
                this.area.setText("Er is door " + s + " nog geen film volledig afgekeken!");
            }

        //Als er op de combobox van films in overzciht 4 iets wordt gekozen wordt deze if statement uitgevoerd
        } else if (this.box.getName().equals("filmTitles")) {
            //Connect de database en haalt het geselecteerde item uit de combobox
            String z = (String) box.getSelectedItem();
            SqlConnection connection = new SqlConnection();
            connection.connectDatabase(SqlDatabaseLink);

            //Maakt een nieuwe profielrepsository aan en maakt een list van de profielen die de gekozen film VOLLEDIG hebben bekeken
            ProfielRepository profielRepository = new ProfielRepository(connection);
            List<Profiel> profiles = profielRepository.profilesThatWatchedWholeChosenFilm(z);

            // Als er een profiel bestaat die de gekozen film heeft bekeken wordt dit uitgevoerd
                this.field.setText("");
                this.field.setText("<html>");
                if (profiles.size() > 0) {
                    for (int i = 0; i < profiles.size(); i++) {
                        this.field.setText(this.field.getText() + profiles.get(i).getProfileName());
                        if (profiles.size() > 1 && i == 0) {
                            this.field.setText(this.field.getText() + " en ");
                        }
                    }
                }

                //Zorgt ervoor dat de zin klopt met het aantal profielen
                if (profiles.size() == 2) {
                    this.field.setText(this.field.getText() + " hebben deze film volledig gekeken!");
                } else if (profiles.size() == 1) {
                    this.field.setText(this.field.getText() + " heeft deze film volledig gekeken!");
                }

                //Als er geen profiel gevonden wordt, wordt dit uitgevoerd
                if (this.field.getText() == null || this.field.getText().equals("") || this.field.getText().equals("<html>")) {
                    this.field.setText(this.field.getText() + "Er heeft nog geen gebruiker deze film volledig afgekeken!");
                }

                //Voegt een witte regel toe
                this.field.setText(this.field.getText() + "<br>");

                //Nieuwe list met profielen die de gekozen film hebben bekeken
                List<Profiel> profilesThatWatchedAFilm = profielRepository.profilesThatWatchedAFilm(z);
                double total = 0;

                    //Berekent het gemiddeld bekeken % van de serie in zijn geheel
                    if (profiles.size() != 0) {
                        total = (double) profiles.size() / (double) profilesThatWatchedAFilm.size() * 100;
                    }

                    //Zorgt ervoor dat het resultaat een goed formaat heeft (Meervoud en werkwoord vervoegen)
                    if (profilesThatWatchedAFilm.size() == 0 || profilesThatWatchedAFilm.size() > 1 && profiles.size() == 0 || profiles.size() > 1) {
                        this.field.setText(this.field.getText() + "<br>In totaal hebben " + profilesThatWatchedAFilm.size() + " profielen " + z + " gekeken<br>"
                                + profiles.size() + " profielen hebben " + z + " volledig bekeken<br>"
                                + total + "% van alle kijkers heeft " + z + " volledig bekeken!");
                    }
                    //Zorgt ervoor dat het resultaat een goed formaat heeft (Meervoud en werkwoord vervoegen)
                    else if (profilesThatWatchedAFilm.size() == 1 && profiles.size() == 1) {
                         this.field.setText(this.field.getText() + "<br>In totaal heeft " + profilesThatWatchedAFilm.size() + " profiel " + z + " gekeken<br>"
                        + profiles.size() + " profiel heeft " + z + " volledig bekeken<br>"
                        + total + "% van alle kijkers heeft " + z + " volledig bekeken!");
                     }
                    //Zorgt ervoor dat het resultaat een goed formaat heeft (Meervoud en werkwoord vervoegen)
                     else if (profilesThatWatchedAFilm.size() == 1 && profiles.size() == 0 || profiles.size() > 1) {
                        this.field.setText(this.field.getText() + "<br>In totaal heeft " + profilesThatWatchedAFilm.size() + " profiel " + z + " gekeken<br>"
                                + profiles.size() + " profielen hebben " + z + " volledig bekeken<br>"
                                + total + "% van alle kijkers heeft " + z + " volledig bekeken!");
                    }
                    //Zorgt ervoor dat het resultaat een goed formaat heeft (Meervoud en werkwoord vervoegen)
                    else if (profilesThatWatchedAFilm.size() == 0 || profilesThatWatchedAFilm.size() > 1 && profiles.size() == 1) {
                        this.field.setText(this.field.getText() + "<br>In totaal hebben " + profilesThatWatchedAFilm.size() + " profielen " + z + " gekeken<br>"
                                + profiles.size() + " profiel heeft " + z + " volledig bekeken<br>"
                                + total + "% van alle kijkers heeft " + z + " volledig bekeken!");
                    }

                    //Zorgt ervoor dat de tekst in het midden komt te staan
                    this.field.setText(this.field.getText() + "</html>");
                    this.field.setAlignmentX(Component.CENTER_ALIGNMENT);
                    this.field.setAlignmentY(Component.CENTER_ALIGNMENT);
                    this.field.setVerticalTextPosition(SwingConstants.CENTER);

                }
                //Als de combobox met de serienamen wordt gebruikt, dan wordt deze if statement uitgevoerd (Overzicht5)
                else if (this.box.getName().equals("episodeTitleList")) {

                    //Connect de database en haalt het geselecteerde item uit de combobox
                    String s = (String) box.getSelectedItem();
                    SqlConnection connection = new SqlConnection();
                    connection.connectDatabase(SqlDatabaseLink);

                    //Maakt een nieuwe aflevering repository aan en maakt een nieuwe list aan van de afleveringen van de geselecteerde serie
                    AfleveringRepository afleveringRepository = new AfleveringRepository(connection);
                    List<Aflevering> episodes = new ArrayList<Aflevering>();
                    episodes = afleveringRepository.getEpisodesAndAvgSeenByChosenSerie(s);

                    //Voegt het resultaat met het gemiddelde % bekeken aan het resultaat TextArea toe
                    this.area.setText("");
                    for (int i = 0; i < episodes.size(); i++) {
                        this.area.append("Aflevering " + episodes.get(i).getTitle() + " is gemiddeld voor " + episodes.get(i).getAvgSeen() + " % bekeken!" + "\n");
                        this.area.setBackground(Color.DARK_GRAY);
                        this.area.setForeground(Color.WHITE);
                    }

                    //Berekent het gemiddelde totale % van de gehele serie
                    int total = 0;
                    for (Aflevering f : episodes) {
                        total += f.getAvgSeen();
                    }
                    int amountOfEpisodes = episodes.size();
                    total = total / amountOfEpisodes;

                    //Formateert het resultaat in twee correcte zinnen
                    this.area.append("\n" + s + " heeft in totaal " + amountOfEpisodes + " afleveringen! " + "\n");
                    this.area.append(s + " is in zijn geheel is voor " + total + "% bekeken!");

                  //Als de een van de comboboxen van overzicht 6 worden gebruikt wordt deze if statement uitgevoerd
                } else if (this.secondBox.getName().equals("1") || this.box.getName().equals("profileNames")) {

                    //Connect de database en haalt het geselecteerde item uit de comboboxen
                    String s = (String) this.box.getSelectedItem();
                    String x = (String) this.secondBox.getSelectedItem();
                    SqlConnection connection = new SqlConnection();
                    connection.connectDatabase(SqlDatabaseLink);

                    //Maakt een nieuwe aflevering repository aan en maakt een nieuwe list aan met afleveringen van een geselecteerde serie en een gekozen profiel
                    AfleveringRepository afleveringRepository = new AfleveringRepository(connection);
                    List<Aflevering> episodes = afleveringRepository.getEpisodesAndAvgSeenByProfileAndSerie(x, s);

                    //Voegt het resultaat toe aan de text area
                    this.area.setText("");
                    for (int i = 0; i < episodes.size(); i++) {
                        this.area.append("De aflevering " + episodes.get(i).getTitle() + " van de serie " + x + " is voor " + episodes.get(i).getAvgSeen() + "% bekeken door " + s + "\n");
                        this.area.setBackground(Color.DARK_GRAY);
                        this.area.setForeground(Color.WHITE);
                    }

                    //Als er geen afleveringen gevonden zijn wordt dit weergegeven
                    if (this.area.getText().equals("")) {
                        this.area.setText("Er is door " + s + " geen aflevering van de serie " + x + " bekeken");
                    }

                    //Zorgt ervoor dat de comboboxen weer op leeg worden gezet
                    if (s != null && x != null) {
                        this.box.setSelectedIndex(-1);
                        this.secondBox.setSelectedIndex(-1);
                    }
                }
    }
}
