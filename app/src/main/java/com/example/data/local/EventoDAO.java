package com.example.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.model.Evento;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface EventoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insereEventos(List<Evento> eventos);

    @Query("SELECT * FROM eventos ORDER BY idEvento desc limit 3")
    Flowable<List<Evento>> pegaPrincipaisEventos();

    @Query("SELECT * FROM eventos")
    Flowable<List<Evento>> pegaTodosEventos();
}