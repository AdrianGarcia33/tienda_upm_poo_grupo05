package es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage;

import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.TicketElement;

/**
 * Interfaz que define el comportamiento para la impresión de tickets.
 * Cumple con el requisito de "clase de manipulación de comportamiento".
 *
 * @param <T> Tipo de elementos que contiene el ticket (Product o TicketElement).
 */
public interface ReceiptPrinter<T extends TicketElement> {

    /**
     * Transforma la información del ticket en una cadena de texto formateada.
     * * @param receipt El ticket que se desea imprimir.
     * @return El ticket formateado como String.
     */
    String format(Receipt<T> receipt);
}
