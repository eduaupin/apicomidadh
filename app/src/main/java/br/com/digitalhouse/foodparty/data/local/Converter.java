package br.com.digitalhouse.foodparty.data.local;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import br.com.digitalhouse.foodparty.model.Ingrediente;
import br.com.digitalhouse.foodparty.model.Participante;
import br.com.digitalhouse.foodparty.model.Prato;

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

    @TypeConverter
    public static List<Prato> toPratoList(String value) {
        Type listType = new TypeToken<List<Prato>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromPratoList(List<Prato> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static List<Participante> toParticipanteList(String value) {
        Type listType = new TypeToken<List<Participante>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromParticipanteList(List<Participante> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
