package com.example.model;

public class Marca {
    private Long id;
    private String nome;

    public Marca(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public Marca() {
    }
    public Marca(long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
