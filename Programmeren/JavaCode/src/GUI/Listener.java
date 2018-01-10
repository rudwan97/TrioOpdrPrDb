package GUI;

import Account.AccountRepository;
import Connection.SqlConnection;
import Film.FilmRepository;
import Profiel.ProfielRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {

    private JLabel field;
    private JComboBox box;

    public Listener(JComboBox box, JLabel field) {
        this.box = box;
        this.field = field;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        //Klikken voor films bij behorende profielen

        if (this.box.getName() == "accountNames") {
            String s = (String) box.getSelectedItem();
            SqlConnection connection = new SqlConnection();
            connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

            FilmRepository repository = new FilmRepository(connection);
            this.field.setText(repository.filmsFromChosenAccount(s).toString());

            if (this.field.getText() == null || this.field.getText().equals("[]")) {
                this.field.setText("Deze gebruiker heeft nog geen films bekeken of helemaal afgekeken!");
            }

        } else if (this.box.getName() == "filmTitles") {
            String z = (String) box.getSelectedItem();

            SqlConnection connection = new SqlConnection();
            connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

            ProfielRepository profielRepository = new ProfielRepository(connection);
            this.field.setText(profielRepository.profilesThatWatchedWholeChosenFilm(z).toString());
        }

    }
}

