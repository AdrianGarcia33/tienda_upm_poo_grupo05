package es.upm.etsisi.poo.grupo05.commandpackage.TicketCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

/**
 * Class for the ticket list command, calling the apply method will printout all the tickets ordered by the cashier id
 */
public class TicketListCommand extends Command {
    private UserMap userMap;
    public TicketListCommand(String name, UserMap userMap) {
        super(name);
        this.userMap = userMap;
    }

    @Override
    public boolean apply(String[] args) {
        if (args.length != 0) {
            System.out.println(ExceptionHandler.getNullArgument());
        }else  {
            StringBuilder result = new StringBuilder();
            result.append("Ticket List:\n");
            for (User cashier : userMap.getUserMap().values()) {
               if (cashier instanceof Cashier) {
                   result.append(cashier.getReceiptMap().list());
               }
            }
            System.out.println(result);
            System.out.println("ticket list: ok\n");
        }
        return false;
    }
}
