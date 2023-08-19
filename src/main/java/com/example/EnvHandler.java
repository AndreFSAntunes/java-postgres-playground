package com.example;

public class EnvHandler {

    static void loadEnvVariables() {
        String pathToEnvFile = "config.env";
        
        // Create a new instance of the EnvFileReader
        EnvFileReader envReader = null;
        envReader = AppBd.loadEnvFiles(pathToEnvFile, envReader);
        
        // Now you can use this instance to get the values from your .env file
        AppBd.dbHost = envReader.get("DB_HOST");
        AppBd.dbUser = envReader.get("DB_USER");
        AppBd.dbPass = envReader.get("DB_PASS");
    }
    
}
