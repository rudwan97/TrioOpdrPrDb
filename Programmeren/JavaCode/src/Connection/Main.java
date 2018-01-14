package Connection;

import Account.AccountRepository;
import Aflevering.Aflevering;
import Aflevering.*;
import Bekeken.BekekenRepository;
import Film.FilmRepository;
import GUI.UserInterface;
import Profiel.ProfielRepository;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {


        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        UserInterface ui = new UserInterface();
        SwingUtilities.invokeLater(ui);

        AccountRepository accountRepository = new AccountRepository( connection);
//        System.out.println(accountRepository.read("5602533"));
//        System.out.println(accountRepository.readAll());
//        System.out.println(accountRepository.accountsWithOneProfile());

        ProfielRepository profielRepository = new ProfielRepository(connection);
//        System.out.println(profielRepository.read("5602533"));
//        System.out.println(profielRepository.readAll());

        BekekenRepository bekekenRepository = new BekekenRepository(connection);
//        System.out.println(bekekenRepository.read("5602533"));
//        System.out.println(bekekenRepository.readAll());
//        System.out.println(bekekenRepository.totalWatchedFilms("8001"));

        FilmRepository filmRepository = new FilmRepository(connection);
//        System.out.println(filmRepository.filmsFromChosenAccount("5285824"));
//        System.out.println(filmRepository.readAll());
//        System.out.println(filmRepository.filmsFromChosenAccount("1215426"));
//        System.out.println(filmRepository.filmUnder16WithLongestDuration());
//        System.out.println(filmRepository.filmsSeenByChosenProfile());

        AfleveringRepository afleveringRepository = new AfleveringRepository(connection);



        System.out.println(filmRepository.filmsFromChosenAccount("121436"));
        System.out.println(filmRepository.filmsFromChosenAccount("Frank"));
        System.out.println(afleveringRepository.getEpisodesAndAvgSeenByChosenSerie("1"));
        System.out.println(afleveringRepository.getEpisodesAndAvgSeenByProfileAndSerie("Sherlock", "Frank"));


        connection.disconnectDatabase();

    }
}
