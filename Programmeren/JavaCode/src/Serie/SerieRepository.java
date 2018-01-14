package Serie;

import Aflevering.Aflevering;
import Connection.SqlConnection;
import Profiel.Profiel;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SerieRepository {
    //Alle repositories zijn bedoeld om gegevens op tehalen, te zoeken op een special gegeven en overzichten op te vragen voor de overzichten.
//Sommige repositories hebben ook de CRUD functies (Create, update, delete)
    private SqlConnection sqlConnection;

    public SerieRepository(SqlConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    //Haalt alle series uit de database en stopt deze in een list van series
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

    //Haalt een serie uit de database op een meegegeven serienaam
    public Serie read(String serieName) {
        Serie serie = null;
        try {
            String sqlQuery = "SELECT * FROM Serie WHERE Serienaam=" + serieName;
            ResultSet rs = sqlConnection.executeSql(sqlQuery);
            rs.next();
            serie = new Serie(rs.getString("Serienaam"),
                    rs.getString("Seizoen"),
                    rs.getString("Leeftijd"),
                    rs.getString("Taal"),
                    rs.getString("Genre"),
                    rs.getString("Lijkt een beetje op"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return serie;
    }


}
