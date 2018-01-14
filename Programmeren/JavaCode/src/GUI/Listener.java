package GUI;

import Account.AccountRepository;
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


        if (this.box.getName().equals("accountNames")) {
            String s = (String) box.getSelectedItem();
            SqlConnection connection = new SqlConnection();
            connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

            FilmRepository repository = new FilmRepository(connection);

            List<Film> films = repository.filmsFromChosenAccount(s);
            this.area.setText("");
            for (int i = 0; i < films.size(); i++) {
                this.area.append(" Film " + films.get(i).getTitle() + " van het genre " + films.get(i).getFilmType() + " is door " + s + " bekeken" + "\n");
                this.area.setBackground(Color.DARK_GRAY);
                this.area.setForeground(Color.WHITE);
            }
            if (this.area.getText() == null || this.area.getText().equals("")) {
                this.area.setText("Er is door " + s + " nog geen film volledig afgekeken!");
            }

        } else if (this.box.getName().equals("filmTitles")) {
            String z = (String) box.getSelectedItem();

            SqlConnection connection = new SqlConnection();
            connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");


            ProfielRepository profielRepository = new ProfielRepository(connection);
            List<Profiel> movies = profielRepository.profilesThatWatchedWholeChosenFilm(z);

            this.field.setText("");
            for (int i = 0; i < movies.size(); i++) {
                this.field.setText(this.field.getText() + movies.get(i).getProfileName());
                if (movies.size() > 1 && i == 0) {
                    this.field.setText(this.field.getText() + " en ");
                }
            }

            if (movies.size() == 2) {
                this.field.setText(this.field.getText() + " hebben deze film gekeken!");
            } else {
                if (!this.field.getText().equals("")) {
                    this.field.setText(this.field.getText() + " heeft deze film gekeken!");
                }
            }

            if (this.field.getText() == null || this.field.getText().equals("")) {
                this.field.setText("Er heeft nog geen gebruiker deze film volledig afgekeken!");
            }


        }
        else if (this.box.getName().equals("episodeTitleList")) {

            String s = (String) box.getSelectedItem();
            SqlConnection connection = new SqlConnection();
            connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

            AfleveringRepository afleveringRepository = new AfleveringRepository(connection);
            List<Aflevering> episodes = new ArrayList<Aflevering>();
            episodes = afleveringRepository.getEpisodesAndAvgSeenByChosenSerie(s);

            this.area.setText("");
            for (int i = 0; i < episodes.size(); i++) {
                this.area.append("Aflevering " + episodes.get(i).getTitle() + " is gemiddeld voor " + episodes.get(i).getAvgSeen() + " % bekeken!" + "\n");
                this.area.setBackground(Color.DARK_GRAY);
                this.area.setForeground(Color.WHITE);
            }
        }
            else if (this.secondBox.getName().equals("1") || this.box.getName().equals("profileNames")) {

            String s = (String) this.box.getSelectedItem();
            String x = (String) this.secondBox.getSelectedItem();

            SqlConnection connection = new SqlConnection();
            connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

            AfleveringRepository afleveringRepository = new AfleveringRepository(connection);
            List<Aflevering> episodes = afleveringRepository.getEpisodesAndAvgSeenByProfileAndSerie(x, s);

            this.area.setText("");
            for (int i = 0; i < episodes.size(); i++) {
                this.area.append("De aflevering " + episodes.get(i).getTitle() + " van de serie " + x + " is voor " + episodes.get(i).getAvgSeen() + "% bekeken door " + s + "\n");
                this.area.setBackground(Color.DARK_GRAY);
                this.area.setForeground(Color.WHITE);
            }

            if (this.area.getText().equals("")) {
                this.area.setText("Er is door " + s + " geen aflevering van de serie " + x + " bekeken");
            }

            if (s != null && x != null) {
                this.box.setSelectedIndex(-1);
                this.secondBox.setSelectedIndex(-1);
            }
        } else if (this.box.getName().equals("5")) {
//
//            String x = (String) this.box.getSelectedItem();
//
//            SqlConnection connection = new SqlConnection();
//            connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");
////            AfleveringRepository afleveringRepository = new AfleveringRepository(connection);
////            List<Aflevering> episodes = afleveringRepository.getEpisodesAndAvgSeenByChosenSerie(x);
//
//     //       int total = 0;
//     //       int amountOfEpisodes = 0;
//     //       for (Aflevering f : episodes){
//     //           total += f.getAvgSeen();
//     //           amountOfEpisodes++;
//     //       }
//
//
//       //     this.field.setText(String.valueOf(total) + " <-- gemiddelde van totale film " + x + "<-- de serie" + String.valueOf(amountOfEpisodes) + "<-- aantal afleverignen");
//
        }
    }
}
