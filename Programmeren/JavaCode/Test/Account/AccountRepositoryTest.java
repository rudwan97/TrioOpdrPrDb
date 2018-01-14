package Account;

import Connection.SqlConnection;
import Film.Film;
import Film.FilmRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AccountRepositoryTest {
    @Test
    public void accountWithOneProfileReturnsCorrectAmountOfAccountsWithValidInput() throws Exception {

        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        AccountRepository accountRepository = new AccountRepository(connection);
        List<Account> list = new ArrayList<Account>();

        list = accountRepository.accountsWithOneProfile();


        boolean accountsWithOneProfileReturnsCorrectAccounts  = false;

        if (list.size() == 0){
            accountsWithOneProfileReturnsCorrectAccounts = true;
        }

        Assert.assertTrue(accountsWithOneProfileReturnsCorrectAccounts);
    }

    @Test
    public void accountWithOneProfileReturnsWrongAmountOfAccountsWithIncorrectAmount() throws Exception {

        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        AccountRepository accountRepository = new AccountRepository(connection);
        List<Account> list = new ArrayList<Account>();

        list = accountRepository.accountsWithOneProfile();


        boolean accountsWithOneProfileReturnsCorrectAccounts  = false;

        if (list.size() == 1){
            accountsWithOneProfileReturnsCorrectAccounts = true;
        }

        Assert.assertFalse(accountsWithOneProfileReturnsCorrectAccounts);
    }



}