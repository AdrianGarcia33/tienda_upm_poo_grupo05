package es.upm.etsisi.poo.grupo05.commandpackage.TicketCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

/**
 * Class for the ticket print command, calling the apply method will close the ticket and printout all the products in the ticket
 */
public class TicketPrintCommand extends Command {
    private UserMap userMap;
    public TicketPrintCommand(String name, UserMap userMap) {
        super(name);
        this.userMap = userMap;
    }

    @Override
    public boolean apply(String[] args) {
        try {
            String receiptId = args[0];
            User cashier = userMap.getUserMap().get(args[1]);
            if (!(cashier instanceof Cashier)) {
                System.out.println("No such cashier with this ID");
                return false;
            }
            System.out.println(cashier.getReceiptMap().print(receiptId));
            System.out.println("ticket print: ok");
            return false;
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(ExceptionHandler.getArrayIndexOutOfBoundsMessage());
        }
        return false;
    }
}
