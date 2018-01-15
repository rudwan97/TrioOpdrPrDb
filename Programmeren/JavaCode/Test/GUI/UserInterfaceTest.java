package GUI;

import Connection.SqlConnection;
import Film.Film;
import Film.FilmRepository;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.util.List;

import static org.junit.Assert.*;

public class UserInterfaceTest {
    @Test
    public void TestFilmComboboxShowsCorrectAmountOfItems() throws Exception {

        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        FilmRepository filmRepository = new FilmRepository(connection);
        List<Film> movies = filmRepository.readAll();

        UserInterface userInterface = new UserInterface();

        JComboBox box = userInterface.makeFilmTitleCombobox();
        int amountOfItems = box.getItemCount();
        int amountOfMovies = movies.size();

        boolean AmountOfItemsInBoxIsCorrect = false;
        if (amountOfItems == amountOfMovies){
            AmountOfItemsInBoxIsCorrect = true;
        }

        Assert.assertTrue(AmountOfItemsInBoxIsCorrect);
    }

}