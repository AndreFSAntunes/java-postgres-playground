package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.model.Produto;

public class ProdutoDao {
    private Connection conn;

    public ProdutoDao(Connection conn) {
        this.conn = conn;
    }

    private void excluir(int id) {
        var sql = "delete from produto where id = ?";
        try (var statement = conn.prepareStatement(sql)) {
            statement.setLong(1, id);
            if (statement.executeUpdate() == 1)
                System.out.println("produto excluido com sucesso!");
            else System.out.println("produto não localizado");
        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta sql: " + e.getMessage());
        }
    }

    private void alterar(Produto produto) {
        var sql = "update produto set nome = ?, marca_id = ? , valor = ? where id = ?";
        try (var statement = conn.prepareStatement(sql)) {
            statement.setString(1, produto.getNome());
            statement.setLong(2, produto.getMarca().getId());
            statement.setDouble(3, produto.getValor());
            statement.setLong(4, produto.getId());
            statement.executeUpdate();
            System.out.println("produto inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta sql: " + e.getMessage());
        }

    }

    private void inserir(Produto produto) {
        var sql = "insert into produto (nome, marca_id, valor) values (?, ? , ?)";
        try (var statement = conn.prepareStatement(sql)) {
            statement.setString(1, produto.getNome());
            statement.setLong(2, produto.getMarca().getId());
            statement.setDouble(3, produto.getValor());
            statement.executeUpdate();
            System.out.println("produto inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta sql: " + e.getMessage());
        }

    }
}
