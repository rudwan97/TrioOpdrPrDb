package Connection;

import org.junit.Test;
import org.junit.*;

import static org.junit.Assert.*;

public class SqlConnectionTest {
    @Test
    public void TestIfConnectDatabaseWorksReturnsTrue() throws Exception {

        //Arrange
        SqlConnection sqlConnection = new SqlConnection();
        sqlConnection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        //Act
        boolean connectDatabaseWorks = sqlConnection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        Assert.assertTrue(connectDatabaseWorks);

    }

}