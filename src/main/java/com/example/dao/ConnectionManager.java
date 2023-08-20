package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.handlers.EnvHandler;

public class ConnectionManager {

    public static String dbHost;
    public static String dbUser;
    public static String dbPass;

    public static void setDbHost(String dbHost) {
        ConnectionManager.dbHost = dbHost;
    }

    public static void setDbUser(String dbUser) {
        ConnectionManager.dbUser = dbUser;
    }

    public static void setDbPass(String dbPass) {
        ConnectionManager.dbPass = dbPass;
    }

    public static Connection getConnection() throws SQLException {
        EnvHandler.loadEnvVariables();
        return DriverManager.getConnection(dbHost, dbUser, dbPass);
    }
    
}
