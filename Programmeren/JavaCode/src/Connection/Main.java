package Connection;

import GUI.UserInterface;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        UserInterface ui = new UserInterface();
        SwingUtilities.invokeLater(ui);

        connection.disconnectDatabase();

    }
}
