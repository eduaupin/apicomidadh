package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelCardPratosHome implements Parcelable {

    private int imgPrato;
    private String txtPrato;
    private String descricao;

    public ModelCardPratosHome( String txtPrato) {
        this.txtPrato = txtPrato;
    }


    protected ModelCardPratosHome(Parcel in) {
        txtPrato = in.readString();
    }

    public static final Creator<ModelCardPratosHome> CREATOR = new Creator<ModelCardPratosHome>() {
        @Override
        public ModelCardPratosHome createFromParcel(Parcel in) {
            return new ModelCardPratosHome(in);
        }

        @Override
        public ModelCardPratosHome[] newArray(int size) {
            return new ModelCardPratosHome[size];
        }
    };

    public void setImgPrato(int imgPrato) {
        this.imgPrato = imgPrato;
    }

    public String getTxtPrato() {
        return txtPrato;
    }

    public void setTxtPrato(String txtPrato) {
        this.txtPrato = txtPrato;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(txtPrato);
    }
}
