package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelPrato implements Parcelable {

    private int imgPrato;
    private String nomePRato;
    private String descricaoPrato;

    public ModelPrato(int imgPrato, String nomePRato, String descricaoPrato) {
        this.imgPrato = imgPrato;
        this.nomePRato = nomePRato;
        this.descricaoPrato = descricaoPrato;
    }

    protected ModelPrato(Parcel in) {
        imgPrato = in.readInt();
        nomePRato = in.readString();
        descricaoPrato = in.readString();
    }

    public static final Parcelable.Creator<ModelPrato> CREATOR = new Parcelable.Creator<ModelPrato>() {
        @Override
        public ModelPrato createFromParcel(Parcel in) {
            return new ModelPrato(in);
        }

        @Override
        public ModelPrato[] newArray(int size) {
            return new ModelPrato[size];
        }
    };

    public int getImgPrato() {
        return imgPrato;
    }

    public void setImgPrato(int imgPrato) {
        this.imgPrato = imgPrato;
    }

    public String getNomePRato() {
        return nomePRato;
    }

    public void setNomePRato(String nomePRato) {
        this.nomePRato = nomePRato;
    }

    public String getDescricaoPrato() {
        return descricaoPrato;
    }

    public void setDescricaoPrato(String descricaoPrato) {
        this.descricaoPrato = descricaoPrato;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imgPrato);
        dest.writeString(nomePRato);
        dest.writeString(descricaoPrato);
    }
}