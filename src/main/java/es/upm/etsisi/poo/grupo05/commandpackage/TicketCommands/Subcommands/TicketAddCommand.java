package es.upm.etsisi.poo.grupo05.commandpackage.TicketCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.PersonalizedProducts;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static es.upm.etsisi.poo.grupo05.ExceptionHandler.getInputMismatchExceptionMessage;

/**
 * Class made for ticket add command, it will add the specified product to the ticket
 */

public class TicketAddCommand extends Command {
    private UserMap userMap;
    private ProductMap productMap;

    public TicketAddCommand(String name, UserMap userMap, ProductMap productMap) {
        super(name);
        this.userMap = userMap;
        this.productMap = productMap;
    }

    @Override
    public boolean apply(String[] args) {
        String line = String.join(" ", args).trim();
        args = line.split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        try {

            String receiptId = args[0];
            String cashierId = args[1];
            String productIdString = args[2];
            int amount = 1;
            if (args.length >= 4) amount = Integer.parseInt(args[3]);
            User cashier = userMap.getUserMap().get(cashierId);
            if (!(cashier instanceof Cashier)) {
                System.out.println("No such cashier with ID: " + cashierId);
                return false;
            }
            if(!(cashier.getReceiptMap().contains(receiptId))){
                System.out.println(ExceptionHandler.getTicketNotExists());
                return false;

            }
            if (isServiceId(productIdString)) {
                if (cashier.getReceiptMap().addItemtoReceipt(receiptId, productMap.getService(serviceIdToInteger(productIdString)), amount)) {
                    System.out.println("ticket add: ok");
                }
            } else {

                int productId = Integer.parseInt(args[2]);
                if (args.length == 4) {
                    if (cashier.getReceiptMap().addItemtoReceipt(receiptId, productMap.getProduct(productId), amount)) {
                        System.out.println("ticket add: ok");
                    }
                } else {
                    if (productMap.getProduct(productId) instanceof PersonalizedProducts){
                        List<String> personalizationsList = new ArrayList<>();

                        for (int i = 4; i < args.length; i++) {
                            if (args[i].startsWith("--p")) {
                                personalizationsList.add(args[i].substring(3));
                            }
                        }

                        String[] personalizations = personalizationsList.toArray(new String[0]);

                        if (cashier.getReceiptMap().addPersonalizedItemtoReceipt(receiptId, productMap.getProduct(productId), amount, personalizations)) {
                            System.out.println("ticket add: ok");
                        }
                    }
                }
            }

        }catch(IllegalArgumentException ex){
            System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
        }catch(NullPointerException ex){
            System.out.println(ExceptionHandler.getNullPointerExceptionMessage());
        }

        return false;
    }
    private boolean isServiceId(String line){
        if(line.endsWith("S")){return true;}
        else return false;
    }
    private int serviceIdToInteger(String line) {
        String onlyNumbers = line.replaceAll("[^0-9]", "");
        return Integer.parseInt(onlyNumbers);
    }

}