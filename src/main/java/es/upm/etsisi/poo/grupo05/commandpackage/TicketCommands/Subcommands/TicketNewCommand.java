package es.upm.etsisi.poo.grupo05.commandpackage.TicketCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage.Receipt;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

public class TicketNewCommand extends Command {
    private UserMap userMap;
    public TicketNewCommand(String name, UserMap userMap) {
        super(name);
        this.userMap = userMap;
    }

    @Override
    public boolean apply(String[] args) {
        try{
            if (args.length == 2) {
                String receiptId = null;
                String cashId = args[0];
                int userId = Integer.parseInt(args[1]);
                User cashier = userMap.getUserMap().get(cashId);
                cashier = userMap.getUserMap().get(cashId);
                if (!(cashier instanceof Cashier)) {
                    System.out.println("No such cashier with this ID ");
                    return false;
                }
                cashier.getReceiptMap().newReceipt(new Receipt(null, cashId, userId));
            }
        }catch(IllegalArgumentException e){
            System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
        }








        return false;
    }
}
