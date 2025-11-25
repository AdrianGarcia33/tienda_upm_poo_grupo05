package es.upm.etsisi.poo.grupo05.commandpackage.ProdCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;

public class ProdListCommand extends Command {
    private ProductMap productMap;

    public ProdListCommand(String name, ProductMap productmap) {
        super(name);
        this.productMap = productmap;
    }


    //El metodo como tal
    @Override
    public boolean apply(String[] args) { //solo nos queda los datos que necesitamos
        System.out.print(productMap.printList());
        return false;
    }
}
