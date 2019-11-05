package com.example.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.data.local.Database;
import com.example.data.local.PratoDAO;
import com.example.model.Prato;
import com.example.model.PratosPopulares;
import com.example.repository.PratosRepository;

import java.util.List;
import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.util.ConnectionUtil.isNetworkConnected;

public class HomeFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<List<Prato>> pratosPopulares = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private PratosRepository repository = new PratosRepository();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Prato>> getPratosLiveData() {
        return pratosPopulares;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public void getPratos() {
        if (isNetworkConnected(getApplication())) {
            getPratosPopularesRemote(letraAleatoria());
        } else {
            getPratosPopularesLocal();
        }
    }

    private void getPratosPopularesLocal() {
        disposable.add(
                repository.getLocalPratos(getApplication().getApplicationContext())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(subscription -> {
                            //TODO: Por que sem internet ele mantém o loading, mesmo mostrando o resultado atrás?
                            loading.setValue(true);
                        })
                        .doAfterTerminate(() -> {
                            loading.setValue(false);
                        })
                        .subscribe(pratos -> {
                            pratosPopulares.setValue(pratos);
                        }, throwable -> {
                            Log.i("PRATOS", "getFromLocal " + throwable.getMessage());
                        })
        );
    }

    private void getPratosPopularesRemote(char letra) {
        disposable.add(
                repository.getRemotePratos(letra)
                        .subscribeOn(Schedulers.io())
                        .map(this::saveOnDatabase)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> {
                            loading.setValue(true);
                        })
                        .doAfterTerminate(() -> {
                            loading.setValue(false);
                        })
                        .subscribe(pratosPopulares1 -> {
                            pratosPopulares.setValue(pratosPopulares1.getPratos());
                        }, throwable -> {
                            Log.i("PRATOS", "getFromRemote " + throwable.getMessage());
                        })
        );
    }

    private PratosPopulares saveOnDatabase(PratosPopulares pratosPopulares) {
        PratoDAO pratoDAO = Database.getDatabase(getApplication().getApplicationContext()).pratoDao();

        pratoDAO.deleteAll();

        pratoDAO.inserePratos(pratosPopulares.getPratos());

        return pratosPopulares;
    }

    private char letraAleatoria() {
        String alfabeto = "abcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        char letra = alfabeto.charAt(rnd.nextInt(alfabeto.length()));
        return letra;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
