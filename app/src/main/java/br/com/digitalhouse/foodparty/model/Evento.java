package br.com.digitalhouse.foodparty.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.fragment.app.Fragment;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import br.com.digitalhouse.foodparty.data.local.Converter;

@Entity(tableName = "eventos")
@TypeConverters(Converter.class)
public class Evento implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private long idEvento;
    private String imgEvento;
    private String nomeEvento;
    private String dataEvento;
    private String horaEvento;
    private String enderecoEvento;
    private List<Prato> pratos;
    private List<Participante> participantes;
    @Ignore
    private Fragment fragment;

    public Evento() {
    }

    public Evento(String imgEvento, String nomeEvento, String dataEvento, String horaEvento, String enderecoEvento, List<Prato> pratos, List<Participante> participantes) {
        this.imgEvento = imgEvento;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.horaEvento = horaEvento;
        this.enderecoEvento = enderecoEvento;
        this.pratos = pratos;
        this.participantes = participantes;
    }

    @Ignore
    public Evento(String imgEvento, String nomeEvento, String dataEvento, String horaEvento, String enderecoEvento, List<Prato> pratos, List<Participante> participantes, Fragment fragment) {
        this.imgEvento = imgEvento;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.horaEvento = horaEvento;
        this.enderecoEvento = enderecoEvento;
        this.pratos = pratos;
        this.participantes = participantes;
        this.fragment = fragment;
    }

    protected Evento(Parcel in) {
        idEvento = in.readLong();
        imgEvento = in.readString();
        nomeEvento = in.readString();
        dataEvento = in.readString();
        horaEvento = in.readString();
        enderecoEvento = in.readString();
        pratos = in.createTypedArrayList(Prato.CREATOR);
        participantes = in.createTypedArrayList(Participante.CREATOR);
    }

    public static final Creator<Evento> CREATOR = new Creator<Evento>() {
        @Override
        public Evento createFromParcel(Parcel in) {
            return new Evento(in);
        }

        @Override
        public Evento[] newArray(int size) {
            return new Evento[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(idEvento);
        parcel.writeString(imgEvento);
        parcel.writeString(nomeEvento);
        parcel.writeString(dataEvento);
        parcel.writeString(horaEvento);
        parcel.writeString(enderecoEvento);
        parcel.writeTypedList(pratos);
        parcel.writeTypedList(participantes);
    }

    public long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(long idEvento) {
        this.idEvento = idEvento;
    }

    public String getImgEvento() {
        return imgEvento;
    }

    public void setImgEvento(String imgEvento) {
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

    public String getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(String horaEvento) {
        this.horaEvento = horaEvento;
    }

    public String getEnderecoEvento() {
        return enderecoEvento;
    }

    public void setEnderecoEvento(String enderecoEvento) {
        this.enderecoEvento = enderecoEvento;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}

