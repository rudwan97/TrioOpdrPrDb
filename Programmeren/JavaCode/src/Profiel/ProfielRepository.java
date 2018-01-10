package Profiel;

import Account.Account;
import Connection.SqlConnection;
import Film.Film;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProfielRepository {

    private SqlConnection sqlConnection;

    public ProfielRepository(SqlConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public ArrayList<Profiel> readAll() {
        ArrayList<Profiel> lijst = new ArrayList<>();
        try {
            ResultSet rs = sqlConnection.executeSql("SELECT * FROM PROFIEL");
            while(rs.next()) {
                lijst.add(new Profiel(rs.getString("Abbonneenummer"), rs.getString("Profielnaam"), rs.getString("Geboortedatum")));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return lijst;
    }

    public Profiel read(String subscriptionNumber) {
        Profiel profiel = null;
        try {
            String sqlQuery = "SELECT * FROM PROFIEL WHERE Abbonneenummer=" + subscriptionNumber;
            ResultSet rs = sqlConnection.executeSql(sqlQuery);
            rs.next();
            profiel = new Profiel(rs.getString("Abbonneenummer"), rs.getString("Profielnaam"), rs.getString("Geboortedatum"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return profiel;
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


    }






