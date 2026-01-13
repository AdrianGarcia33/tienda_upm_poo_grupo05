package es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage;

import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.*;

import java.util.ArrayList;
import java.util.Locale;
import java.util.List;
import java.util.stream.Collectors;

public class EnterprisePrinter<T extends TicketElement> implements ReceiptPrinter<T> {
    @Override
    public String format(Receipt<T> receipt) {
        StringBuilder sb = new StringBuilder();
        List<T> items = receipt.getTicketItems(); //

        // 1. Filtrar servicios y productos para el conteo de descuento
        List<ProductService> services = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        // 2. Repartimos los items del ticket en las listas (Sustituye a los Streams)
        for (T item : receipt.getTicketItems()) { //
            if (item instanceof ProductService) {
                services.add((ProductService) item); //
            } else if (item instanceof Product) {
                products.add((Product) item); //
            }
        }

        int nServicios = services.size();
        double extraDiscountRate = nServicios * 0.15; // 15% por cada servicio

        // 2. Encabezado principal
        sb.append("Ticket : ").append(receipt.getId()).append("\n");

        // 3. Bloque de Servicios Incluidos
        if (!services.isEmpty()) {
            sb.append("Services Included: \n");
            for (ProductService s : services) {
                sb.append(s.toString()+"\n");

            }
        }

        // 4. Bloque de Productos Incluidos
        double subTotalPrice = 0.0;
        if (!products.isEmpty()) {
            sb.append("Product Included\n");
            for (Product p : products) {
                double itemPrice = 0.0;

                sb.append( p.toString()+ "\n");
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