package Account;

import Connection.SqlConnection;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AccountRepository {
    private SqlConnection sqlConnection;

    public AccountRepository(SqlConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public ArrayList<Account> readAll() {
        ArrayList<Account> lijst = new ArrayList<>();
        try {
            ResultSet rs = sqlConnection.executeSql("SELECT * FROM ACCOUNT");
            while(rs.next()) {
                lijst.add(new Account(rs.getString("Abbonneenummer"), rs.getString("Naam"), rs.getString("Straat"), rs.getString("Postcode"), rs.getString("Huisnummer"), rs.getString("Plaats")));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return lijst;
    }

    public Account read(String subscriptionNumber) {
        Account account = null;
        try
        {
            String sqlQuery = "SELECT * FROM ACCOUNT WHERE Abbonneenummer=" + subscriptionNumber;
            ResultSet rs = sqlConnection.executeSql(sqlQuery);
            rs.next();
            account = new Account(rs.getString("Abbonneenummer"), rs.getString("Naam"), rs.getString("Straat"), rs.getString("Postcode"), rs.getString("Huisnummer"), rs.getString("Plaats"));
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return account;
    }

    public boolean create(Account account) {
        try
        {
            String sqlQuery = "INSERT INTO STUDENTS VALUES (" + account.getSubscriptionNumber() + ", '" + account.getName() + ", '" + account.getStreet() + ", '" + account.getZipcode() + ", '"+ account.getHouseNumber() + ", '"+ account.getCity() + ")";
            return sqlConnection.executeSqlNoResult(sqlQuery);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }


}
