package com.example.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstadoDao {
    private Connection conn;

    public EstadoDao(Connection conn) {
        this.conn = conn;
    }

    public void localizar(String uf) {
        try {
            var sql = "select * from estado where uf = ?";
            var statement = conn.prepareStatement(sql);
            statement.setString(1, uf);
            var resultado = statement.executeQuery();
            printResultado(resultado);

        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta sql: " + e.getMessage());
            ;
        }
    }

    public void listar() throws SQLException {
        try {
            var statement = conn.createStatement();
            var estados = statement.executeQuery("select * from estado");
            printResultado(estados);

        } catch (SQLException e) {
            System.err.println("Algum erro na consulta sql: " + e.getMessage());
        }

    }

    private void printResultado(ResultSet resultado) throws SQLException {
        while (resultado.next()) {
            System.out.printf("Id: %-3d | Nome: %-20s | UF: %s\n", resultado.getInt("id"), resultado.getString("nome"),
                    resultado.getString("uf"));
        }
    }
}
