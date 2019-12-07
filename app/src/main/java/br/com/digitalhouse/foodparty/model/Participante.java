package br.com.digitalhouse.foodparty.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Participante implements Parcelable {
    private long id;
    private String nome;

    public Participante() {
    }

    public Participante(String nome) {
        this.nome = nome;
    }

    protected Participante(Parcel in) {
        id = in.readLong();
        nome = in.readString();
    }

    public static final Creator<Participante> CREATOR = new Creator<Participante>() {
        @Override
        public Participante createFromParcel(Parcel in) {
            return new Participante(in);
        }

        @Override
        public Participante[] newArray(int size) {
            return new Participante[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(nome);
    }
}
