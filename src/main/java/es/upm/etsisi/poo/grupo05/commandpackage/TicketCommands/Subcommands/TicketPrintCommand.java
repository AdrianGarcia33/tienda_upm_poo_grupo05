package es.upm.etsisi.poo.grupo05.commandpackage.TicketCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

public class TicketPrintCommand extends Command {
    private UserMap userMap;
    public TicketPrintCommand(String name, UserMap userMap) {
        super(name);
        this.userMap = userMap;
    }

    @Override
    public boolean apply(String[] args) {
        String receiptId = args[0];
        User cashier = userMap.getUserMap().get(args[1]);
        if (!(cashier instanceof Cashier)) {
            System.out.println("No such cashier with this ID");
            return false;
        }
        cashier.getReceiptMap().print(receiptId);
        return false;
    }
}
