package com.example.model;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

public class ModelCard {



    private ImageView imagem;
    private String nome;
    private String data;
    private Fragment fragment;

    public ModelCard(String nome, String data, ImageView imagem, Fragment fragment) {
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

    public ImageView getImagem() {
        return imagem;
    }

    public void setImagem(ImageView imagem) {
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
