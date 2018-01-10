package Bekeken;


import Account.Account;
import Connection.SqlConnection;

import java.sql.ResultSet;
import java.util.ArrayList;

public class BekekenRepository {

    private SqlConnection sqlConnection;

    public BekekenRepository(SqlConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public Bekeken read(String id) {
        Bekeken bekeken = null;
        try
        {
            String sqlQuery = "SELECT * FROM BEKEKEN WHERE ABBONNENUMMER=" + id;
            ResultSet rs = sqlConnection.executeSql(sqlQuery);
            rs.next();
            bekeken = new Bekeken(rs.getString("Abbonnenummer"),
                    rs.getString("Profielnaam"),
                    rs.getString("Gezien"),
                    rs.getString("Percentage"));
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return bekeken;
    }

    public ArrayList<Bekeken> readAll() {
        ArrayList<Bekeken> lijst = new ArrayList<>();
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

    public int totalWatchedFilms(String titelId){
        int amount = 0;
        Bekeken bekeken = null;
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
