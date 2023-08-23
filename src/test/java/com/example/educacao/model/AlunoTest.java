package com.example.educacao.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AlunoTest {
    @Test
    void testCalculaMedia() {
        var aluno = new Aluno();
        aluno.setNota1(11);
        aluno.setNota2(8);
        aluno.setNota3(8);
        var obtido = aluno.calculaMedia();
        assertEquals(8.66, obtido, 0.01);
    }
}
