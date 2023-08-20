package com.example;

import java.sql.Connection;
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
        try (var conn = ConnectionManager.getConnection()) {
            // var estadoDao = new EstadoDao(conn);
            // estadoDao.listar();
            // estadoDao.localizar("TO");

            var marca = new Marca(42L);
            var produto = new Produto(205l, "Universal Device Plus", marca, 1499d);
            var produtoDao = new ProdutoDao(conn);
            // produtoDao.inserir(produto);
            // produtoDao.excluir(206);
            produtoDao.alterar(produto);
            var dao = new Dao(conn);
            dao.listar("produto", true);
        } catch (SQLException e) {
            System.err.println("Não foi possivel conectaro ao banco de dados: " + e.getMessage());
        }
    }
}