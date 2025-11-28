package es.upm.etsisi.poo.grupo05.commandpackage.ClientCommands;

import es.upm.etsisi.poo.grupo05.commandpackage.ClientCommands.Subcommands.ClientAddCommand;
import es.upm.etsisi.poo.grupo05.commandpackage.ClientCommands.Subcommands.ClientListCommand;
import es.upm.etsisi.poo.grupo05.commandpackage.ClientCommands.Subcommands.ClientRemoveCommand;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
/**
 * Superclass which inherits from Command. It groups all the client subcommand, and enables the search and calls for a specific one. Also it
 * is meant to initialize each of them.
 */
public class ClientCommand extends Command{
    private LinkedList<Command> subcommands;

    public ClientCommand(String name) {
        super(name);
        this.subcommands = new LinkedList<>();
    }

    public void initialize(UserMap userMap) {
        ClientListCommand clientListCommand = new ClientListCommand("list", userMap);
        ClientRemoveCommand clientRemoveCommand = new ClientRemoveCommand("remove", userMap);
        ClientAddCommand clientAddCommand = new ClientAddCommand("add", userMap);

        subcommands.add(clientAddCommand);
        subcommands.add(clientListCommand);
        subcommands.add(clientRemoveCommand);
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

