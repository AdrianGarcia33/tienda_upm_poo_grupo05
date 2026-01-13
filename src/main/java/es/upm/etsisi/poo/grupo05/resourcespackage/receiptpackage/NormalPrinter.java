package es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage;

import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class NormalPrinter<T extends Product> implements ReceiptPrinter<T> {
    @Override
    public String format(Receipt<T> receipt) {
        List<T> ticketArray = new ArrayList<>(receipt.getTicketItems());
        ticketArray.sort(Comparator.comparing(T::getName));

        StringBuilder sb = new StringBuilder();
        sb.append("Ticket : ").append(receipt.getId()).append("\n");

        double totalPrice = 0.0;
        double finalPrice = 0.0;

        for (T p : ticketArray) {
            float price = p.getBasePrice();
            if (p instanceof BasicProducts bp) {
                int quantity = bp.getQuantity();
                for (int i = 0; i < quantity; i++) {
                    sb.append(bp.toString()).append("\n");
                }
                totalPrice += (price * quantity);
                finalPrice += p.getTotalPrice(quantity);
            } else {
                sb.append("\t").append(p.toString()).append("\n");

                // Calculate price based on actual participants
                int participants = ((Events) p).getActualParticipants();
                float eventTotal = p.getTotalPrice(participants);

                totalPrice += eventTotal;
                finalPrice += eventTotal;
            }
        }
        double totalDiscount = totalPrice - finalPrice;

        sb.append("\tTotal price: " + String.format(Locale.US, "%.3f", totalPrice) + "\n");
        sb.append("\tTotal discount: " + String.format(Locale.US, "%.6f", totalDiscount) + "\n");
        sb.append("\tFinal price: " + String.format(Locale.US, "%.3f", finalPrice) + "\n");

        return sb.toString();
    }
}