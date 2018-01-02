package com.company;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


        SqlConnection connection = new SqlConnection();
        connection.connectDatabase("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetlflixStatistix;integratedSecurity=true;");

        AccountRepository accountRepository = new AccountRepository( connection);
        System.out.println(accountRepository.read("5602533"));

        connection.disconnectDatabase();

    }
}
