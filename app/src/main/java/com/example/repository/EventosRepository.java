package com.example.repository;

import android.content.Context;

import com.example.data.local.Database;
import com.example.data.local.EventoDAO;
import com.example.model.Evento;

import java.util.List;

import io.reactivex.Flowable;

public class EventosRepository {

    public void insereEventos(Context context, List<Evento> eventos) {
        Database room = Database.getDatabase(context);
        EventoDAO eventoDAO = room.eventoDao();
        eventoDAO.insereEventos(eventos);
    }

    public Flowable<List<Evento>> pegaPrincipaisEventos(Context context) {
        Database room = Database.getDatabase(context);
        EventoDAO eventoDAO = room.eventoDao();
        return eventoDAO.pegaPrincipaisEventos();
    }

    public Flowable<List<Evento>> pegaTodosEventos(Context context) {
        Database room = Database.getDatabase(context);
        EventoDAO eventoDAO = room.eventoDao();
        return eventoDAO.pegaTodosEventos();
    }
}