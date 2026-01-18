package es.upm.etsisi.poo.grupo05.commandpackage.CashierCommands.subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
/**
 * Class that responds to the cash remove command,if the ID provided is on the userMap it will remove said cashier from the userMap
 */
public class CashRemoveCommand extends Command {
    private UserMap userMap;

    public CashRemoveCommand(String name, UserMap userMap) {
        super(name);
        this.userMap = userMap;
    }

    @Override
    public boolean apply(String[] args) { //solo nos queda los datos que necesitamos
        String line = args.toString();
        try{
            String id = args[0];
            if(userMap.removeUser(userMap.getUserMap().get(id))) System.out.println("cash remove: ok\n");
        } catch (IllegalArgumentException e) {
            System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
        } catch (NullPointerException e) {
            System.out.println(ExceptionHandler.getNullPointerExceptionMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ExceptionHandler.getArrayIndexOutOfBoundsMessage());
        }
        return false;
    }
}
