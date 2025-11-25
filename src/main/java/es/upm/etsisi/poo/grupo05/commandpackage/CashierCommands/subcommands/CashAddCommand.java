package es.upm.etsisi.poo.grupo05.commandpackage.CashierCommands.subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.Category;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.Product;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;

public class CashAddCommand extends Command {
    private UserMap userMap;

    public CashAddCommand(String name, UserMap userMap) {
        super(name);
        this.userMap = userMap;
    }

    @Override
    public boolean apply(String[] args) { //solo nos queda los datos que necesitamos
        String line = String.join(" ", args).trim();
        args= line.split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        String id, name,email;
        try {
            if (args.length == 3) {
                id = args[0];
                name = args[1];
                email = args[2];
                if (cashIdAcceptable(id) && cashEmailAcceptable(email)){
                    userMap.addUser(new Cashier(id, name, email));
                    System.out.println("Cashier ("+ id+ ") has been added");}
            }else if (args.length == 2) {
                id = generateId();
                name = args[0];
                email = args[1];
                if (cashEmailAcceptable(email)){
                    userMap.addUser(new Cashier(id, name, email));
                    System.out.println("Cashier ("+ id+ ") has been added");}
                else System.out.println(ExceptionHandler.getEmailUnacceptable());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
        } catch (NullPointerException e) {
            System.out.println(ExceptionHandler.getNullPointerExceptionMessage());
        }
        return false;
    }
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
    private boolean cashIdAcceptable(String id){
        // TENGO QUE HACER QUE CONTROLE QUE SEAN 7 NUMEROS Y NO LETRAS
        if(id.length()==9 && !userMap.getUserMap().containsKey(id) && id.startsWith("UW")){
            return true;
        }else{
            System.out.println("Cashier("+id+") alerady added");
            return false;
        }
    }
    private boolean cashEmailAcceptable(String email){
        if(email.endsWith("@upm.es")){
            return true;
        }else{
            System.out.println("the email does not end with \"@upm.es\"");
            return false;
        }
    }
}
