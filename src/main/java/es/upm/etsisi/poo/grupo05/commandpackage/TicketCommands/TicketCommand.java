package es.upm.etsisi.poo.grupo05.commandpackage.TicketCommands;

import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.commandpackage.TicketCommands.Subcommands.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;


public class TicketCommand extends Command {
    private LinkedList<Command> subcommands;

    public TicketCommand (String name) {
        super(name);
        this.subcommands = new LinkedList<>();
    }

    public void initialize(ProductMap productMap, UserMap userMap) {
        TicketNewCommand ticketNewCommand = new TicketNewCommand("new", userMap, productMap);
        TicketAddCommand ticketAddCommand = new TicketAddCommand("add", userMap, productMap);
        TicketListCommand ticketListCommand = new TicketListCommand("list", userMap);
        TicketPrintCommand ticketPrintCommand = new TicketPrintCommand("print", userMap);
        TicketRemoveCommand ticketRemoveCommand = new TicketRemoveCommand("remove", userMap);

        subcommands.add(ticketNewCommand);
        subcommands.add(ticketAddCommand);
        subcommands.add(ticketListCommand);
        subcommands.add(ticketPrintCommand);
        subcommands.add(ticketRemoveCommand);
    }


    @Override
    public boolean apply(String[] prompt) {
        Iterator<Command> iterator = subcommands.iterator();
        String part = prompt[0];

        while (iterator.hasNext()) {
            Command subcommand = (Command) iterator.next();
            if (subcommand.getName().equals(part)) {

                String[] sentprompt = Arrays.copyOfRange(prompt, 1, prompt.length);
                subcommand.apply(sentprompt);
            }
        }

        return false;
    }
}
