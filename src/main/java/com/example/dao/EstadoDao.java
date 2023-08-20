package com.example.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.example.model.Estado;
import com.example.model.RegiaoGeografica;

public class EstadoDao extends Dao{

    public EstadoDao(Connection conn) {
        super(conn);
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

    public void listar(){
        try {
            var statement = conn.createStatement();
            var estados = statement.executeQuery("select * from estado");
            printResultado(estados);

        } catch (SQLException e) {
            System.err.println("Algum erro na consulta sql: " + e.getMessage());
        }

    }

    public List<Estado> listar(boolean returnList){
        if (!returnList) listar();
        List<Estado> listaEstados = new LinkedList<>();
        try {
            var statement = conn.createStatement();
            var estados = statement.executeQuery("select * from estado");
            while (estados.next()) {
                var novoEstado = new Estado(
                    estados.getString("nome"),
                    estados.getString("uf"),
                    new RegiaoGeografica(estados.getLong("regiao_id")),
                    estados.getInt("area_km2"),
                    estados.getInt("populacao")
                );
                novoEstado.setId(estados.getLong("id"));
                listaEstados.add(novoEstado);
            }

        } catch (SQLException e) {
            System.err.println("Algum erro na consulta sql: " + e.getMessage());
        }
        return listaEstados;
    }

    private void printResultado(ResultSet resultado) throws SQLException {
        while (resultado.next()) {
            System.out.printf("Id: %-3d | Nome: %-20s | UF: %s\n", resultado.getInt("id"), resultado.getString("nome"),
                    resultado.getString("uf"));
        }
    }

    public void inserir(Estado estado) {
        var sql = "insert into estado (nome, uf, regiao_id, area_km2, populacao) values (?, ? , ?, ?, ?)";
        try (var statement = conn.prepareStatement(sql)) {
            statement.setString(1, estado.getNome());
            statement.setString(2, estado.getUf());
            statement.setLong(3, estado.getRegiao().getId());
            statement.setInt(4, estado.getArea());
            statement.setInt(5, estado.getPopulacao());
            statement.executeUpdate();
            System.out.println("Estado inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta sql: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        var sql = "delete from estado where id = ?";
        try (var statement = conn.prepareStatement(sql)) {
            statement.setLong(1, id);
            if (statement.executeUpdate() == 1)
                System.out.println("produto excluido com sucesso!");
            else System.out.println("produto não localizado");
        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta sql: " + e.getMessage());
        }
    }

    public void alterar(Estado estado) {
        var sql = "update estado set nome = ?, uf = ?, regiao_id = ?, area_km2 = ?, populacao = ? where id = ?";
        try (var statement = conn.prepareStatement(sql)) {
            statement.setString(1, estado.getNome());
            statement.setString(2, estado.getUf());
            statement.setLong(3, estado.getRegiao().getId());
            statement.setInt(4, estado.getArea());
            statement.setInt(5, estado.getPopulacao());
            statement.setLong(6, estado.getId());
            statement.executeUpdate();
            System.out.println("Estado alterado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao excutar consulta slq: " + e.getMessage());
        }
    }
}
