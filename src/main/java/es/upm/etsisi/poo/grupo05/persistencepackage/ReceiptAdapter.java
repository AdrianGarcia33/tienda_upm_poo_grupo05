package es.upm.etsisi.poo.grupo05.persistencepackage;

import com.google.gson.*;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.TicketElement;
import es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage.Receipt;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class ReceiptAdapter implements JsonDeserializer<Receipt> {

    @Override
    public Receipt deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        //hacemos que gson sepa que es un objeto de tipo receipt
        Receipt receipt = new Gson().fromJson(json, Receipt.class);

        //Ahora arreglamos la lista "ticket" manualmente
        JsonArray ticketArray = jsonObject.getAsJsonArray("ticket");
        if (ticketArray != null) {
            List<TicketElement> items = new LinkedList<>();
            
            for (JsonElement element : ticketArray) {
                // Aquí forzamos a GSON a usar el TicketElementAdapter (o ProductAdapter)
                // al decirle explícitamente que queremos un TicketElement.class
                TicketElement item = context.deserialize(element, TicketElement.class);
                items.add(item);
            }
            // Ehm voy a ser sincero, esto (entre muchas cosas), me lo ha hecho la ia y es un cosa que se llama reflexión o algo así
            // para saltarse como la privacidad de las clases (ni idea la verdad). Pero funciona :=).
            
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
