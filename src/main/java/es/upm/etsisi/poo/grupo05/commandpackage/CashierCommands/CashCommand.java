package es.upm.etsisi.poo.grupo05.commandpackage.CashierCommands;

import es.upm.etsisi.poo.grupo05.commandpackage.CashierCommands.subcommands.CashAddCommand;
import es.upm.etsisi.poo.grupo05.commandpackage.CashierCommands.subcommands.CashListCommand;
import es.upm.etsisi.poo.grupo05.commandpackage.CashierCommands.subcommands.CashRemoveCommand;
import es.upm.etsisi.poo.grupo05.commandpackage.CashierCommands.subcommands.CashTicketsCommand;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class CashCommand extends Command {
    private LinkedList<Command> subcommands;

    public CashCommand(String name) {
        super(name);
        this.subcommands = new LinkedList<>();
    }

    public void initialize(UserMap userMap) {
        CashAddCommand cashAddCommand = new CashAddCommand("add", userMap);
        CashTicketsCommand cashTicketsCommand = new CashTicketsCommand("tickets", userMap);
        CashListCommand cashListCommand = new CashListCommand("list", userMap);
        CashRemoveCommand cashRemoveCommand = new CashRemoveCommand("remove", userMap);

        subcommands.add(cashAddCommand);
        subcommands.add(cashTicketsCommand);
        subcommands.add(cashListCommand);
        subcommands.add(cashRemoveCommand);
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
