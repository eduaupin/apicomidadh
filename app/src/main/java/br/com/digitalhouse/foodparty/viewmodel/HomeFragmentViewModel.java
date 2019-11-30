package br.com.digitalhouse.foodparty.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import br.com.digitalhouse.foodparty.data.local.Database;
import br.com.digitalhouse.foodparty.data.local.PratoDAO;
import br.com.digitalhouse.foodparty.model.Prato;
import br.com.digitalhouse.foodparty.model.PratosPopulares;
import br.com.digitalhouse.foodparty.repository.PratosRepository;
import br.com.digitalhouse.foodparty.util.ConnectionUtil;

import java.util.List;
import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<List<Prato>> pratosPopulares = new MutableLiveData<>();
    private MutableLiveData<String> erro = new MutableLiveData<>();
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

    public LiveData<String> getErro() {
        return erro;
    }

    public void getPratos() {
        if (ConnectionUtil.isNetworkConnected(getApplication())) {
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
                            loading.setValue(true);
                        })
                        .doAfterTerminate(() -> {
                            loading.setValue(false);
                        })
                        .subscribe(pratos -> {
                            pratosPopulares.setValue(pratos);
                            loading.setValue(false);
                        }, throwable -> {
                            erro.setValue(throwable.getMessage());
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
                            erro.setValue(throwable.getMessage());
                            Log.i("PRATOS", "getPratosPopularesRemote: " + throwable.getMessage());
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
        String alfabeto = "abcdefghijklmnoprstvwy";
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
