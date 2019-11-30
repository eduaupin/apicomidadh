package br.com.digitalhouse.foodparty.data.local;

import androidx.room.TypeConverter;

<<<<<<< Updated upstream:app/src/main/java/com/example/data/local/Converter.java
import com.example.model.Ingrediente;
=======
import br.com.digitalhouse.foodparty.model.Ingrediente;
import br.com.digitalhouse.foodparty.model.Participante;
import br.com.digitalhouse.foodparty.model.Prato;
>>>>>>> Stashed changes:app/src/main/java/br/com/digitalhouse/foodparty/data/local/Converter.java
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class Converter {
    @TypeConverter
    public Date toDate(Long timestamp) {
        if (timestamp == null) {
            return null;
        } else {
            return new Date(timestamp);
        }
    }

    @TypeConverter
    public Long toTimestamp(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public static List<Ingrediente> toIngredientList(String value) {
        Type listType = new TypeToken<List<Ingrediente>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromIngredientList(List<Ingrediente> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
