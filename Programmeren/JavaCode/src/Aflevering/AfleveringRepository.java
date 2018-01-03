package Aflevering;

import Connection.SqlConnection;
import Film.Film;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AfleveringRepository {

    private SqlConnection sqlConnection;

    public AfleveringRepository(SqlConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }


    public ArrayList<Aflevering> readAll() {
        ArrayList<Aflevering> lijst = new ArrayList<>();
        try {
            ResultSet rs = sqlConnection.executeSql("SELECT * FROM AFLEVERING");
            while(rs.next()) {
                lijst.add(new Aflevering(rs.getString("ID"),rs.getString("Serie"), rs.getString("Seizoen"), rs.getString("Titel"), rs.getString("Tijdsduur")));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return lijst;
    }

    public Aflevering read(String id) {
        Aflevering aflevering= null;
        try
        {
            String sqlQuery = "SELECT * FROM AFLEVERING WHERE ID=" + id;
            ResultSet rs = sqlConnection.executeSql(sqlQuery);
            rs.next();
            aflevering = new Aflevering(rs.getString("ID"),rs.getString("Serie"), rs.getString("Seizoen"), rs.getString("Titel"), rs.getString("Tijdsduur"));
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return aflevering;
    }

}
