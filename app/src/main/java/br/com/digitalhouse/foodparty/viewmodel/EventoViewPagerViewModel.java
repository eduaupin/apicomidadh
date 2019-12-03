package br.com.digitalhouse.foodparty.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

import br.com.digitalhouse.foodparty.model.Evento;

public class EventoViewPagerViewModel extends AndroidViewModel {
    private HashMap<Integer, MutableLiveData<Evento>> hash = new HashMap<>();

    public EventoViewPagerViewModel(@NonNull Application application) {
        super(application);
    }

    public void setEvento(int index, Evento cadaEvento) {
        if (!hash.containsKey(index)) {
            throw new IllegalStateException("Não há evento.");
        }
        MutableLiveData<Evento> mutableLiveData = hash.get(index);
        mutableLiveData.setValue(cadaEvento);
    }

    public LiveData<Evento> getEvento(int index) {
        if (!hash.containsKey(index)) {
            hash.put(index, new MutableLiveData<Evento>());
        }
        return hash.get(index);
    }

    public void flush(Object key) {
        hash.remove(key);
    }
}
