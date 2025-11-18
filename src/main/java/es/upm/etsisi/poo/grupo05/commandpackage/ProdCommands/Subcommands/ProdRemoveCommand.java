package es.upm.etsisi.poo.grupo05.commandpackage.ProdCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.ReceiptMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

import java.util.HashMap;

public class ProdRemoveCommand extends Command {
    private ProductMap productMap;
    private UserMap userMap; //Cuando eliminamos un producto, tenemos que eliminarlo tambien de todos los tickets

    public ProdRemoveCommand(String name, ProductMap productmap, UserMap userMap) {
        super(name);
        this.productMap = productmap;
        this.userMap = userMap;
    }




    //El metodo como tal
    @Override
    public boolean apply(String[] args) { //solo nos queda los datos que necesitamos
        int id = Integer.parseInt(args[0]);
        //No he hecho un seguimiento de excepciones
        if (!productMap.hasProduct(id)) {
            System.out.println(ExceptionHandler.getIdOfProductsNotExists());
        } else {
            productMap.removeProduct(id);
            HashMap<String, User> userHashMap = userMap.getUserMap();
            for (User user : userHashMap.values()) {
                ReceiptMap receiptMap = user.getReceiptMap();
                receiptMap.removeItemsFromAllReceipts(id);
            }
        }
        return false;
    }
}
