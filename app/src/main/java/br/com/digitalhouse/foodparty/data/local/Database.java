package br.com.digitalhouse.foodparty.data.local;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.digitalhouse.foodparty.model.Evento;
import br.com.digitalhouse.foodparty.model.Prato;

@androidx.room.Database(entities = {Prato.class, Evento.class}, version = 3, exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static volatile Database INSTANCE;

    public abstract PratoDAO pratoDao();
    public abstract EventoDAO eventoDao();

    public static Database getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, Database.class, "apicomidas_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
