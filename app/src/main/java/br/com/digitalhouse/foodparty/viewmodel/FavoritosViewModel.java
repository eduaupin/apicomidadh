package br.com.digitalhouse.foodparty.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.digitalhouse.foodparty.model.Prato;
import br.com.digitalhouse.foodparty.model.PratosFavoritos;
import br.com.digitalhouse.foodparty.util.AppUtil;
import io.reactivex.disposables.CompositeDisposable;

public class FavoritosViewModel extends AndroidViewModel {

    public MutableLiveData<Prato> favoriteAdded = new MutableLiveData<>();
    public MutableLiveData<Throwable> pratosFavoritosLiveDataError = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    public FavoritosViewModel(@NonNull Application application) {
        super(application);
    }

    public void salvarFavorito(Prato prato) {

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
