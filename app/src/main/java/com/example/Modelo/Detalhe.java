package com.example.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Detalhe implements Parcelable {

    private int imgViewDtlhe;
    private String txtDetalhe;


    public Detalhe(int imgViewDtlhe, String txtDetalhe) {
        this.imgViewDtlhe = imgViewDtlhe;
        this.txtDetalhe = txtDetalhe;
    }

    protected Detalhe(Parcel in) {
        imgViewDtlhe = in.readInt();
        txtDetalhe = in.readString();
    }




    public int getImgViewDtlhe() {
        return imgViewDtlhe;
    }

    public String getTxtDetalhe() {
        return txtDetalhe;
    }


    public void setImgViewDtlhe(int imgViewDtlhe) {
        this.imgViewDtlhe = imgViewDtlhe;
    }

    public void setTxtDetalhe(String txtDetalhe) {
        this.txtDetalhe = txtDetalhe;
    }



    public static final Creator<Detalhe> CREATOR = new Creator<Detalhe>() {
        @Override
        public Detalhe createFromParcel(Parcel in) {
            return new Detalhe(in);
        }

        @Override
        public Detalhe[] newArray(int size) {
            return new Detalhe[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imgViewDtlhe);
        parcel.writeString(txtDetalhe);

    }
}
