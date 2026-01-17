package es.upm.etsisi.poo.grupo05.persistencepackage.adapters;

import com.google.gson.*;
import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Client;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

import java.lang.reflect.Type;

public class UserAdapter implements JsonDeserializer<User> {

    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // We read the type field of a our jsonelement
        if (!jsonObject.has("type")) {
            throw new JsonParseException("Type field is mandatory in order to deserializate object of the class Product");
        }

        String type = jsonObject.get("type").getAsString();

        // We choose the class we are deserializating
        Class<? extends User> userClass;
        switch (type) {
            case "Client":
                userClass = Client.class;
                break;
            case "Cashier":
                userClass = Cashier.class;
                break;

            default:
                throw new JsonParseException(ExceptionHandler.getJsonParseException());
        }

        return context.deserialize(json, userClass);
    }
}
