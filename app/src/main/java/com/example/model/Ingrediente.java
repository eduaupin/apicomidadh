package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingrediente implements Parcelable {
    private String nome;
    private String medida;

    public Ingrediente(String nome, String medida) {
        this.nome = nome;
        this.medida = medida;
    }

    public Ingrediente() {
    }

    protected Ingrediente(Parcel in) {
        nome = in.readString();
        medida = in.readString();
    }

    public static final Creator<Ingrediente> CREATOR = new Creator<Ingrediente>() {
        @Override
        public Ingrediente createFromParcel(Parcel in) {
            return new Ingrediente(in);
        }

        @Override
        public Ingrediente[] newArray(int size) {
            return new Ingrediente[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nome);
        parcel.writeString(medida);
    }
}
