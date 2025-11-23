package es.upm.etsisi.poo.grupo05.commandpackage.CashierCommands.subcommands;

import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;

public class CashTicketsCommand extends Command {
    private UserMap userMap;

    public CashTicketsCommand(String name, UserMap userMap) {
        super(name);
        this.userMap = userMap;
    }

    @Override
    public boolean apply(String[] args) { //solo nos queda los datos que necesitamos
        String line = args.toString();
    try{
        String id=args[0];
        Cashier cashier =(Cashier) userMap.getUserMap().get(id);
        System.out.println(cashier.tickets());
    }catch(Exception e){}
        return false;
    }
}
