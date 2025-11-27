package es.upm.etsisi.poo.grupo05.commandpackage.TicketCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

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
            for (User cashier : userMap.getUserMap().values()) {
               if (cashier instanceof Cashier) {
                   String result = cashier.getReceiptMap().list();
                   System.out.println(result);
               }
            }
            System.out.println("ticket new: ok\n");
        }
        return false;
    }
}
