package Connection;

import java.sql.*;

public class SqlConnection {

    private Connection connection = null;


    //Verbindt de database met dit project
    public boolean connectDatabase(String connectionUrl) {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.connection = DriverManager.getConnection(connectionUrl);
        }
        catch(Exception e)
        {
            System.out.println(e);
            connection=null;
            return false;
        }
        return true;
    }

    //Verbreekt de verbinding tussen de database en het project
    public void disconnectDatabase() {
        if (connection != null) {
            try {
                connection.close();
            }
            catch(Exception e) {
                System.out.println(e);
            }
            connection=null;
        }
    }

    //Geeft een resultset gebaseerd op een meegekregen query
    public ResultSet executeSql(String sqlQuery) {
        ResultSet rs = null;
        try
        {
            Statement statement = this.connection.createStatement();
            rs= statement.executeQuery(sqlQuery);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

       return rs;
    }

    //Voert een sql functie uit zonder een result (Voor het maken en verwijderen van gegevens in de database)
    public boolean executeSqlNoResult(String sqlQuery) {
        try
        {
            Statement statement = this.connection.createStatement();
            return statement.execute(sqlQuery);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
}
