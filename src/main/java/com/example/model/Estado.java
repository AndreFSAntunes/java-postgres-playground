package com.example.model;

public class Estado {
    private Long id;
    private String nome;
    private String uf;
    private RegiaoGeografica regiao;
    private int area;
    private int populacao;

    public Estado(String nome, String uf, RegiaoGeografica regiao, int area, int populacao) {
        this.nome = nome;
        this.uf = uf;
        this.regiao = regiao;
        this.area = area;
        this.populacao = populacao;
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
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }
    public RegiaoGeografica getRegiao() {
        return regiao;
    }
    public void setRegiao(RegiaoGeografica regiao) {
        this.regiao = regiao;
    }
    public int getArea() {
        return area;
    }
    public void setArea(int area) {
        this.area = area;
    }
    public int getPopulacao() {
        return populacao;
    }
    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    @Override
    public String toString() {
        return "Estado [id = " + id + ", nome = " + nome + ", uf = " + uf + ", populacao = " + populacao + "]";
    }
}
