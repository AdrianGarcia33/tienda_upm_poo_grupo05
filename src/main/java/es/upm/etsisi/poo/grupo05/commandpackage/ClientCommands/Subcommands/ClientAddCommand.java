package es.upm.etsisi.poo.grupo05.commandpackage.ClientCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Client;

public class ClientAddCommand extends Command {
    private UserMap userMap;

    public ClientAddCommand (String name, UserMap userMap) {
        super(name);
        this.userMap = userMap;
    }

    @Override
    public boolean apply(String[] args) { //solo nos queda los datos que necesitamos
        String line = args.toString();
    try{
        String name = args[0],DNI = args[1],email = args[2];
        Cashier cashier=null;
        if(userMap.getUserMap().containsKey(args[5])) cashier=(Cashier) userMap.getUserMap().get(args[5]);
        if(clientEmailAcceptable(email) && cashier!= null) userMap.addUser(new Client(DNI,name,email,cashier));
    } catch (IllegalArgumentException e) {
        System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
    } catch (NullPointerException e) {
        System.out.println(ExceptionHandler.getNullPointerExceptionMessage());
    }

        return false;
    }
    private boolean clientEmailAcceptable(String email){
        if(!email.endsWith("@upm.es")){
            return true;
        }else{
            System.out.println("Incorrect data");
            return false;
        }
    }
}
