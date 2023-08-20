package com.example.dao;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Dao {
    protected Connection conn;

    public Dao(Connection conn) {
        this.conn = conn;
    }

    public void listar(String tabela, boolean lastFive) {
        String sql = "SELECT * FROM \"" + tabela + "\"";
        if (lastFive) sql += " order by id desc limit 5";
        try {
            var statement = conn.createStatement();
            var resultado = statement.executeQuery(sql);
            var metadata = resultado.getMetaData();
            int colunas = metadata.getColumnCount();

            printHeader(metadata, colunas);

            while (resultado.next()) {
                for (int i = 1; i <= colunas; i++) {
                    System.out.printf("%-23s | ", resultado.getString(i));
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta sql: " + e.getMessage());
        }
    }

    private void printHeader(ResultSetMetaData metadata, int colunas) throws SQLException {
        for (int i = 1; i <= colunas; i++) {
            System.out.printf("%-23s | ", metadata.getColumnName(i));
        }
        System.out.println();
        for (int i = 0; i < 26 * colunas; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
