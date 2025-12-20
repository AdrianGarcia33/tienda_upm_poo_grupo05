package es.upm.etsisi.poo.grupo05.persistencepackage;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.*;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println(ExceptionHandler.getIoExceptionMessage());;
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
            System.out.println(ExceptionHandler.getIoExceptionMessage());
        }
    }

    public void loadProducts(String file, ProductMap productMap) {

        try {
            FileReader fileReader = new FileReader(file);

            //this line is for setting the typing the json should converto to,
            Type listtype = new TypeToken<ArrayList<Product>>(){}.getType();

            ArrayList<Product> productList = gson.fromJson(fileReader, listtype);

            if (productList != null) {
                for (Product p : productList) {
                    productMap.addProduct(p);
                }
            }

        } catch (IOException e) {
            System.out.println(ExceptionHandler.getIoExceptionMessage());
        }
    }

    public void loadUsers(String file, UserMap userMap) {
        try {
            FileReader fileReader = new FileReader(file);

            //this line is for setting the typing the json should converto to,
            Type listtype = new TypeToken<ArrayList<User>>(){}.getType();

            ArrayList<User> userList = gson.fromJson(fileReader, listtype);

            if (userList != null) {
                for (User p : userList) {
                    userMap.addUser(p);
                }
            }

        } catch (IOException e) {
            System.out.println(ExceptionHandler.getIoExceptionMessage());
        }
    }
}
