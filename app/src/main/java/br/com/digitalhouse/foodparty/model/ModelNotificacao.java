package br.com.digitalhouse.foodparty.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelNotificacao implements Parcelable {

    private String title;
    private String date;

    public ModelNotificacao(String title, String date) {
        this.title = title;
        this.date = date;
    }

    protected ModelNotificacao(Parcel in) {
        title = in.readString();
        date = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public static final Creator<ModelNotificacao> CREATOR = new Creator<ModelNotificacao>() {
        @Override
        public ModelNotificacao createFromParcel(Parcel in) {
            return new ModelNotificacao(in);
        }

        @Override
        public ModelNotificacao[] newArray(int size) {
            return new ModelNotificacao[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(date);
    }
}
