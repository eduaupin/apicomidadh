package com.example.model;

public class Participante {
    private long id;
    private String nome;

    public Participante() {
    }

    public Participante(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
