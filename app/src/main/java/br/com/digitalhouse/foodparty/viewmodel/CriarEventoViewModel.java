package br.com.digitalhouse.foodparty.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import br.com.digitalhouse.foodparty.model.Evento;
import br.com.digitalhouse.foodparty.repository.EventosRepository;

public class CriarEventoViewModel extends AndroidViewModel {
    private MutableLiveData<Evento> evento = new MutableLiveData<>();
    private EventosRepository repository = new EventosRepository();

    public CriarEventoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Evento> getEvento() {
        return this.evento;
    }

    public void salvarEvento(Evento evento) {
        new Thread(() -> {
            if (evento != null) {
                repository.insereEvento(getApplication(), evento);
                Log.i("EVENTOSALVO", "salvarEvento: " + evento.toString());
            }
        }).start();

        this.evento.setValue(evento);
    }
}
