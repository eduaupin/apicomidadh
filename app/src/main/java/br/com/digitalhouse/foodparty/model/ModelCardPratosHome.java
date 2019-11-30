package br.com.digitalhouse.foodparty.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelCardPratosHome implements Parcelable {

    private int imgPrato;
    private String txtPrato;
    private String descricao;

    public ModelCardPratosHome(int imgPrato, String txtPrato) {
        this.imgPrato = imgPrato;
        this.txtPrato = txtPrato;
}
    public ModelCardPratosHome( String txtPrato) {
        this.txtPrato = txtPrato;
    }

    protected ModelCardPratosHome(Parcel in) {
        imgPrato = in.readInt();
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
    public int getImgPrato() {
        return imgPrato;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imgPrato);
        dest.writeString(txtPrato);
    }
}
