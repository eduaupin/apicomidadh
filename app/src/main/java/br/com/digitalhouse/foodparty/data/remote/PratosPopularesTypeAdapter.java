package br.com.digitalhouse.foodparty.data.remote;

import br.com.digitalhouse.foodparty.model.Ingrediente;
import br.com.digitalhouse.foodparty.model.Prato;
import br.com.digitalhouse.foodparty.model.PratosPopulares;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PratosPopularesTypeAdapter extends TypeAdapter<PratosPopulares> {

    @Override
    public void write(JsonWriter out, PratosPopulares value) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public PratosPopulares read(JsonReader in) throws IOException {
        final PratosPopulares pratosPopulares = new PratosPopulares();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "meals":
                    in.beginArray();
                    final List<Prato> pratoList = new ArrayList<>();
                    while (in.hasNext()) {
                        final Prato prato = new Prato();
                        List<Ingrediente> listaIngredientes = new ArrayList<>();
                        Ingrediente ingrediente1 = new Ingrediente();
                        Ingrediente ingrediente2 = new Ingrediente();
                        Ingrediente ingrediente3 = new Ingrediente();
                        Ingrediente ingrediente4 = new Ingrediente();
                        Ingrediente ingrediente5 = new Ingrediente();
                        Ingrediente ingrediente6 = new Ingrediente();
                        Ingrediente ingrediente7 = new Ingrediente();
                        Ingrediente ingrediente8 = new Ingrediente();
                        Ingrediente ingrediente9 = new Ingrediente();
                        Ingrediente ingrediente10 = new Ingrediente();
                        Ingrediente ingrediente11 = new Ingrediente();
                        Ingrediente ingrediente12 = new Ingrediente();
                        Ingrediente ingrediente13 = new Ingrediente();
                        Ingrediente ingrediente14 = new Ingrediente();
                        Ingrediente ingrediente15 = new Ingrediente();
                        Ingrediente ingrediente16 = new Ingrediente();
                        Ingrediente ingrediente17 = new Ingrediente();
                        Ingrediente ingrediente18 = new Ingrediente();
                        Ingrediente ingrediente19 = new Ingrediente();
                        Ingrediente ingrediente20 = new Ingrediente();
                        in.beginObject();
                        while (in.hasNext()) {

                            switch (in.nextName()) {

                                case "idMeal":
                                    prato.setIdMeal(in.nextString());
                                    break;
                                case "strMeal":
                                    prato.setStrMeal(in.nextString());
                                    break;
                                case "strDrinkAlternate":
                                    if (in.peek() != JsonToken.NULL) {
                                        prato.setStrDrinkAlternate(in.nextString());
                                    } else {
                                        in.nextNull();
                                        prato.setStrDrinkAlternate(null);
                                    }
                                    break;
                                case "strCategory":
                                    prato.setStrCategory(in.nextString());
                                    break;
                                case "strArea":
                                    prato.setStrArea(in.nextString());
                                    break;
                                case "strInstructions":
                                    prato.setStrInstructions(in.nextString());
                                    break;
                                case "strMealThumb":
                                    prato.setStrMealThumb(in.nextString());
                                    break;
                                case "strTags":
                                    if (in.peek() != JsonToken.NULL) {
                                        prato.setStrTags(in.nextString());
                                    } else {
                                        in.nextNull();
                                        prato.setStrTags(null);
                                    }
                                    break;
                                case "strYouTube":
                                    prato.setStrYoutube(in.nextString());
                                    break;
                                case "strIngredient1":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente1.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient2":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente2.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient3":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente3.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient4":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente4.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient5":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente5.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient6":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente6.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient7":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente7.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient8":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente8.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient9":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente9.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient10":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente10.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient11":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente11.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient12":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente12.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient13":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente13.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient14":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente14.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient15":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente15.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient16":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente16.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient17":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente17.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient18":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente18.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient19":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente19.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strIngredient20":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente20.setNome(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure1":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente1.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure2":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente2.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure3":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente3.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure4":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente4.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure5":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente5.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure6":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente6.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure7":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente7.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure8":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente8.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure9":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente9.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure10":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente10.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure11":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente11.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure12":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente12.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure13":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente13.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure14":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente14.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure15":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente15.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure16":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente16.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure17":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente17.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure18":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente18.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure19":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente19.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strMeasure20":
                                    if (in.peek() != JsonToken.NULL) {
                                        ingrediente20.setMedida(in.nextString());
                                    } else {
                                        in.skipValue();
                                    }
                                    break;
                                case "strSource":
                                    if (in.peek() != JsonToken.NULL) {
                                        prato.setStrSource(in.nextString());
                                    } else {
                                        in.nextNull();
                                        prato.setStrSource(null);
                                    }
                                    break;
                                case "dateMofified":
                                    if (in.peek() != JsonToken.NULL) {
                                        prato.setDateModified(in.nextString());
                                    } else {
                                        in.nextNull();
                                        prato.setDateModified(null);
                                    }
                                    break;

                                default:
                                    in.skipValue();
                                    break;

                            }

                        }
                        if (ingrediente1.getNome() != null && !ingrediente1.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente1);
                        }
                        if (ingrediente2.getNome() != null && !ingrediente2.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente2);
                        }
                        if (ingrediente3.getNome() != null && !ingrediente3.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente3);
                        }
                        if (ingrediente4.getNome() != null && !ingrediente4.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente4);
                        }
                        if (ingrediente5.getNome() != null && !ingrediente5.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente5);
                        }
                        if (ingrediente6.getNome() != null && !ingrediente6.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente6);
                        }
                        if (ingrediente7.getNome() != null && !ingrediente7.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente7);
                        }
                        if (ingrediente8.getNome() != null && !ingrediente8.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente8);
                        }
                        if (ingrediente9.getNome() != null && !ingrediente9.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente9);
                        }
                        if (ingrediente10.getNome() != null && !ingrediente10.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente10);
                        }
                        if (ingrediente11.getNome() != null && !ingrediente11.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente11);
                        }
                        if (ingrediente12.getNome() != null && !ingrediente12.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente12);
                        }
                        if (ingrediente13.getNome() != null && !ingrediente13.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente13);
                        }
                        if (ingrediente14.getNome() != null && !ingrediente14.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente14);
                        }
                        if (ingrediente15.getNome() != null && !ingrediente15.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente15);
                        }
                        if (ingrediente16.getNome() != null && !ingrediente16.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente16);
                        }
                        if (ingrediente17.getNome() != null && !ingrediente17.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente17);
                        }
                        if (ingrediente18.getNome() != null && !ingrediente18.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente18);
                        }
                        if (ingrediente19.getNome() != null && !ingrediente19.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente19);
                        }
                        if (ingrediente20.getNome() != null && !ingrediente20.getNome().isEmpty()) {
                            listaIngredientes.add(ingrediente20);
                        }
                        prato.setListaIngredientes(listaIngredientes);
                        pratoList.add(prato);
                        in.endObject();
                    }

                    in.endArray();
                    pratosPopulares.setPratos(pratoList);
                    break;
                default:
                    in.skipValue();
                    break;
            }

        }
        in.endObject();

        return pratosPopulares;
    }
}
