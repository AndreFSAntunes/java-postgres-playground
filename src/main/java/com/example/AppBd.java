package com.example;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import com.example.dao.*;
import com.example.model.Estado;
import com.example.model.Marca;
import com.example.model.Produto;
import com.example.model.RegiaoGeografica;

public class AppBd {
    public static void main(String[] args) {
        new AppBd();
    }

    public AppBd() {
        try (var conn = ConnectionManager.getConnection()) {
            var estadoDao = new EstadoDao(conn);
            // var estado = new Estado(
            //     "Terra do Nunca",
            //     "TN",
            //     new RegiaoGeografica(1l),
            //     100,
            //     15
            // );
            // estado.setId(29l);
            // estadoDao.inserir(estado);
            // estadoDao.alterar(estado);
            List<Estado> listaEstados = estadoDao.listar(true);
            for (var estado: listaEstados) {
                System.out.println(estado);
            }
            // estadoDao.localizar("TO");

            // var marca = new Marca(42L);
            // var produto = new Produto(205l, "Universal Device Plus", marca, 1499d);
            // var produtoDao = new ProdutoDao(conn);
            // produtoDao.inserir(produto);
            // produtoDao.excluir(206);
            // produtoDao.alterar(produto);
            // var dao = new Dao(conn);
            // dao.listar("estado", true);
        } catch (SQLException e) {
            System.err.println("Não foi possivel conectaro ao banco de dados: " + e.getMessage());
        }
    }
}