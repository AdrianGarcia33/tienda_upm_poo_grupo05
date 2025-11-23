package es.upm.etsisi.poo.grupo05.commandpackage.ClientCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;

public class ClientListCommand extends Command {
    private UserMap userMap;
    public ClientListCommand (String name, UserMap userMap) {
        super(name);
        this.userMap = userMap;
    }
    @Override
    public boolean apply(String[] args) { //solo nos queda los datos que necesitamos
        String line = args.toString();
        try {
            System.out.println(userMap.UserList(true));
            System.out.println("client list: ok");
        }catch(Exception e){}
        return false;
    }
}
