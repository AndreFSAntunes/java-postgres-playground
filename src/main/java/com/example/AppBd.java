package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.example.dao.*;
import com.example.model.Marca;
import com.example.model.Produto;

public class AppBd {
    public static void main(String[] args) {
        new AppBd();
    }

    public AppBd() {
        // não é mais necessario?
        // carregarDriverJDBC();
        try (var conn = ConnectionManager.getConnection()) {
            var estadoDao = new EstadoDao(conn);
            estadoDao.listar();
            estadoDao.localizar("TO");

            // var marca = new Marca(42L);
            // var produto = new Produto(202l, "Universal Device PRO", marca, 2000d);
            var produtoDao = new ProdutoDao(conn);
            // produtoDao.inserir(produto);
            // produtoDao.excluir(204);
            // produtoDao.alterar(produto);
            listarDadosTabela(conn, "produto", true);
        } catch (SQLException e) {
            System.err.println("Não foi possivel conectaro ao banco de dados: " + e.getMessage());
        }
    }

    private void listarDadosTabela(Connection conn, String tabela, boolean lastFive) {
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

    
    // private void carregarDriverJDBC() {
    //     try {
    //         Class.forName("org.postgresql.Driver");

    //     } catch (ClassNotFoundException e) {
    //         System.err
    //                 .println("Não foi possível carregar a biblioteca para acesso ao banco de dados: " + e.getMessage());
    //     }
    // }
}