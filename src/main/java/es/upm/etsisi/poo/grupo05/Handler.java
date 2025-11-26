package es.upm.etsisi.poo.grupo05;


import es.upm.etsisi.poo.grupo05.commandpackage.CashierCommands.CashCommand;
import es.upm.etsisi.poo.grupo05.commandpackage.ClientCommands.ClientCommand;
import es.upm.etsisi.poo.grupo05.commandpackage.ClientCommands.Subcommands.ClientAddCommand;
import es.upm.etsisi.poo.grupo05.commandpackage.EchoCommand;
import es.upm.etsisi.poo.grupo05.commandpackage.ExitCommand;
import es.upm.etsisi.poo.grupo05.commandpackage.HelpCommand;
import es.upm.etsisi.poo.grupo05.commandpackage.TicketCommands.TicketCommand;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.commandpackage.ProdCommands.ProdCommand;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Handler {
    private ProductMap productmap;
    private UserMap usermap;
    private Scanner scanner;
    private LinkedList<Command> commands;

    public Handler(int max_products) {
        this.productmap = new ProductMap(max_products);
        this.usermap = new UserMap();
        this.commands = new LinkedList<>();
    }

    public void initialize() {
        //Creamos las clase con el nombre correspondiente
        ProdCommand prodCommand = new ProdCommand("prod");
        TicketCommand ticketCommand = new TicketCommand("ticket");
        ClientCommand clientCommand = new ClientCommand("client");
        CashCommand cashCommand = new CashCommand("cash");
        EchoCommand echoCommand = new EchoCommand("echo");
        HelpCommand helpCommand = new HelpCommand("help");
        ExitCommand exitCommand = new ExitCommand("exit");

        //Inializamos los commandos que necesiten algun map
        prodCommand.initialize(this.productmap, this.usermap);
        ticketCommand.initialize(this.usermap);
        clientCommand.initialize(this.usermap);
        cashCommand.initialize(this.usermap);

        //Añadimos a la lista de commandos
        commands.add(prodCommand);
        commands.add(ticketCommand);
        commands.add(clientCommand);
        commands.add(cashCommand);
        commands.add(echoCommand);
        commands.add(helpCommand);
        commands.add(exitCommand);

    }


    public void start (String[] args) { //En el main de app, llamamos a este metodo metiendo el String[] args de main
        try {
            boolean imprimir_comando = false;
            if (args.length > 0) {
                scanner = new Scanner(new File(args[0]));
                imprimir_comando = true;
            } else {
                scanner = new Scanner(System.in);
            }

            System.out.println("Welcome to the ticket module App");
            System.out.println("Ticket module. Type 'help' to see commands.");
            boolean stop = false;

            while (!stop) {
                System.out.print("tUPM> ");
                String line = scanner.nextLine();
                if (imprimir_comando) {
                    System.out.println(line);
                }
                String[] prompt = line.split(" ");

                stop = startCommand(prompt);
            }

            System.out.println("Closing application.");
            System.out.println("Goodbye!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private boolean startCommand(String[] prompt) {
        Iterator iterator = commands.iterator();
        boolean stop = false;
        String part = prompt[0];

        while (iterator.hasNext() && !stop) {
             //Part that could be prod, ticket, user etc.
            Command command = (Command) iterator.next(); //Downcast

            if (command.getName().equals(part) && !stop) {

                String[] sentprompt = Arrays.copyOfRange(prompt, 1, prompt.length);
                //Como ya hemos detectado el prod,
                // ticket etc. no hace falta que lo mandemos al siguiente paso de deteccion
                stop = command.apply(sentprompt);
                //Si detecta que el atributo de la subclase a la que esta llamando (ProdCommand, Receipt Command etc
                // corresponde entoces llama el apply de tal clase;
                //exit es el unico que cambiara stop a true entonces saldremos de este bucle
            }

        }

        return stop;
    }


}
