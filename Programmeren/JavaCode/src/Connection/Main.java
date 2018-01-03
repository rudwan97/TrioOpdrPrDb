package Connection;

import Account.AccountRepository;
import Bekeken.BekekenRepository;
import Profiel.ProfielRepository;

public class Main {

    public static void main(String[] args) {


        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        AccountRepository accountRepository = new AccountRepository( connection);
        System.out.println(accountRepository.read("5602533"));
        System.out.println(accountRepository.readAll());
        System.out.println(accountRepository.accountsWithOneProfile());

        ProfielRepository profielRepository = new ProfielRepository(connection);
        System.out.println(profielRepository.read("5602533"));
        System.out.println(profielRepository.readAll());

        BekekenRepository bekekenRepository = new BekekenRepository(connection);
        System.out.println(bekekenRepository.read("5602533"));
        System.out.println(bekekenRepository.readAll());
        System.out.println(bekekenRepository.totalWatchedFilms("8001"));



        connection.disconnectDatabase();

    }
}
