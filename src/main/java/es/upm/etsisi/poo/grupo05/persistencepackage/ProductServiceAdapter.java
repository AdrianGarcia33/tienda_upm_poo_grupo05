package es.upm.etsisi.poo.grupo05.persistencepackage;

import com.google.gson.*;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.ProductService;
import java.lang.reflect.Type;

public class ProductServiceAdapter implements JsonSerializer<ProductService>, JsonDeserializer<ProductService> {

    @Override
    public JsonElement serialize(ProductService src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        jsonObject.addProperty("type", "ProductService");
        return jsonObject;
    }

    @Override
    public ProductService deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        return context.deserialize(json, ProductService.class);
    }
}
