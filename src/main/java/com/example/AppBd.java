package com.example;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AppBd {
    public static String dbHost;
    public static String dbUser;
    public static String dbPass;
    public static void main(String[] args) {
        EnvHandler.loadEnvVariables();
        listarEstados();
    }

    private static void listarEstados() {
        carregarDriverJDBC();

        Statement statement = null;
        try (var conn = DriverManager.getConnection(dbHost, dbUser, dbPass)) {

            System.out.println("Conexão realizada com sucesso!");

            statement = conn.createStatement();
            var estados = statement.executeQuery("select * from estado");
            while (estados.next()) {
                System.out.printf("Id: %-3d - Nome: %-20s - UF: %s\n", estados.getInt("id"), estados.getString("nome"),
                        estados.getString("uf"));
            }

        } catch (SQLException e) {
            if (statement == null)
                System.err.println("Não foi possível conectar ao banco de dados: " + e.getMessage());
            else System.err.println("Algum erro na consulta sql: " + e.getMessage());
        }
    }

    private static void carregarDriverJDBC() {
        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            System.err
                    .println("Não foi possível carregar a biblioteca para acesso ao banco de dados: " + e.getMessage());
        }
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

