package br.com.digitalhouse.foodparty.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import br.com.digitalhouse.foodparty.model.Prato;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface PratoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void inserePratos(List<Prato> pratos);

    @Query("DELETE FROM pratos")
    void deleteAll();

    @Query("SELECT * FROM pratos")
    Flowable<List<Prato>> getAllPratos();

//    @Query("SELECT * FROM pratos WHERE favorito = 'true'")
//    Flowable<List<Prato>> getAllFavoritos();
}
