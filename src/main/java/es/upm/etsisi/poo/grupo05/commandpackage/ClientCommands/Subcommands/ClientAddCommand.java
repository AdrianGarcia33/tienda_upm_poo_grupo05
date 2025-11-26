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
        String line = String.join(" ", args).trim();
        args= line.split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    try{
        String name = args[0],DNI = args[1],email = args[2];
        Cashier cashier=null;
        if(userMap.getUserMap().containsKey(args[3])){
            cashier=(Cashier) userMap.getUserMap().get(args[3]);
        }
        if(DNIAcceptable(DNI) && cashier!= null) {
            Client client= new Client(DNI, name, email, cashier);
            userMap.addUser(client);
            System.out.println(client);
            System.out.println("client add: ok\n");
        }
    } catch (IllegalArgumentException e) {
        System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
    } catch (NullPointerException e) {
        System.out.println(ExceptionHandler.getNullPointerExceptionMessage());
    }

        return false;
    }
    private boolean DNIAcceptable(String DNI){
        if(userMap.getUserMap().containsKey(DNI)){
            System.out.println("ERROR: Client ("+ DNI+") is already added");
            return false;
        }else{
            if(DNI.length()==9 && DNI.matches(".*[a-zA-Z]$"))return true;
            else{
                System.out.println("ERROR: Client ("+ DNI+") is not valid");
                return false;
            }
        }
    }
}
