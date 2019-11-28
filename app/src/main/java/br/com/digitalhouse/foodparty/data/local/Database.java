package br.com.digitalhouse.foodparty.data.local;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

<<<<<<< Updated upstream:app/src/main/java/com/example/data/local/Database.java
import com.example.model.Prato;
=======
import br.com.digitalhouse.foodparty.model.Evento;
import br.com.digitalhouse.foodparty.model.Prato;
>>>>>>> Stashed changes:app/src/main/java/br/com/digitalhouse/foodparty/data/local/Database.java

@androidx.room.Database(entities = {Prato.class}, version = 3, exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static volatile Database INSTANCE;

    public abstract PratoDAO pratoDao();

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
