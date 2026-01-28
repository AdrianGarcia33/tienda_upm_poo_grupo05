package es.upm.etsisi.poo.grupo05.commandpackage.CashierCommands.subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;
/**
 * Class made for cash add command, it detects the correct arguments and adds it to the userMap
 */
public class CashAddCommand extends Command {
    private UserMap userMap;

    public CashAddCommand(String name, UserMap userMap) {
        super(name);
        this.userMap = userMap;
    }

    @Override
    public boolean apply(String[] args){ //solo nos queda los datos que necesitamos
        String line = String.join(" ", args).trim();
        args= line.split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        String id, name,email;
        try {
            if (args.length == 3){
                id = args[0];
                name = args[1].replace("\"", "");
                email = args[2];
                if (cashIdAcceptable(id) && cashEmailAcceptable(email)){
                    Cashier cashier = new Cashier(id, name, email);
                    userMap.addUser(cashier);
                    System.out.println(cashier);
                    System.out.println("cash add: ok\n");
                }else {
                    if (!cashIdAcceptable(id)) System.out.println("ERROR: ID (" + id + ") is not acceptable");
                    if (!cashEmailAcceptable(email)) System.out.println("EEROR: email (" + email + ") is not acceptable");
                }
            }else if (args.length == 2) {
                id = generateId();
                name = args[0].replace("\"", "");
                email = args[1];
                if (cashEmailAcceptable(email)){
                    Cashier cashier = new Cashier(id, name, email);
                    userMap.addUser(cashier);
                    System.out.println(cashier);
                    System.out.println("cash add: ok\n");
                }else System.out.println(ExceptionHandler.getEmailUnacceptable());
            }else throw new ArrayIndexOutOfBoundsException();
        } catch (IllegalArgumentException e) {
            System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
        } catch (NullPointerException e) {
            System.out.println(ExceptionHandler.getNullPointerExceptionMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ExceptionHandler.getArrayIndexOutOfBoundsMessage());
        }
        return false;
    }
    /**
     * Auxiliary method that generates a unique cashier ID.
     *
     * @return a new valid ID
     */

    private String generateId() {
        // This method must be called when no cashId is provided when called the command "cash add"
        //if id==null llamamos a este metodo, llamamos hsta que el id random no esté en el hashmap
        StringBuilder id= new StringBuilder();
        boolean contiene = true;
        while(contiene) {
            StringBuilder aux = new StringBuilder("UW");
            for (int i = 0; i < 7; i++) {
                aux.append((int) (Math.random()*10));
            }
            if(!userMap.getUserMap().containsKey(aux.toString())){
                id=aux;
                contiene = false;
            }
        }

        return String.valueOf(id);
    }
    /**
     * Auxiliary method that checks whether the given ID is valid.
     *
     * @param id the ID to verify
     */
    private boolean cashIdAcceptable(String id){
        if(id.length()==9 && !userMap.getUserMap().containsKey(id) && id.startsWith("UW")){

            for (int i = 2; i< id.length();i++ ) {
                if (!Character.isDigit(id.charAt(i))){
                    return false;}
            }
            return true;
        }else{
            return false;
        }
    }
    /**
     * Auxiliary method to check whether the given email is valid.
     *
     * @param email the email to verify
     */
    private boolean cashEmailAcceptable(String email){
        if(email.endsWith("@upm.es")){
            return true;
        }else{
            return false;
        }
    }
}
