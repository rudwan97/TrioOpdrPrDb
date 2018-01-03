package Film;

import Connection.SqlConnection;
import Examples.Student;


import java.sql.ResultSet;
import java.util.ArrayList;

public class FilmRepository {

    private SqlConnection sqlConnection;

    public FilmRepository(SqlConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public ArrayList<Film> readAll() {
        ArrayList<Film> lijst = new ArrayList<>();
        try {
            ResultSet rs = sqlConnection.executeSql("SELECT * FROM Film");
            while(rs.next()) {
                lijst.add(new Film(rs.getString("ID"),rs.getString("Titel"), rs.getString("Leeftijdsindicatie"), rs.getString("Taal"), rs.getString("Tijdsduur"), rs.getString("Genre")));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return lijst;
    }

    public Film read(String id) {
        Film film= null;
        try
        {
            String sqlQuery = "SELECT * FROM FILM WHERE ID=" + id;
            ResultSet rs = sqlConnection.executeSql(sqlQuery);
            rs.next();
            film = new Film(rs.getString("ID"),rs.getString("Titel"), rs.getString("Leeftijdsindicatie"), rs.getString("Taal"), rs.getString("Tijdsduur"), rs.getString("Genre"));

        }
        catch(Exception e) {
            System.out.println(e);
        }
        return film;
    }


}
