package Account;

import Connection.SqlConnection;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//Alle repositories zijn bedoeld om gegevens op tehalen, te zoeken op een special gegeven en overzichten op te vragen voor de overzichten.
//Sommige repositories hebben ook de CRUD functies (Create, update, delete)
public class AccountRepository {
    private SqlConnection sqlConnection;

    //Maakt een AccountRepository en verbind deze met de database dmv de sql connection.
    public AccountRepository(SqlConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    //Haalt alle accounts uit de database op, en stopt deze in een List van accounts
    public List<Account> readAll() {
        List<Account> lijst = new ArrayList<>();
        try {
            ResultSet rs = sqlConnection.executeSql("SELECT * FROM ACCOUNT");
            while(rs.next()) {
                lijst.add(new Account(rs.getString("Abbonneenummer"),
                        rs.getString("Naam"),
                        rs.getString("Straat"),
                        rs.getString("Postcode"),
                        rs.getString("Huisnummer"),
                        rs.getString("Plaats")));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return lijst;
    }

    //Met deze functie kan je een account zoeken in de database op abbonneenummer.
    public Account read(String subscriptionNumber) {
        Account account = null;
        try
        {
            String sqlQuery = "SELECT * FROM ACCOUNT WHERE Abbonneenummer=" + subscriptionNumber;
            ResultSet rs = sqlConnection.executeSql(sqlQuery);
            rs.next();
            account = new Account(rs.getString("Abbonneenummer"),
                    rs.getString("Naam"),
                    rs.getString("Straat"),
                    rs.getString("Postcode"),
                    rs.getString("Huisnummer"),
                    rs.getString("Plaats"));
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return account;
    }


    //Met deze functie kan je een account maken in de database.
    public boolean create(Account account) {
        try
        {
            String sqlQuery = "INSERT INTO STUDENTS VALUES (" + account.getSubscriptionNumber() + ", '"
                    + account.getName() + ", '"
                    + account.getStreet() + ", '"
                    + account.getZipcode() + ", '"
                    + account.getHouseNumber() + ", '"
                    + account.getCity() + ")";
            return sqlConnection.executeSqlNoResult(sqlQuery);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //Deze functie geeft een List terug van account met maar 1 profiel.
    public List<Account> accountsWithOneProfile(){
        List<Account> lijst = new ArrayList<>();
        Account account = null;
        String accountNaam = "";
        try {
            ResultSet rs = sqlConnection.executeSql("SELECT COUNT (Profiel.Abbonneenummer) as Aantal, Profiel.Abbonneenummer " +
                    "FROM Profiel " +
                    "JOIN Account on Account.Abbonneenummer = Profiel.Abbonneenummer " +
                    "GROUP BY Profiel.Abbonneenummer " +
                    "HAVING COUNT(Profiel.Abbonneenummer) = 1");
            while(rs.next()) {
                accountNaam = rs.getString("Abbonneenummer");
                lijst.add(read(accountNaam));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return lijst;
    }


}
