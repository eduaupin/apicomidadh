package br.com.digitalhouse.foodparty.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.fragment.app.Fragment;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import br.com.digitalhouse.foodparty.data.local.Converter;

import java.util.List;

@Entity(tableName = "eventos")
@TypeConverters(Converter.class)
public class Evento implements Parcelable {
    public long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(long idEvento) {
        this.idEvento = idEvento;
    }

    @PrimaryKey(autoGenerate = true)
    private long idEvento;
    private int imgEvento;
    private String nomeEvento;
    private String dataEvento;
    private String enderecoEvento;
    private List<Prato> pratos;
    private List<Participante> participantes;
    @Ignore
    private Fragment fragment;

    public Evento() {
    }

    @Ignore
    public Evento(int imgEvento, String nomeEvento, String dataEvento, String enderecoEvento, List<Prato> pratos, List<Participante> participantes) {
        this.imgEvento = imgEvento;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.enderecoEvento = enderecoEvento;
        this.pratos = pratos;
        this.participantes = participantes;
    }

    @Ignore
    public Evento(int imgEvento, String nomeEvento, String dataEvento, String enderecoEvento, List<Prato> pratos, List<Participante> participantes, Fragment fragment) {
        this.imgEvento = imgEvento;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.enderecoEvento = enderecoEvento;
        this.pratos = pratos;
        this.participantes = participantes;
        this.fragment = fragment;
    }

    protected Evento(Parcel in) {
        imgEvento = in.readInt();
        nomeEvento = in.readString();
        dataEvento = in.readString();
        enderecoEvento = in.readString();
        pratos = in.createTypedArrayList(Prato.CREATOR);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idEvento);
        dest.writeInt(imgEvento);
        dest.writeString(nomeEvento);
        dest.writeString(dataEvento);
        dest.writeString(enderecoEvento);
        dest.writeTypedList(pratos);
    }
}

