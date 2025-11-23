package es.upm.etsisi.poo.grupo05.commandpackage.ClientCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;

public class ClientRemoveCommand extends Command {
    private UserMap userMap;
    public ClientRemoveCommand (String name, UserMap userMap) {
        super(name);
        this.userMap = userMap;
    }

    @Override
    public boolean apply(String[] args) { //solo nos queda los datos que necesitamos
        String line = args.toString();
        try {
            String DNI = args[0];
            userMap.removeUser(userMap.getUserMap().get(DNI));
        }catch(Exception e){
            System.out.println();
        }
        return false;
    }
}
