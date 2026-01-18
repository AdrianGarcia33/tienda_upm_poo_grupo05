package es.upm.etsisi.poo.grupo05.commandpackage.ProdCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.ReceiptMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.Product;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

import java.util.HashMap;

/**
 * Class that responds to the prod remove command, as its name suggests, it will remove said item from both the catalog and all existing receipts / tickets
 */
public class ProdRemoveCommand extends Command {
    private ProductMap productMap;
    private UserMap userMap; //Cuando eliminamos un producto, tenemos que eliminarlo tambien de todos los tickets

    public ProdRemoveCommand(String name, ProductMap productmap, UserMap userMap) {
        super(name);
        this.productMap = productmap;
        this.userMap = userMap;
    }



    @Override
    public boolean apply(String[] args) { //solo nos queda los datos que necesitamos
        try{
            int id = Integer.parseInt(args[0]);
        if (!productMap.hasProduct(id)) {
            System.out.println(ExceptionHandler.getIdOfProductsNotExists());
        } else {
            Product product = productMap.getProduct(id);
            System.out.println(product.toString());
            productMap.removeProduct(id);
            HashMap<String, User> userHashMap = userMap.getUserMap();
            for (User user : userHashMap.values()) {
                ReceiptMap receiptMap = user.getReceiptMap();
                receiptMap.removeItemsFromAllReceipts(id);
            }
            System.out.println("prod remove: ok");
        }
    }catch(ArrayIndexOutOfBoundsException e){
            System.out.println(ExceptionHandler.getArrayIndexOutOfBoundsMessage());
        }
        return false;
    }
}
