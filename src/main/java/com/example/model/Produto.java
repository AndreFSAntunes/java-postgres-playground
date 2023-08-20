package com.example.model;

public class Produto {
    private Long id;
    private String nome;
    private Marca marca;
    private double valor;

    public Produto(Long id, String nome, Marca marca, double valor) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.valor = valor;
    }
    public Produto(String nome, Marca marca, double valor) {
        this.nome = nome;
        this.marca = marca;
        this.valor = valor;
    }
    public Produto() {
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
    public Marca getMarca() {
        return marca;
    }
    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
