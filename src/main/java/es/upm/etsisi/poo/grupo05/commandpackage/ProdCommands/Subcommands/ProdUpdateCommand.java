package es.upm.etsisi.poo.grupo05.commandpackage.ProdCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;

public class ProdUpdateCommand extends Command {
    private ProductMap productMap;

    public ProdUpdateCommand(String name, ProductMap productmap) {
        super(name);
        this.productMap = productmap;
    }

    //El metodo como tal
    @Override
    public boolean apply(String[] args) { //solo nos queda los datos que necesitamos
        String line = args.toString();

        //matcher and pattern type shit




        return false;
    }
}
