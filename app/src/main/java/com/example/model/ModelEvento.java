package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ModelEvento implements Parcelable {

    private int imgEvento;
    private String nomeEvento;
    private String dataEvento;
    private String enderecoEvento;
    private List<ModelCardPratosHome> pratos;


    public ModelEvento(int imgEvento, String nomeEvento, String dataEvento, String enderecoEvento, List<ModelCardPratosHome> pratos) {
        this.imgEvento = imgEvento;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.enderecoEvento = enderecoEvento;
        this.pratos = pratos;
    }


    protected ModelEvento(Parcel in) {
        imgEvento = in.readInt();
        nomeEvento = in.readString();
        dataEvento = in.readString();
        enderecoEvento = in.readString();
        pratos = in.createTypedArrayList(ModelCardPratosHome.CREATOR);
    }

    public static final Parcelable.Creator<ModelEvento> CREATOR = new Parcelable.Creator<ModelEvento>() {
        @Override
        public ModelEvento createFromParcel(Parcel in) {
            return new ModelEvento(in);
        }

        @Override
        public ModelEvento[] newArray(int size) {
            return new ModelEvento[size];
        }
    };

    public int getImgEvento() {
        return imgEvento;
    }

    public void setImgEvento(int imgEvento) {
        this.imgEvento = imgEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getEnderecoEvento() {
        return enderecoEvento;
    }

    public void setEnderecoEvento(String enderecoEvento) {
        this.enderecoEvento = enderecoEvento;
    }

    public List<ModelCardPratosHome> getPratos() {
        return pratos;
    }

    public void setPratos(List<ModelCardPratosHome> pratos) {
        this.pratos = pratos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imgEvento);
        dest.writeString(nomeEvento);
        dest.writeString(dataEvento);
        dest.writeString(enderecoEvento);
        dest.writeTypedList(pratos);
    }
}

