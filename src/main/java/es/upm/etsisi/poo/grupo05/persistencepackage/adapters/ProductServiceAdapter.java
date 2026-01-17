package es.upm.etsisi.poo.grupo05.persistencepackage.adapters;

import com.google.gson.*;
import es.upm.etsisi.poo.grupo05.persistencepackage.adapters.LocalDateAdapter;
import es.upm.etsisi.poo.grupo05.persistencepackage.adapters.LocalDateTimeAdapter;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.ProductService;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProductServiceAdapter implements JsonSerializer<ProductService>, JsonDeserializer<ProductService> {

    @Override
    public JsonElement serialize(ProductService src, Type typeOfSrc, JsonSerializationContext context) {
        // Usamos el contexto para serializar, pero añadimos el type manualmente
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        jsonObject.addProperty("type", "ProductService");
        return jsonObject;
    }

    @Override
    public ProductService deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // Para evitar el StackOverflow, NO usamos context.deserialize con la misma clase.
        // Usamos un Gson auxiliar que sepa manejar fechas pero que NO tenga este adaptador registrado.

        Gson tempGson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        return tempGson.fromJson(json, ProductService.class);
    }
}
