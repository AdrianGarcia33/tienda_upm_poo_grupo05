package es.upm.etsisi.poo.grupo05.commandpackage.ClientCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
/**
 * Class that responds to the client remove command,if the ID provided is on the userMap it will remove said client from the userMap
 */
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
            if(userMap.removeUser(userMap.getUserMap().get(DNI))) System.out.println("client remove: ok\n");
        } catch (IllegalArgumentException e) {
            System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
        } catch (NullPointerException e) {
            System.out.println(ExceptionHandler.getNullPointerExceptionMessage());
        }
        return false;
    }
}
