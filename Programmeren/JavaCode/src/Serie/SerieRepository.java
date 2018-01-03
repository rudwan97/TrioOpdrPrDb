package Serie;

import Aflevering.Aflevering;
import Connection.SqlConnection;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SerieRepository {

    private SqlConnection sqlConnection;

    public SerieRepository(SqlConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public ArrayList<Serie> readAll() {
        ArrayList<Serie> lijst = new ArrayList<>();
        try {
            ResultSet rs = sqlConnection.executeSql("SELECT * FROM SERIE");
            while(rs.next()) {
                lijst.add(new Serie(rs.getString("SerieNaam"),rs.getString("Seizoen"), rs.getString("Leeftijd"), rs.getString("Taal"), rs.getString("Genre"), rs.getString("LijktEenBeetjeOp")));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return lijst;
    }
}
