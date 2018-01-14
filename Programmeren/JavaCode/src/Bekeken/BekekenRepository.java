package Bekeken;


import Account.Account;
import Connection.SqlConnection;
import Profiel.Profiel;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//Alle repositories zijn bedoeld om gegevens op tehalen, te zoeken op een special gegeven en overzichten op te vragen voor de overzichten.
//Sommige repositories hebben ook de CRUD functies (Create, update, delete)
public class BekekenRepository {

    private SqlConnection sqlConnection;

    //Maakt een BekekenRepository en verbind deze met de database dmv de sql connection.
    public BekekenRepository(SqlConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    //Met deze functie kan je een bekeken programma zoeken in de database op abbonneenummer.
    public Bekeken read(String id) {
        Bekeken seen = null;
        try
        {
            String sqlQuery = "SELECT * FROM BEKEKEN WHERE ABBONNENUMMER=" + id;
            ResultSet rs = sqlConnection.executeSql(sqlQuery);
            rs.next();
            seen = new Bekeken(rs.getString("Abbonnenummer"),
                    rs.getString("Profielnaam"),
                    rs.getString("Gezien"),
                    rs.getString("Percentage"));
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return seen;
    }

    //Haalt alle bekeken programma's uit de database op, en stopt deze in een List van Bekeken
    public List<Bekeken> readAll() {
        List<Bekeken> lijst = new ArrayList<>();
        try {
            ResultSet rs = sqlConnection.executeSql("SELECT * FROM BEKEKEN");
            while(rs.next()) {
                lijst.add(new Bekeken(rs.getString("Abbonnenummer"),
                        rs.getString("Profielnaam"),
                        rs.getString("Gezien"),
                        rs.getString("Percentage")));
            }
        }

        catch(Exception e) {
            System.out.println(e);
        }

        return lijst;
    }

    //Deze functie kan een bekeken programma toevoegen aan de database
    public boolean create(Bekeken bekeken) {
        try
        {
            String sqlQuery = "INSERT INTO BEKEKEN VALUES ("
                    + bekeken.getSubscriptionNumber()
                    + ", '" + bekeken.getProfileName()
                    + ", '" + bekeken.getPercentageSeen()
                    + ", '" + bekeken.getWatched() + ")";
            return sqlConnection.executeSqlNoResult(sqlQuery);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //Met deze functie kan je een bekeken programma verwijderen.
    public boolean delete(String subscriptionNumber) {
        try
        {
            String sqlQuery = "DELETE Bekeken WHERE Abbonneenummer=" + subscriptionNumber;
            return sqlConnection.executeSqlNoResult(sqlQuery);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //Met deze functie kan je een bekeken programma update door het oude programma en het geupdate programma mee te geven.
    public boolean update(Bekeken oldSeen, Bekeken newSeen){
        try
        {
            String sqlQuery = "Update Bekeken SET Abbonneenummer = " + newSeen.getSubscriptionNumber() +
                    "Profielnaam =" + newSeen.getProfileName()  +
                    "Geboortedatum =" + newSeen.getWatched()  +
                    "Percentage = " + newSeen.getPercentageSeen() +
                    "WHERE Abbonneenummer=" + oldSeen.getSubscriptionNumber();
            return sqlConnection.executeSqlNoResult(sqlQuery);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return false;

    }

    //Deze fucntie haalt het totaal aan bekeken films gebaseerd op een gekozen filmID
    public int totalWatchedFilms(String titelId){
        int amount = 0;
        Bekeken seen = null;
        try
        {
            String sqlQuery = "SELECT COUNT(Bekeken.Gezien) as Aantal " +
                    "FROM Bekeken " +
                    "JOIN FILm on Film.ID = Bekeken.Gezien " +
                    "WHERE Bekeken.Gezien=" + titelId;
            ResultSet rs = sqlConnection.executeSql(sqlQuery);
            rs.next();
            amount = rs.getInt("Aantal");
        }
        catch(Exception e) {
            System.out.println(e);
        }

    return amount;
    }

}
