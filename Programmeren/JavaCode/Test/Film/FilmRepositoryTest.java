package Film;

import Connection.SqlConnection;
import org.junit.Assert;
import org.junit.Test;

public class FilmRepositoryTest {

    @Test
    public void TestfilmUnder16WithLongestDurationReturnCorrectFilmTitle() throws Exception {
        //Assert
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        FilmRepository filmRepository = new FilmRepository(connection);

        Film film = filmRepository.filmUnder16WithLongestDuration();
        boolean methodsReturnsCorrectValue = film.getTitle().equals("Der Untergang");

        Assert.assertTrue(methodsReturnsCorrectValue);
    }

    @Test
    public void TestfilmUnder16WithLongestDurationReturnCorrectfilmTitle2() throws Exception {
        //Assert
        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        FilmRepository filmRepository = new FilmRepository(connection);

        Film film = filmRepository.filmUnder16WithLongestDuration();
        boolean methodsReturnsCorrectValue = film.getTitle().equals("Pulp Fiction");

        Assert.assertFalse(methodsReturnsCorrectValue);
    }

}