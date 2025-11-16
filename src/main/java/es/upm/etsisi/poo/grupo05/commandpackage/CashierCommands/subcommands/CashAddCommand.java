package es.upm.etsisi.poo.grupo05.commandpackage.CashierCommands.subcommands;

import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;

public class CashAddCommand extends Command {
    private UserMap userMap;

    public CashAddCommand(String name, UserMap userMap) {
        super(name);
        this.userMap = userMap;
    }

    @Override
    public boolean apply(String[] args) { //solo nos queda los datos que necesitamos
        String line = args.toString();

        //matcher and pattern type shit




        return false;
    }
}
