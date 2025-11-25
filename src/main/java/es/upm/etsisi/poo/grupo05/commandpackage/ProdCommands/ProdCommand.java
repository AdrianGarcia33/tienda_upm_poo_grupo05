package es.upm.etsisi.poo.grupo05.commandpackage.ProdCommands;

import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.commandpackage.ProdCommands.Subcommands.*;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class ProdCommand extends Command {
    private LinkedList<Command> subcommands;

    public ProdCommand (String name) {
        super(name);
        this.subcommands = new LinkedList<>();
    }

    public void initialize(ProductMap productmap, UserMap userMap) {
        ProdAddCommand prodAdd = new ProdAddCommand("add", productmap);
        ProdAddFoodCommand prodAddFood = new ProdAddFoodCommand("addFood", productmap);
        ProdAddMeetingCommand prodAddMeeting = new ProdAddMeetingCommand("addMeeting", productmap);
        ProdListCommand prodList = new ProdListCommand("list", productmap);
        ProdRemoveCommand prodRemove = new ProdRemoveCommand("remove", productmap, userMap);
        ProdUpdateCommand prodUpdate = new ProdUpdateCommand("update", productmap);

        subcommands.add(prodAdd);
        subcommands.add(prodAddFood);
        subcommands.add(prodAddMeeting);
        subcommands.add(prodList);
        subcommands.add(prodRemove);
        subcommands.add(prodUpdate);
    }


    @Override
    public boolean apply(String[] prompt) {
        Iterator<Command> iterator = subcommands.iterator();
        String part = prompt[0];

        while (iterator.hasNext()) {
            Command subcommand = (Command) iterator.next();
            if (subcommand.getName().equals(part)) {
                // Encuentra el comando, eso si aunque encuentra el comando este bucle va a seguir hasta el final,
                // solamente que no encontrará mas recurrencias (por ahora). Si en un futuro existiera dos prod add diferentes creo que habria un problema,
                // aunque posiblemente se filtrase en en un nivel mas bajo  como si fueran dos subcomandos de prodadd (POR AHORA NO HAY PROBLEMA)

                String[] sentprompt = Arrays.copyOfRange(prompt, 1, prompt.length);
                subcommand.apply(sentprompt);
            }
        }

        return false;
    }
}
