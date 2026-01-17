package es.upm.etsisi.poo.grupo05.persistencepackage.adapters;

import com.google.gson.*;
import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.*;
import java.lang.reflect.Type;

public class ProductAdapter implements JsonDeserializer<Product> {

    @Override
    public Product deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        if (!jsonObject.has("type")) {
            throw new JsonParseException("Type field is mandatory in order to deserializate object of the class Product");
        }

        String type = jsonObject.get("type").getAsString();

        Class<? extends Product> productClass;
        switch (type) {
            case "BasicProducts":
                productClass = BasicProducts.class;
                break;
            case "PersonalizedProducts":
                productClass = PersonalizedProducts.class;
                break;
            case "Lunch":
                productClass = Lunch.class;
                break;
            case "Meeting":
                productClass = Meeting.class;
                break;
            default:
                throw new JsonParseException(ExceptionHandler.getJsonParseException());
        }

        return context.deserialize(json, productClass);
    }
}
