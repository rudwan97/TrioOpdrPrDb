package Connection;

import java.sql.*;

public class SqlConnection {

    private Connection connection = null;

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
