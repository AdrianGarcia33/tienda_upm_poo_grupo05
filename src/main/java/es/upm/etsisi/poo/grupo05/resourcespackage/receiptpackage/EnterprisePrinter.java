package es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage;

import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.*;
import java.util.Locale;

public class EnterprisePrinter<T extends TicketElement> implements ReceiptPrinter<T> {
    @Override
    public String format(Receipt<T> receipt) {
        StringBuilder sb = new StringBuilder();
        sb.append("FACTURA EMPRESA - NIF: ").append(receipt.getClientId()).append("\n");
        sb.append("TICKET: ").append(receipt.getId()).append("\n");
        sb.append("------------------------------------------\n");

        // 1. Contamos servicios para calcular el plus de descuento
        long nServicios = receipt.getTicketItems().stream()
                .filter(i -> i instanceof ProductService)
                .count();

        // Plus de descuento: 15% por cada servicio
        double plusDescuento = nServicios * 0.15;

        double finalPrice = 0;

        for (T item : receipt.getTicketItems()) {
            if (item instanceof ProductService service) {
                // REGLA: Los servicios no aparecen con precio
                sb.append("[SERVICIO] ").append(service.getName()).append(" (ID: ").append(service.getId()).append("S)\n");
            } else if (item instanceof Product p) {
                // REGLA: Aplicar plus de descuento a productos
                double precioConPlus = p.getBasePrice() * (1 - plusDescuento);
                sb.append(p.getName()).append(" ... ").append(String.format(Locale.US, "%.2f", precioConPlus)).append("€\n");
                finalPrice += precioConPlus;
            }
        }

        sb.append("------------------------------------------\n");
        sb.append("TOTAL A PAGAR (Productos): ").append(String.format(Locale.US, "%.2f", finalPrice)).append("€\n");
        sb.append("(Servicios facturados a posteriori)\n");

        return sb.toString();
    }
}