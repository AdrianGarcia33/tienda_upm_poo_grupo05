package es.upm.etsisi.poo.grupo05.commandpackage.TicketCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.ReceiptMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage.Receipt;
import es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage.TicketState;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for the ticket remove command, it will remove a product from the ticket
 */
public class TicketRemoveCommand extends Command {
    private UserMap userMap;
    private ProductMap productMap;
    public TicketRemoveCommand(String name, UserMap userMap, ProductMap productMap) {
        super(name);
        this.userMap = userMap;
    }

    @Override
    public boolean apply(String[] args) {
        try {
            String line = String.join(" ", args).trim();

            Pattern p = Pattern.compile("^(\\S+)\\s+(\\S+)\\s+(\\d+)$");
            Matcher m = p.matcher(line);

            if (!m.find()) {
                return false; // Formato no reconocido
            }

            String ticketId = m.group(1);
            String cashId = m.group(2);
            int prodId = Integer.parseInt(m.group(3));

            if (!userMap.getUserMap().containsKey(cashId)) {
                System.out.println(ExceptionHandler.getNotInstanceOfCashierMessage());
                return false;
            }

            User user = userMap.getUserMap().get(cashId);
            if (!(user instanceof Cashier)) {
                System.out.println(ExceptionHandler.getNotInstanceOfCashierMessage());
                return false;
            }

            Cashier cashier = (Cashier) user;

            if (cashier.getReceiptMap().contains(ticketId)) {
                Receipt receipt = cashier.getReceiptMap().getReceiptmap().get(ticketId);

                if (receipt.removeItem(prodId)) {
                    System.out.println(cashier.getReceiptMap().getProvisionalString(ticketId));
                    System.out.println("ticket remove: ok");
                } else {
                    System.out.println(ExceptionHandler.getProductNotFound());
                }

            } else {
                System.out.println(ExceptionHandler.getTicketNotFromCashier());
            }

        } catch (NumberFormatException e) {
            System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
        } catch (Exception e) {
            System.out.println(ExceptionHandler.getNullPointerExceptionMessage());
        }

        return false;
    }
}

