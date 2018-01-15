package Account;

import Connection.SqlConnection;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryTest {
    @Test
    public void TestaccountWithOneProfileReturnsCorrectAmountOfAccountsWithValidInput() throws Exception {

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
    public void TestaccountWithOneProfileReturnsWrongAmountOfAccountsWithIncorrectAmount() throws Exception {

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