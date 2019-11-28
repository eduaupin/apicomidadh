package com.example.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.model.Evento;
import com.example.repository.EventosRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ListaEventosViewModel extends AndroidViewModel {
    private MutableLiveData<List<Evento>> listaEventos = new MutableLiveData<>();
    private MutableLiveData<String> erro = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private EventosRepository eventosRepository = new EventosRepository();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public ListaEventosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Evento>> pegaEventosLiveData() {
        return listaEventos;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<String> getErro() {
        return erro;
    }

    public void getAllEventosLocal() {
        disposable.add(
                eventosRepository.pegaTodosEventos(getApplication().getApplicationContext())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(subscription -> {
                            loading.setValue(true);
                        })
                        .doAfterTerminate(() -> {
                            loading.setValue(false);
                        })
                        .subscribe(eventos -> {
                            listaEventos.setValue(eventos);
                            loading.setValue(false);
                        }, throwable -> {
                            erro.setValue(throwable.getMessage());
                        })
        );
    }
}
