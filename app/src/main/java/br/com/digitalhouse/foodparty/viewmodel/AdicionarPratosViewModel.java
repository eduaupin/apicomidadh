package br.com.digitalhouse.foodparty.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.foodparty.model.Prato;
import br.com.digitalhouse.foodparty.repository.PratosRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AdicionarPratosViewModel extends AndroidViewModel {
    private MutableLiveData<List<Prato>> pratosRetornados = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<String> erro = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private PratosRepository repository = new PratosRepository();

    public AdicionarPratosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Prato>> getPratosRetornados() {
        return this.pratosRetornados;
    }

    public LiveData<Boolean> getLoading() {
        return this.loading;
    }

    public LiveData<String> getError() {
        return this.erro;
    }

    public void getPratosFromAPI(String query) {
        disposable.add(
                repository.getQueryPratos(query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> {
                            loading.setValue(true);
                        })
                        .doOnTerminate(() -> {
                            loading.setValue(false);
                        })
                        .subscribe(pratosSearchResult -> {
                            pratosRetornados.setValue(pratosSearchResult.getPratos());
                        }, throwable -> {
                            erro.setValue(throwable.getMessage());
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
