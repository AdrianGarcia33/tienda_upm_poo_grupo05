package es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage;

import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.*;
import java.util.Locale;
import java.util.List;
import java.util.stream.Collectors;

public class EnterprisePrinter<T extends TicketElement> implements ReceiptPrinter<T> {
    @Override
    public String format(Receipt<T> receipt) {
        StringBuilder sb = new StringBuilder();
        List<T> items = receipt.getTicketItems(); //

        // 1. Filtrar servicios y productos para el conteo de descuento
        List<ProductService> services = items.stream()
                .filter(i -> i instanceof ProductService)
                .map(i -> (ProductService) i)
                .collect(Collectors.toList());

        List<Product> products = items.stream()
                .filter(i -> i instanceof Product && !(i instanceof ProductService))
                .map(i -> (Product) i)
                .collect(Collectors.toList());

        int nServicios = services.size();
        double extraDiscountRate = nServicios * 0.15; // 15% por cada servicio

        // 2. Encabezado principal
        sb.append("Ticket : ").append(receipt.getId()).append("\n");

        // 3. Bloque de Servicios Incluidos
        if (!services.isEmpty()) {
            sb.append("Services Included: \n");
            for (ProductService s : services) {
                sb.append("  {class:").append(s.getClass().getSimpleName())
                        .append(", id:").append(s.getId())
                        .append(", category:").append(s.getServiceType())
                        .append(", expiration:").append(s.getMaxDate())
                        .append("}\n");
            }
        }

        // 4. Bloque de Productos Incluidos
        double subTotalPrice = 0.0;
        if (!products.isEmpty()) {
            sb.append("Product Included\n");
            for (Product p : products) {
                double itemPrice = 0.0;

                sb.append("  {class:").append(p.getClass().getSimpleName())
                        .append(", id:").append(p.getId())
                        .append(", name:'").append(p.getName()).append("'");

                if (p instanceof Events event) { // Manejo de Lunch/Meeting
                    int participants = event.getActualParticipants();
                    itemPrice = event.getTotalPrice(participants);
                    sb.append(", price:").append(String.format(Locale.US, "%.1f", itemPrice))
                            .append(", date of Event:").append(event.getExpirationDate())
                            .append(", max people allowed:").append(event.getMaxParticipants())
                            .append(", actual people in event:").append(participants);
                } else if (p instanceof BasicProducts bp) {
                    itemPrice = bp.getTotalPrice(bp.getQuantity());
                    sb.append(", price:").append(String.format(Locale.US, "%.1f", itemPrice))
                            .append(", category:").append(bp.getCategory())
                            .append(", quantity:").append(bp.getQuantity());
                }

                sb.append("}\n");
                subTotalPrice += itemPrice;
            }

            // 5. Resumen de precios y descuentos finales
            double extraDiscountValue = subTotalPrice * extraDiscountRate;
            double finalPrice = subTotalPrice - extraDiscountValue;

            sb.append("  Total price: ").append(String.format(Locale.US, "%.1f", subTotalPrice)).append("\n");
            if (nServicios > 0) {
                sb.append("  Extra Discount from services:")
                        .append(String.format(Locale.US, "%.1f", extraDiscountValue))
                        .append(" **discount -").append(String.format(Locale.US, "%.1f", extraDiscountValue)).append("\n");
            }
            sb.append("  Total discount: ").append(String.format(Locale.US, "%.1f", extraDiscountValue)).append("\n");
            sb.append("  Final Price: ").append(String.format(Locale.US, "%.1f", finalPrice)).append("\n");
        }

        return sb.toString();
    }
}