package es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage;

import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EnterprisePrinter<T extends TicketElement> implements ReceiptPrinter<T> {
    @Override
    public String format(Receipt<T> receipt) {
        StringBuilder sb = new StringBuilder();

        List<ProductService> services = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        // 1. Separar los elementos por tipo
        for (T item : receipt.getTicketItems()) {
            if (item instanceof ProductService) {
                services.add((ProductService) item);
            } else if (item instanceof Product) {
                products.add((Product) item);
            }
        }

        int nServicios = services.size();
        double extraDiscountRate = nServicios * 0.15; // 15% por cada servicio

        // 2. Encabezado principal
        sb.append("Ticket : ").append(receipt.getId()).append("\n");

        // 3. Bloque de Servicios (si existen)
        if (!services.isEmpty()) {
            sb.append("Services Included: \n");
            for (ProductService s : services) {
                sb.append("  ").append(s.toString()).append("\n");
            }
        }

        // 4. Bloque de Productos (siguiendo la lógica de NormalPrinter)
        double subTotalPrice = 0.0;
        if (!products.isEmpty()) {
            sb.append("Product Included\n");
            for (Product p : products) {
                float itemTotal = 0.0f;

                if (p instanceof BasicProducts bp) {
                    int quantity = bp.getQuantity();
                    itemTotal = bp.getTotalPrice(quantity);
                    // Imprimimos el toString del producto básico
                    sb.append("  ").append(bp.toString()).append("\n");
                } else if (p instanceof Events event) {
                    int participants = event.getActualParticipants();
                    itemTotal = p.getTotalPrice(participants);
                    // Imprimimos el toString del evento (Meeting, etc)
                    sb.append("  ").append(p.toString()).append("\n");
                } else {
                    itemTotal = p.getTotalPrice(1);
                    sb.append("  ").append(p.toString()).append("\n");
                }

                subTotalPrice += itemTotal;
            }

            // 5. Cálculos de descuento Enterprise
            double extraDiscountValue = subTotalPrice * extraDiscountRate;
            double finalPrice = subTotalPrice - extraDiscountValue;

            // 6. Resumen final con formato %.1f
            sb.append("  Total price: ").append(String.format(Locale.US, "%.1f", subTotalPrice)).append("\n");

            if (nServicios > 0) {
                sb.append("  Extra Discount from services:")
                        .append(String.format(Locale.US, "%.1f", extraDiscountValue))
                        .append(" **discount -")
                        .append(String.format(Locale.US, "%.1f", extraDiscountValue))
                        .append("\n");
            }

            sb.append("  Total discount: ").append(String.format(Locale.US, "%.1f", extraDiscountValue)).append("\n");
            sb.append("  Final Price: ").append(String.format(Locale.US, "%.1f", finalPrice)).append("\n");
        }

        return sb.toString();
    }
}