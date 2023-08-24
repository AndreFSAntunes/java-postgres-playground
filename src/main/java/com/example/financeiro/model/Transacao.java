package com.example.financeiro.model;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Transacao {
    private Integer id;
    private String cliente;
    private double valor;
    private String moeda;
    private String tipo;
    private LocalDateTime data;

    public Transacao(String cliente, double valor, String moeda, String tipo) {
        this.cliente = cliente;
        this.valor = valor;
        this.moeda = moeda;
        this.tipo = tipo;
    }

    public List<Transacao> todasTransacoes = new LinkedList<>();

    public void adicionarTransacao(Transacao t) {
        todasTransacoes.add(t);
    }

    public List<Transacao> filtrar(String cliente, String tipo) {
        var transacoesFiltradas = new LinkedList<Transacao>();
        for (Transacao t : todasTransacoes) {
            if ((cliente == null || t.cliente.equals(cliente)) ||
                    (tipo == null || t.tipo.equals(tipo))) {
                transacoesFiltradas.add(t);
            }
        }
        // USING STREAMS
        // return todasTransacoes.stream()
        // .filter(t -> (cliente == null || t.cliente.equals(cliente))
        // || (tipo == null || t.tipo.equals(tipo)))
        // .collect(Collectors.toList()); // newer version u just need toList()

        // USING STREAM + PREDICATE
        // Predicate<Transacao> clientePredicate = t -> cliente == null || t.cliente.equals(cliente);
        // Predicate<Transacao> tipoPredicate = t -> tipo == null || t.tipo.equals(tipo);
        // return todasTransacoes.stream()
        //         .filter(clientePredicate.or(tipoPredicate))
        //         .collect(Collectors.toList()); // newer version u just need toList()
                //.collect(Collectors.toCollection(LinkedList::new)) // if you want to return a linkedlist

        return transacoesFiltradas;
    }

    public double getSaldo(String Cliente) {
        double saldo = 0d;
        for (Transacao t : todasTransacoes) {
            if (t.cliente.equals(cliente)) {
                saldo += t.valor;
            }
        }
        return saldo;
    }

    public Integer getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public double getValor() {
        return valor;
    }

    public String getMoeda() {
        return moeda;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public List<Transacao> getTodasTransacoes() {
        return todasTransacoes;
    }

}
