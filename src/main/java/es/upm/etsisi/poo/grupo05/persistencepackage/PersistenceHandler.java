package es.upm.etsisi.poo.grupo05.persistencepackage;

import com.google.gson.*;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.*;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PersistenceHandler {
    private final String catalogFile = "catalog.json";
    private final String userRegisterFile = "user_register.json";
    private final Gson gson;

    public PersistenceHandler() {
        // Configura Gson para leer (deserializar) y escribir el archivo final
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Product.class, new ProductAdapter())
                .registerTypeAdapter(User.class, new UserAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
    }

    public void updatePersistenceForProducts(ProductMap productMap) {
        try (FileWriter writer = new FileWriter(catalogFile)) {
            
            JsonArray jsonArray = new JsonArray();
            
            // IMPORTANTE: El Gson auxiliar también necesita saber manejar fechas
            Gson cleanGson = new GsonBuilder().create();

            for (Product product : productMap.getProductMap().values()) {
                JsonElement jsonElement = cleanGson.toJsonTree(product);
                
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    jsonObject.addProperty("type", product.getClass().getSimpleName());
                    jsonArray.add(jsonObject);
                }
            }

            gson.toJson(jsonArray, writer);

        } catch (IOException e) {
            System.out.println("Error al escribir en " + catalogFile + ": " + e.getMessage());
        }
    }

    public void updatePersistenceForUsers(UserMap userMap) {
        try (FileWriter writer = new FileWriter(userRegisterFile)) {

            JsonArray jsonArray = new JsonArray();

            // IMPORTANTE: El Gson auxiliar también necesita saber manejar fechas
            Gson cleanGson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();

            for (User user : userMap.getUserMap().values()) {

                JsonElement jsonElement = cleanGson.toJsonTree(user);

                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    jsonObject.addProperty("type", user.getClass().getSimpleName());
                    jsonArray.add(jsonObject);
                }
            }

            gson.toJson(jsonArray, writer);

        } catch (IOException e) {
            System.out.println("Error al escribir en " + userRegisterFile + ": " + e.getMessage());
        }
    }
}
