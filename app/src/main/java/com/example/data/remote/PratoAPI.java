package com.example.data.remote;


import com.example.model.PratosPopulares;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PratoAPI {

    @GET("search.php")
    Observable<PratosPopulares> searchPopulares(@Query("f") char letra);

}