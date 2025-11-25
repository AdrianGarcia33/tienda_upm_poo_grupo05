package es.upm.etsisi.poo.grupo05.commandpackage.ProdCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.BasicProducts;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.Category;

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
            String value = args[2];

            BasicProducts p = (BasicProducts) productMap.getProduct(id);

            switch (value) {
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

        } catch (ClassCastException e) {
            System.out.println(ExceptionHandler.getClassCastExceptionMessage());
        }
        return false;
    }
}
