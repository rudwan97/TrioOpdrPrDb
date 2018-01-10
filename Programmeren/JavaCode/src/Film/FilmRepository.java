package Film;

import Connection.SqlConnection;
import Examples.Student;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FilmRepository {

    private SqlConnection sqlConnection;

    public FilmRepository(SqlConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public ArrayList<Film> readAll() {
        ArrayList<Film> lijst = new ArrayList<>();
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
        return films;
    }

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

//    public ArrayList<Film> filmsSeenByChosenProfile(String name) {
//        ArrayList<Film> films = new ArrayList<Film>();
//        Film film = null;
//
//        try {
//            String sqlQuery = "SELECT Film.Titel, Film.* FROM Bekeken JOIN Profiel on Profiel.Profielnaam = Bekeken.Profielnaam JOIN Film on Film.ID = Bekeken.Gezien WHERE Profiel.Profielnaam = '" + name + "'";
//            ResultSet rs = sqlConnection.executeSql(sqlQuery);
//            rs.next();
//            while (rs.next()) {
//                film = new Film(rs.getString("ID"),
//                        rs.getString("Titel"),
//                        rs.getString("Leeftijdsindicatie"),
//                        rs.getString("Taal"),
//                        rs.getTime("Tijdsduur"),
//                        rs.getString("Genre"));
//                films.add(film);
//
//            }
//
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return films;
//
//    }



}
