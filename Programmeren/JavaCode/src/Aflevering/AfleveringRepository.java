package Aflevering;

import Connection.SqlConnection;
import Film.Film;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//Alle repositories zijn bedoeld om gegevens op tehalen, te zoeken op een special gegeven en overzichten op te vragen voor de overzichten.
//Sommige repositories hebben ook de CRUD functies (Create, update, delete)
public class AfleveringRepository {

    private SqlConnection sqlConnection;

    //Maakt een AfleveringRepository en verbind deze met de database dmv de sql connection.
    public AfleveringRepository(SqlConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    //Haalt alle afleveringen uit de database op, en stopt deze in een List van aflevering
    public List<Aflevering> readAll() {
        List<Aflevering> lijst = new ArrayList<>();
        try {
            ResultSet rs = sqlConnection.executeSql("SELECT * FROM AFLEVERING");
            while(rs.next()) {
                lijst.add(new Aflevering(rs.getString("ID"),
                        rs.getString("Serie"),
                        rs.getString("Seizoen"),
                        rs.getString("Titel"),
                        rs.getString("Tijdsduur")));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return lijst;
    }

    //Met deze functie kan je een aflevering zoeken in de database op een id.
    public Aflevering read(String id) {
        Aflevering aflevering= null;
        try
        {
            String sqlQuery = "SELECT * FROM AFLEVERING WHERE ID=" + id;
            ResultSet rs = sqlConnection.executeSql(sqlQuery);
            rs.next();
            aflevering = new Aflevering(rs.getString("ID"),
                    rs.getString("Serie"),
                    rs.getString("Seizoen"),
                    rs.getString("Titel"),
                    rs.getString("Tijdsduur"));
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return aflevering;
    }

    // Deze functie geeft een List van aflevering die is gebaseerd op een gekozen serie
    public List<Aflevering> getEpisodesAndAvgSeenByChosenSerie(String serieName){
        Aflevering aflevering= null;
        List<Aflevering> episodes = new ArrayList<Aflevering>();

        try {
            String sqlQuery = "SELECT AVG(Bekeken.Percentage) as Gemiddelde, Aflevering.Titel " +
                    "FROM Bekeken " +
                    "Join Aflevering on Aflevering.ID = Bekeken.Gezien " +
                    "Where Aflevering.Serie = '" + serieName + "' " +
                    "Group by Aflevering.Titel";
            ResultSet rs = sqlConnection.executeSql(sqlQuery);

            while (rs.next()) {
                aflevering = new Aflevering(rs.getString("Titel"), rs.getInt("Gemiddelde"));
                episodes.add(aflevering);
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }

        return episodes;
    }

    //Deze functie geeft een List van aflevering met het gemiddelde bekeken percentage gebaseerd op een gekozen serie en profiel.
    public List<Aflevering> getEpisodesAndAvgSeenByProfileAndSerie(String serieName, String profilename){

        List<Aflevering> episodes = new ArrayList<Aflevering>();
        Aflevering episode = null;

        try {
            String sqlQuery = "SELECT AVG(Bekeken.Percentage) as Gemiddelde, Bekeken.Gezien, Aflevering.Titel\n" +
                    "FROM Bekeken\n" +
                    "JOIN Aflevering on Aflevering.ID = Bekeken.Gezien\n" +
                    "Where Bekeken.Profielnaam = '" + profilename + "' and Aflevering.Serie = '" + serieName + "'\n" +
                    "group by Bekeken.Gezien, Aflevering.Titel";
            ResultSet rs = sqlConnection.executeSql(sqlQuery);

            while (rs.next()) {
                episode = new Aflevering(rs.getString("Titel"), rs.getInt("Gemiddelde"));
                episodes.add(episode);
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return episodes;
    }


}
