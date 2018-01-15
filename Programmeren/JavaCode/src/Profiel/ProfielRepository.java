package Profiel;

import Connection.SqlConnection;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProfielRepository {
    //Alle repositories zijn bedoeld om gegevens op tehalen, te zoeken op een special gegeven en overzichten op te vragen voor de overzichten.
//Sommige repositories hebben ook de CRUD functies (Create, update, delete)
    private SqlConnection sqlConnection;

    public ProfielRepository(SqlConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    //Haalt alle profielen ui de database en stop deze in een arraylist van profiel
    public ArrayList<Profiel> readAll() {
        ArrayList<Profiel> list = new ArrayList<>();
        try {
            ResultSet rs = sqlConnection.executeSql("SELECT * FROM PROFIEL");
            while(rs.next()) {
                list.add(new Profiel(rs.getString("Abbonneenummer"), rs.getString("Profielnaam"), rs.getString("Geboortedatum")));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return list;
    }

    //Haalt een profiel uit de database op een meegegeven abbonneenummer
    public Profiel read(String subscriptionNumber) {
        Profiel profiel = null;
        try {
            String sqlQuery = "SELECT * FROM PROFIEL " +
                    "WHERE Abbonneenummer=" + subscriptionNumber;
            ResultSet rs = sqlConnection.executeSql(sqlQuery);
            rs.next();
            profiel = new Profiel(rs.getString("Abbonneenummer"), rs.getString("Profielnaam"), rs.getString("Geboortedatum"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return profiel;
    }

    //Maakt een profiel aan in de database door een nieuw profiel mee te geven
    public boolean create(Profiel profiel) {
        try
        {
            String sqlQuery = "INSERT INTO Profiel VALUES (" + profiel.getSubscriptionNumber() + ", '" + profiel.getProfileName() + ", '" + profiel.getBirthDate()  + ")";
            return sqlConnection.executeSqlNoResult(sqlQuery);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //Haalt een lijst met profielen uit de databse die een film volledig afhebben gekeken (100%)
    public List<Profiel> profilesThatWatchedWholeChosenFilm(String filmTitel){
        List<Profiel> profiles = new ArrayList<Profiel>();
        try {
            ResultSet rs = sqlConnection.executeSql("Select Profiel.Profielnaam, Profiel.*, Film.Titel, Bekeken.Percentage\n" +
                    "From profiel\n" +
                    "JOIN Bekeken on Profiel.Profielnaam = Bekeken.Profielnaam\n" +
                    "JOIN FILM on Bekeken.Gezien = Film.ID\n" +
                    "WHERE Bekeken.Percentage = '100' AND Film.Titel = " + "'" + filmTitel + "'");
            while(rs.next()) {
                profiles.add(new Profiel(rs.getString("Abbonneenummer"),
                        rs.getString("Profielnaam"),
                        rs.getString("Geboortedatum")));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return profiles;
    }

    //Alle profielen die een film hebben bekeken
    public List<Profiel> profilesThatWatchedAFilm(String filmTitle){
        List<Profiel> profiles = new ArrayList<Profiel>();
        try {
            ResultSet rs = sqlConnection.executeSql("Select Profiel.Profielnaam, Profiel.*, Film.Titel, Bekeken.Percentage\n" +
                    "From profiel\n" +
                    "JOIN Bekeken on Profiel.Profielnaam = Bekeken.Profielnaam\n" +
                    "JOIN FILM on Bekeken.Gezien = Film.ID\n" +
                    "WHERE Film.Titel = " + "'" + filmTitle + "'");
            while(rs.next()) {
                profiles.add(new Profiel(rs.getString("Abbonneenummer"),
                        rs.getString("Profielnaam"),
                        rs.getString("Geboortedatum")));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return profiles;
    }

    //Met deze functie kan je een profiel verwijderen.
    public boolean delete(String subscriptionNumber) {
        try
        {
            String sqlQuery = "DELETE Profiel WHERE Abbonneenummer=" + subscriptionNumber;
            return sqlConnection.executeSqlNoResult(sqlQuery);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //Met deze functie kan je een profiel update door het oude profiel en het geupdate profiel mee te geven.
    public boolean update(Profiel oldProfiel, Profiel updatedProfiel){
        try
        {
            String sqlQuery = "Update Profiel SET Abbonneenummer = " + updatedProfiel.getSubscriptionNumber() +
                    "Profielnaam =" + updatedProfiel.getProfileName()  +
                    "Geboortedatum =" + updatedProfiel.getBirthDate()  +
                    "WHERE Abbonneenummer=" + oldProfiel.getSubscriptionNumber();
            return sqlConnection.executeSqlNoResult(sqlQuery);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }
}






