package com.example.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Notifica implements Parcelable {

    private String txtNotificacao;
    private String txtDataNotifica;

    public Notifica(String txtNotificacao, String txtDataNotifica) {
        this.txtNotificacao = txtNotificacao;
        this.txtDataNotifica = txtDataNotifica;
    }

    protected Notifica(Parcel in) {
        txtDataNotifica = in.readString();
        txtNotificacao = in.readString();
    }


    public String getTxtNotificacao() {
        return txtNotificacao;
    }

    public String getTxtDataNotifica() {
        return txtDataNotifica;
    }

    public void setTxtNotificacao(String txtNotificacao) {
        this.txtNotificacao = txtNotificacao;
    }

    public void setTxtDataNotifica(String txtDataNotifica) {
        this.txtDataNotifica = txtDataNotifica;
    }

    public static final Creator<Notifica> CREATOR = new Creator<Notifica>() {
        @Override
        public Notifica createFromParcel(Parcel in) {
            return new Notifica(in);
        }

        @Override
        public Notifica[] newArray(int size) {
            return new Notifica[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(txtDataNotifica);
        parcel.writeString(txtDataNotifica);
    }
}
