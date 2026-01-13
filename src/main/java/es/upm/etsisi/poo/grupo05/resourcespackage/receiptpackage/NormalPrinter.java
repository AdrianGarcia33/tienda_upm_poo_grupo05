package es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage;

import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.*;
import java.util.Locale;

public class NormalPrinter<T extends TicketElement> implements ReceiptPrinter<T> {
    @Override
    public String format(Receipt<T> receipt) {
        StringBuilder sb = new StringBuilder();
        sb.append("TICKET ID: ").append(receipt.getId()).append("\n");
        sb.append("------------------------------------------\n");

        double total = 0;
        for (T item : receipt.getTicketItems()) {
            sb.append(item.toString()).append("\n");
            // En ticket normal, todos los productos suman su precio total
            if (item instanceof Product p) {
                total += p.getTotalPrice(1); // Simplificado para el ejemplo
            }
        }
        sb.append("------------------------------------------\n");
        sb.append("TOTAL: ").append(String.format(Locale.US, "%.2f", total)).append("€\n");
        return sb.toString();
    }
}