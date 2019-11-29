package br.com.digitalhouse.foodparty.repository;

import android.content.Context;

import br.com.digitalhouse.foodparty.data.local.PratoDAO;
import br.com.digitalhouse.foodparty.data.remote.RetrofitService;
import br.com.digitalhouse.foodparty.data.local.Database;
import br.com.digitalhouse.foodparty.model.Prato;
import br.com.digitalhouse.foodparty.model.PratosPopulares;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class PratosRepository {

    public Flowable<List<Prato>> getLocalPratos(Context context) {
        Database room = Database.getDatabase(context);
        PratoDAO pratoDAO = room.pratoDao();

        return pratoDAO.getAllPratos();
    }

//    public Flowable<List<Prato>> getLocalPratosFavoritos(Context context) {
//        Database room = Database.getDatabase(context);
//        PratoDAO pratoDAO = room.pratoDao();
//
//        return pratoDAO.getAllFavoritos();
//    }

    public void insertOnDatabase(Context context, List<Prato> pratos) {
        Database room = Database.getDatabase(context);
        PratoDAO pratoDao = room.pratoDao();
        pratoDao.inserePratos(pratos);
    }

    public Observable<PratosPopulares> getRemotePratos(char letra) {
        return RetrofitService.getApiService().searchPopulares(letra);
    }


}
