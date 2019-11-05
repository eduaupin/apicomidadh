package com.example.model;

import androidx.fragment.app.Fragment;

public class ModelCard {


    private int imagem;
    private String nome;
    private String data;
    private Fragment fragment;


    public ModelCard(String nome, String data, int imagem, Fragment fragment) {
        this.imagem = imagem;
        this.nome = nome;
        this.data = data;
        this.fragment = fragment;
    }

    public ModelCard(String nome, String data, Fragment fragment) {
        this.nome = nome;
        this.data = data;
        this.fragment = fragment;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

}
