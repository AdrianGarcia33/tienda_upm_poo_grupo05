package es.upm.etsisi.poo.grupo05.commandpackage.ClientCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
/**
 * Class made for client list command, calling the apply method will printout the clients on the userMap
 */
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
        } catch (IllegalArgumentException e) {
            System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
        } catch (NullPointerException e) {
            System.out.println(ExceptionHandler.getNullPointerExceptionMessage());
        }
        return false;
    }
}
