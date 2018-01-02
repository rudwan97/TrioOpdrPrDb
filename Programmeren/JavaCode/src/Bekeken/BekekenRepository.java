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
            String sqlQuery = "SELECT * FROM BEKEKEN WHERE Abbonneenummer=" + id;
            ResultSet rs = sqlConnection.executeSql(sqlQuery);
            rs.next();
            bekeken = new Bekeken(rs.getString("Abbonneenummer"), rs.getString("Profielnaam"), rs.getString("Gezien"), rs.getString("Percentage"));
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return bekeken;
    }

    public ArrayList<Account> readAll() {
        ArrayList<Account> lijst = new ArrayList<>();
        try {
            ResultSet rs = sqlConnection.executeSql("SELECT * FROM BEKEKEN");
            while(rs.next()) {
                lijst.add(new Account(rs.getString("Abbonneenummer"), rs.getString("Naam"), rs.getString("Straat"), rs.getString("Postcode"), rs.getString("Huisnummer"), rs.getString("Plaats")));
            }
        }

        catch(Exception e) {
            System.out.println(e);
        }

        return lijst;
    }
}
