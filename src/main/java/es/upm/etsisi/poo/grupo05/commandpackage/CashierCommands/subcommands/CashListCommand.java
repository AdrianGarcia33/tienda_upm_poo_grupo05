package es.upm.etsisi.poo.grupo05.commandpackage.CashierCommands.subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
/**
 * Class made for cash list command, calling the apply method will printout the cashiers on the userMap
 */
public class CashListCommand extends Command {
    private UserMap userMap;
    /**
     * Constructor of de command that that prints every cahier on the userMap
     *
     * @param name    the name of the command
     * @param userMap the map containing user information
     */

    public CashListCommand(String name, UserMap userMap) {
        super(name);
        this.userMap = userMap;
    }

    @Override
    public boolean apply(String[] args) { //solo nos queda los datos que necesitamos
        String line = args.toString();
        try {
            System.out.println(userMap.UserList(false));
        } catch (IllegalArgumentException e) {
            System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
        } catch (NullPointerException e) {
            System.out.println(ExceptionHandler.getNullPointerExceptionMessage());
        }
        return false;
    }
}
