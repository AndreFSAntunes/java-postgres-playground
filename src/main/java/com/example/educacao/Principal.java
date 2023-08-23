package com.example.educacao;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import com.example.dao.ConnectionManager;
import com.example.educacao.dao.alunoDao;
import com.example.educacao.model.Aluno;

public class Principal {
    public static void main(String[] args) {
        try(var conn = ConnectionManager.getConnection()) {
            var alunoDao = new alunoDao(conn);
            // var aluno = new aluno("Jedi Master");
            // alunoDao.inserir(aluno);
            // alunoDao.inserirNota(3, 5.05, alunoDao.buscaIdPorNome("Soupro"));
            // alunoDao.inserirNota(1, 10, aluno.getMatricula());
            // alunoDao.delete(alunoDao.buscaIdPorNome("KungFuFluter"));
            var aluno = alunoDao.buscaAluno(5);
            System.out.println(aluno.getNome() + " - média: " + aluno.calculaMedia());
            
        } catch (SQLException e) {
            System.err.println("Não foi possivel conectaro ao banco de dados: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Não foi possivel realizar operação: " + e.getMessage());
        }
    }
}
