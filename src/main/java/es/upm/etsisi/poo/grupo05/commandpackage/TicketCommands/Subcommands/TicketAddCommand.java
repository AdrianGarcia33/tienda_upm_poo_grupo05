package es.upm.etsisi.poo.grupo05.commandpackage.TicketCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketAddCommand extends Command {
    private UserMap userMap;

    public TicketAddCommand(String name, UserMap userMap) {
        super(name);
        this.userMap = userMap;
    }

    @Override
    public boolean apply(String[] args) {
        try{
            String line = String.join(" ", args).trim();
            line = line.replaceAll("\"{2,}", "\"");

            Pattern p = Pattern.compile("^(\\d+)\\s+\"([^\"]+)\"\\s+(\\S+)\\s+(\\d+(?:\\.\\d+)?)\\s*(?:\\[(\\d+)\\])?$");
            Matcher m = p.matcher(line);

            if (!m.find()) {
                return false; // formato no reconocido
            }
            String idStr = m.group(1);
            String cashIdStr = m.group(2);
            String prodIdStr = m.group(3);
            String quantityStr = m.group(4);
            String personalizedStr = m.group(5); //Este es opcional

            int id = -1;
            User cashier = null;
            int prodId = -1;
            int quantity = -1;

            if (idStr != null) {
                id = Integer.parseInt(idStr);
            }
            if (cashIdStr != null) {
                cashier = userMap.getUserMap().get(cashIdStr);
                if (!(cashier instanceof Cashier)) {
                    System.out.println("No such cashier with ID: " + cashIdStr);
                    return false;
                }
            }
            if (prodIdStr != null) {
                prodId = Integer.parseInt(prodIdStr);
            }
            if (quantityStr != null) {
                quantity = Integer.parseInt(quantityStr);
            }


        }catch(IllegalArgumentException ex){
            System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
        }








        return false;
    }
}
