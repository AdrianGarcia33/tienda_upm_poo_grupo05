package es.upm.etsisi.poo.grupo05.persistencepackage.adapters;

import com.google.gson.*;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.TicketElement;

import java.lang.reflect.Type;

public class TicketElementAdapter implements JsonDeserializer<TicketElement>, JsonSerializer<TicketElement> {
    @Override
    public JsonElement serialize(TicketElement src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = context.serialize(src).getAsJsonObject();
        obj.addProperty("type", src.getClass().getSimpleName());
        return obj;
    }

    @Override
    public TicketElement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if ("ProductService".equals(type)) {
            // return context.deserialize(json, ProductService.class);
            // Necesitas usar el adaptador específico o la clase
            try {
                return context.deserialize(json, Class.forName("es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.ProductService"));
            } catch (ClassNotFoundException e) {
                throw new JsonParseException(e);
            }
        } else {
            // Es un Product (Basic, Lunch, etc.)
            // Delegamos en el ProductAdapter
            try {
                return context.deserialize(json, Class.forName("es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.Product"));
            } catch (ClassNotFoundException e) {
                throw new JsonParseException(e);
            }
        }
    }
}
