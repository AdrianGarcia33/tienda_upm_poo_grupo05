package es.upm.etsisi.poo.grupo05.commandpackage.ClientCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Client;
/**
 * Class made for client add command, it detects the correct arguments and adds it to the userMap
 */
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
        String name = args[0].replace("\"", ""),DNI = args[1],email = args[2];
        Cashier cashier=null;
        if(userMap.getUserMap().containsKey(args[3])){
            cashier=(Cashier) userMap.getUserMap().get(args[3]);
        }
        if(DNIAcceptable(DNI) && cashier!= null){
            Client client= new Client(DNI, name, email, cashier);
            userMap.addUser(client);
            System.out.println(client);
            System.out.println("client add: ok\n");
        }
    } catch (IllegalArgumentException e) {
        System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
    } catch (NullPointerException e) {
        System.out.println(ExceptionHandler.getNullPointerExceptionMessage());
    } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println(ExceptionHandler.getArrayIndexOutOfBoundsMessage());
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
                return NIFAcceptable(DNI);
            }
        }
    }
    private boolean NIFAcceptable(String nif) {
        if (nif == null || nif.length() != 9) {
            System.out.println("ERROR: Client ("+ nif+") is not valid");
            return false;
        }
        if (!Character.isLetter(nif.charAt(0))) {
            System.out.println("ERROR: Client ("+ nif+") is not valid");
            return false;
        }
        for (int i = 1; i < nif.length(); i++) {
            char c = nif.charAt(i);
            if (!Character.isDigit(c)) {
                System.out.println("ERROR: Client ("+ nif+") is not valid");
                return false;
            }
        }
        return true;
    }

}
