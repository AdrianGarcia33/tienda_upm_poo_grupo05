package es.upm.etsisi.poo.grupo05.commandpackage.TicketCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage.Receipt;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

public class TicketNewCommand extends Command {
    private UserMap userMap;
    private ProductMap productMap;
    public TicketNewCommand(String name, UserMap userMap, ProductMap productMap) {
        super(name);
        this.userMap = userMap;
        this.productMap = productMap;
    }

    @Override
    public boolean apply(String[] args) {
        try{
            if (args.length == 2) {
                String cashId = args[0];
                String userId = args[1];
                User cashier = userMap.getUserMap().get(cashId);
                if (!(cashier instanceof Cashier)) {
                    System.out.println("No such cashier with this ID ");
                    return false;
                }
                cashier.getReceiptMap().newReceipt(new Receipt(null, cashId, userId, productMap));
            } else  if (args.length == 3) {
                String receiptId = args[0];
                String cashId = args[1];
                String userId = args[2];
                User cashier = userMap.getUserMap().get(cashId);
                if (!(cashier instanceof Cashier)) {
                    System.out.println("No such cashier with this ID ");
                    return false;
                }
                cashier.getReceiptMap().newReceipt(new Receipt(receiptId, cashId, userId, productMap));
            }
        }catch(IllegalArgumentException e){
            System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
        }catch (NullPointerException e){
            System.out.println(ExceptionHandler.getNullArgument());
        }








        return false;
    }
}
