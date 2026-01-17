package es.upm.etsisi.poo.grupo05.persistencepackage.adapters;

import com.google.gson.*;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.Product;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.ProductService;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.TicketElement;
import es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage.Receipt;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class ReceiptAdapter implements JsonDeserializer<Receipt> {

    @Override
    public Receipt deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Creamos un Gson auxiliar que sepa manejar la totalidad de los elementos
        // Necesitamos registrar los adaptadores para que pueda instanciar ProductMap y sus contenidos
        Gson tempGson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(Product.class, new ProductAdapter()) // <--- IMPORTANTE
                .registerTypeAdapter(ProductService.class, new ProductServiceAdapter())
                .create();

        // Dejamos que este Gson cree la instancia base de Receipt
        // Ahora no fallará al encontrar ProductMap o Product abstractos
        Receipt receipt = tempGson.fromJson(json, Receipt.class);

        // Ahora arreglamos la lista "ticket" manualmente
        JsonArray ticketArray = jsonObject.getAsJsonArray("ticket");
        if (ticketArray != null) {
            List<TicketElement> items = new LinkedList<>();
            
            for (JsonElement element : ticketArray) {
                // Aquí forzamos a GSON a usar el TicketElementAdapter (o ProductAdapter)
                // al decirle explícitamente que queremos un TicketElement.class
                TicketElement item = context.deserialize(element, TicketElement.class);
                items.add(item);
            }
            
            // Hola muy buenas, digamos que esto (entre muchas cosas) me lo ha hecho la ia, es una cosa que se llama reflexión
            // y es para hacer un bypass de la privacidad de algunas clases. Digamos que no lo entiendo. Muchas gracias por vuestro tiempo
            try {
                java.lang.reflect.Field ticketField = Receipt.class.getDeclaredField("ticket");
                ticketField.setAccessible(true);
                ticketField.set(receipt, items);
            } catch (Exception e) {
                throw new JsonParseException("Error inyectando lista de tickets: " + e.getMessage());
            }
        }

        return receipt;
    }
}
