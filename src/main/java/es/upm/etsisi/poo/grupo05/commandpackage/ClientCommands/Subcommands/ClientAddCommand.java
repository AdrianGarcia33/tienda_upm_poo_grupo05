package es.upm.etsisi.poo.grupo05.commandpackage.ClientCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;

public class ClientAddCommand extends Command {
    private UserMap userMap;

    public ClientAddCommand (String name, UserMap userMap) {
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
