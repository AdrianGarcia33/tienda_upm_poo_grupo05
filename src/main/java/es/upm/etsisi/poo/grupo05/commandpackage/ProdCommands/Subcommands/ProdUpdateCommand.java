package es.upm.etsisi.poo.grupo05.commandpackage.ProdCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.BasicProducts;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.Category;

/**
 * Class which function is to update certain values of a product. It responds to the prod update command line.
 */
public class ProdUpdateCommand extends Command {
    private ProductMap productMap;

    public ProdUpdateCommand(String name, ProductMap productmap) {
        super(name);
        this.productMap = productmap;
    }

    //El metodo como tal
    @Override
    public boolean apply(String[] args) { //solo nos queda los datos que necesitamos
        try {
            int id = Integer.parseInt(args[0]);
            String type = args[1];
            String value = String.join(" ", java.util.Arrays.copyOfRange(args, 2, args.length));
            value = value.replaceAll("\"", "");

            BasicProducts p = (BasicProducts) productMap.getProduct(id);

            switch (type.toUpperCase()) {
                case "NAME":
                    productMap.updateProduct(id, value, p.getBasePrice(), null);
                    break;
                case "PRICE":
                    productMap.updateProduct(id, null, Float.parseFloat(value), null);
                    break;
                case "CATEGORY":
                    productMap.updateProduct(id, null, p.getBasePrice(), Category.valueOf(value));
                    break;
            }

            System.out.println(p.toString());
            System.out.println("prod update: ok");

        }catch(ArrayIndexOutOfBoundsException ex){
            System.out.println(ExceptionHandler.getArrayIndexOutOfBoundsMessage());
        }catch (ClassCastException e) {
            System.out.println(ExceptionHandler.getClassCastExceptionMessage());
        }
        return false;
    }
}
