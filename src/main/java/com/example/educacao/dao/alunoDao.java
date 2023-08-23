package com.example.educacao.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;

import com.example.dao.Dao;
import com.example.educacao.model.Aluno;

public class alunoDao extends Dao {
    public alunoDao(Connection conn) {
        super(conn);
    }

    public void inserir(Aluno aluno) {
        var sql = "insert into aluno (nome) values (?)";
        try {
            var statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, aluno.getNome());
            statement.executeUpdate();
            var result = statement.getGeneratedKeys();
            if (result.next()) {
                aluno.setMatricula(result.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void inserirNota(int idNota, double nota, int matricula) {
        if (idNota <= 0 || idNota > 3) {
            throw new IllegalArgumentException("id da nota deve ser entre 1 e 3.");
        }
        var sql = "update aluno set nota" + idNota + " =  ? where matricula = ?";
        try {
            var statement = conn.prepareStatement(sql);
            statement.setDouble(1, nota);
            statement.setInt(2, matricula);
            statement.executeUpdate();
            System.out.println("Nota" +idNota+ " inserida com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer buscaIdPorNome(String nome) {
        var sql = "select matricula from aluno where nome = ?";
        try {
            var statement = conn.prepareStatement(sql);
            statement.setString(1, nome);
            var result = statement.executeQuery();
            if(result.next()){
                return result.getInt("matricula");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void delete(Integer matricula) {
        if (matricula == null) {
            System.out.println("Não existe aluno com a matricula fornecida");
            return;
        }
        var sql = "delete from aluno where matricula = ?";
        try {
            var statement = conn.prepareStatement(sql);
            statement.setInt(1, matricula);
            statement.executeUpdate();
            System.out.println("Aluno de matricula " + matricula + " excluido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Aluno buscaAluno(int matricula) {
        var sql = "select * from aluno where matricula = ?";
        try {
            var statement = conn.prepareStatement(sql);
            statement.setInt(1, matricula);
            var result = statement.executeQuery();
            if (result.next()) {
                var aluno = new Aluno(result.getString("nome"));
                aluno.setNota1(result.getDouble("nota1"));
                aluno.setNota2(result.getDouble("nota2"));
                aluno.setNota3(result.getDouble("nota3"));
                return aluno;
            } else {
                throw new NoSuchElementException("Aluno with matricula " + matricula + " not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
