package es.upm.etsisi.poo.grupo05.persistencepackage;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.persistencepackage.adapters.*;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.*;
import es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage.Receipt;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Client;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class created to manage persistence, it is based on the library Gson from google: https://github.com/google/gson
 */
public class PersistenceHandler {
    private final String catalogFile = "catalog.json";
    private final String servicesFile = "services.json";
    private final String userRegisterFile = "user_register.json";
    private final Gson gson;

    /**
     * Builder of this class, where it creates the base configuration for the gson used in deserializing
     */
    public PersistenceHandler() {
        // Configuración para deserializar (pasar de json a objetos de java)
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(User.class, new UserAdapter())
                .registerTypeAdapter(ProductService.class, new ProductServiceAdapter())
                .registerTypeAdapter(Product.class, new ProductAdapter())
                .registerTypeAdapter(TicketElement.class, new TicketElementAdapter())
                .registerTypeAdapter(Receipt.class, new ReceiptAdapter())
                .setPrettyPrinting()
                .create();
    }

    /**
     * @return gson used for serializing
     */
    private Gson createWritingGson() {
        // Serializador para productos
        JsonSerializer<Product> productSerializer = (src, typeOfSrc, context) -> {
            JsonObject jsonObject = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create()
                    .toJsonTree(src).getAsJsonObject();
            jsonObject.addProperty("type", src.getClass().getSimpleName());
            return jsonObject;
        };
        
        //Serializador para TicketElement (para cubrir ProductService y Product)
        JsonSerializer<TicketElement> ticketElementSerializer = (src, typeOfSrc, context) -> {
             JsonObject jsonObject = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create()
                    .toJsonTree(src).getAsJsonObject();
            jsonObject.addProperty("type", src.getClass().getSimpleName());
            return jsonObject;
        };

        // 3. Serializador para usuarios
        JsonSerializer<User> userSerializer = (src, typeOfSrc, context) -> {
            Gson tempGson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())

                    //registramos los objectos de tipo product y clases derivadas, también los tickeelements y clases derivadads.
                    .registerTypeAdapter(Product.class, productSerializer)
                    .registerTypeAdapter(BasicProducts.class, productSerializer)
                    .registerTypeAdapter(PersonalizedProducts.class, productSerializer)
                    .registerTypeAdapter(Lunch.class, productSerializer)
                    .registerTypeAdapter(Meeting.class, productSerializer)
                    .registerTypeAdapter(ProductService.class, ticketElementSerializer)
                    .registerTypeAdapter(TicketElement.class, ticketElementSerializer)
                    .create();

            JsonObject jsonObject = tempGson.toJsonTree(src).getAsJsonObject();
            jsonObject.addProperty("type", src.getClass().getSimpleName());
            return jsonObject;
        };

        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                
                // Registramos serializadores
                .registerTypeAdapter(Product.class, productSerializer)
                .registerTypeAdapter(BasicProducts.class, productSerializer)
                .registerTypeAdapter(PersonalizedProducts.class, productSerializer)
                .registerTypeAdapter(Lunch.class, productSerializer)
                .registerTypeAdapter(Meeting.class, productSerializer)
                .registerTypeAdapter(ProductService.class, ticketElementSerializer)
                .registerTypeAdapter(TicketElement.class, ticketElementSerializer)

                .registerTypeAdapter(User.class, userSerializer)
                .registerTypeAdapter(Client.class, userSerializer)
                .registerTypeAdapter(Cashier.class, userSerializer)
                
                .setPrettyPrinting()
                .create();
    }

    /**
     * Method to update our json file with new products (catalog.json)
     * @param productMap
     */
    public void updatePersistenceForProducts(ProductMap productMap) {
        try (FileWriter writer = new FileWriter(catalogFile)) {
            ArrayList<Product> productList = new ArrayList<>(productMap.getProductMap().values());
            createWritingGson().toJson(productList, writer);
        } catch (IOException e) {
            System.out.println(ExceptionHandler.getIoExceptionMessage());
        }
    }

    /**
     * Method to update our json file with new users (user_register.json)
     * @param userMap
     */
    public void updatePersistenceForUsers(UserMap userMap) {
        try (FileWriter writer = new FileWriter(userRegisterFile)) {
            //Lo mismo aqui, como siempre, importante diferencia en este caso que tenemos dos gson, el de escribir y el de leer
            ArrayList<User> userList = new ArrayList<>(userMap.getUserMap().values());
            createWritingGson().toJson(userList, writer);
        } catch (IOException e) {
            System.out.println(ExceptionHandler.getIoExceptionMessage());
        }
    }

    /**
     * Method to update our json file with services (services.json)
     * @param productMap
     */
    public void updatePersistenceForServices(ProductMap productMap) {
        try (FileWriter writer = new FileWriter(servicesFile)) {
            ArrayList<ProductService> serviceList = new ArrayList<>(productMap.getServiceMap().values());
            createWritingGson().toJson(serviceList, writer);
        } catch (IOException e) {
            System.out.println(ExceptionHandler.getIoExceptionMessage());
        } catch (NullPointerException e ) {
        System.out.println(ExceptionHandler.getNullPointerPersistence());
        }
    }

    /**
     * We load the usermap with objects read from the jsonfile
     * @param userMap
     */
    public void loadUsers(UserMap userMap) {
        File file = new File(userRegisterFile);
        if (!file.exists()) return;

        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<User>>(){}.getType();
            ArrayList<User> users = gson.fromJson(reader, listType);
            //En este caso como vemos, usamos el gson para leer
            if (users != null) {
                for (User u : users) {
                    userMap.addUser(u);
                }
            }

        } catch (IOException e) {
            System.out.println(ExceptionHandler.getIoExceptionMessage());
        } catch (NullPointerException e ) {
            System.out.println(ExceptionHandler.getNullPointerPersistence());
        }
    }

    /**
     * We load the product hashmap of our productmap with objects of the corresponding json file
     * @param productMap
     */
    public void loadProducts(ProductMap productMap) {
        File file = new File(catalogFile);
        if (!file.exists()) return;

        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Product>>(){}.getType();
            ArrayList<Product> products = gson.fromJson(reader, listType);

            if (products != null) {
                for (Product p : products) {
                    productMap.addProduct(p);
                }
            }

        } catch (IOException e) {
            System.out.println(ExceptionHandler.getIoExceptionMessage());
        } catch (NullPointerException e ) {
            System.out.println(ExceptionHandler.getNullPointerPersistence());
        }
    }

    /**
     * We load the hashmap for services from our productmap with objects of the corresponding jsonfile
     * @param productMap
     */
    public void loadServices(ProductMap productMap) {
        File file = new File(servicesFile);
        if (!file.exists()) return;

        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<ProductService>>(){}.getType();
            ArrayList<ProductService> services = gson.fromJson(reader, listType);

            if (services != null) {
                for (ProductService s : services) {
                    productMap.addService(s);
                }
            }

        } catch (IOException e) {
            System.out.println(ExceptionHandler.getIoExceptionMessage());
        } catch (NullPointerException e ) {
            System.out.println(ExceptionHandler.getNullPointerPersistence());
        }
    }
}
