package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelPrato implements Parcelable {

    private int imgPrato;
    private String nomePrato;
    private String listaIngredientes;
    private String modoDePreparo;

    public ModelPrato(int imgPrato, String nomePrato, String modoDePreparo,String listaIngredientes) {
        this.imgPrato = imgPrato;
        this.nomePrato = nomePrato;
        this.modoDePreparo = modoDePreparo;
        this.listaIngredientes = listaIngredientes;
    }

    protected ModelPrato(Parcel in) {
        imgPrato = in.readInt();
        nomePrato = in.readString();
        modoDePreparo = in.readString();
        listaIngredientes = in.readString();
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

    public String getNomePrato() {
        return nomePrato;
    }

    public void setNomePrato(String nomePrato) {
        this.nomePrato = nomePrato;
    }

    public String getModoDePreparo() {
        return modoDePreparo;
    }

    public void setModoDePreparo(String modoDePreparo) {
        this.modoDePreparo = modoDePreparo;
    }

    public String getListaIngredientes() {
        return listaIngredientes;
    }

    public void setListaIngredientes(String listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imgPrato);
        dest.writeString(nomePrato);
        dest.writeString(modoDePreparo);
        dest.writeString(listaIngredientes);
    }
}