package Film;

import Connection.SqlConnection;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//Alle repositories zijn bedoeld om gegevens op tehalen, te zoeken op een special gegeven en overzichten op te vragen voor de overzichten.
//Sommige repositories hebben ook de CRUD functies (Create, update, delete)
public class FilmRepository {

    private SqlConnection sqlConnection;

    //Maakt een FilmRepository en verbind deze met de database dmv de sql connection.
    public FilmRepository(SqlConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    //Haalt alle Films uit de database op, en stopt deze in een List van Film
    public List<Film> readAll() {
        List<Film> lijst = new ArrayList<>();
        try {
            ResultSet rs = sqlConnection.executeSql("SELECT * FROM Film");
            while (rs.next()) {
                lijst.add(new Film(rs.getString("ID"),
                        rs.getString("Titel"),
                        rs.getString("Leeftijdsindicatie"),
                        rs.getString("Taal"),
                        rs.getTime("Tijdsduur"),
                        rs.getString("Genre")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lijst;
    }

    //Met deze functie kan je een Film zoeken in de database op ID.
    public Film read(String id) {
        Film film = null;
        try {
            String sqlQuery = "SELECT * FROM FILM WHERE ID=" + id;
            ResultSet rs = sqlConnection.executeSql(sqlQuery);
            rs.next();
            film = new Film(rs.getString("ID"),
                    rs.getString("Titel"),
                    rs.getString("Leeftijdsindicatie"),
                    rs.getString("Taal"),
                    rs.getTime("Tijdsduur"),
                    rs.getString("Genre"));

        } catch (Exception e) {
            System.out.println(e);
        }
        return film;
    }

    //Met deze functie kan je de films ophalen die door een gekozen profiel zijn bekeken
    public List<Film> filmsFromChosenAccount(String name) {
        Film film = null;
        List<Film> films = new ArrayList<Film>();
        try {
            String sqlQuery = "SELECT Film.Titel, Film.* " +
                    "FROM FILM JOIN Bekeken on Bekeken.Gezien = Film.ID " +
                    "WHERE Bekeken.Profielnaam ='" + name + "'";
            ResultSet rs = sqlConnection.executeSql(sqlQuery);
            while (rs.next()) {
                film = new Film(rs.getString("ID"),
                        rs.getString("Titel"),
                        rs.getString("Leeftijdsindicatie"),
                        rs.getString("Taal"),
                        rs.getTime("Tijdsduur"),
                        rs.getString("Genre"));
                films.add(film);

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        for (Film f : films){
            f.toString().replace("[", "");
            f.toString().replace("]", "");
        }
        return films;
    }

    //Met deze funcite haal je de film op met de langste tijdsduur en met een leeftijdsindicatie van onder de 16 jaar.
    public Film filmUnder16WithLongestDuration() {
        Film film = null;
        try {
            String sqlQuery = "SELECT TOP 1 FILM.Tijdsduur, * " +
                    "FROM Film " +
                    "WHERE FILM.Leeftijdsindicatie LIKE '%12%' OR FILM.Leeftijdsindicatie LIKE '%6%'  " +
                    "ORDER BY FILM.Tijdsduur DESC";
            ResultSet rs = sqlConnection.executeSql(sqlQuery);
            while (rs.next()) {
                film = new Film(rs.getString("ID"),
                        rs.getString("Titel"),
                        rs.getString("Leeftijdsindicatie"),
                        rs.getString("Taal"),
                        rs.getTime("Tijdsduur"),
                        rs.getString("Genre"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return film;
    }
}
