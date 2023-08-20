package com.example.handlers;

import java.io.IOException;

import com.example.dao.ConnectionManager;

public class EnvHandler {

    public static void loadEnvVariables() {
        String pathToEnvFile = "config.env";
        
        // Create a new instance of the EnvFileReader
        EnvFileReader envReader = null;
        envReader = loadEnvFiles(pathToEnvFile, envReader);
        
        // Now you can use this instance to get the values from your .env file
    ConnectionManager.setDbHost(envReader.get("DB_HOST"));
    ConnectionManager.setDbUser(envReader.get("DB_USER"));
    ConnectionManager.setDbPass(envReader.get("DB_PASS"));
    }
    
    static EnvFileReader loadEnvFiles(String pathToEnvFile, EnvFileReader envReader) {
        try {
            envReader = new EnvFileReader(pathToEnvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return envReader;
    }
}
