package br.com.digitalhouse.foodparty.repository;

import android.content.Context;

import br.com.digitalhouse.foodparty.data.local.Database;
import br.com.digitalhouse.foodparty.model.Evento;
import br.com.digitalhouse.foodparty.data.local.EventoDAO;

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