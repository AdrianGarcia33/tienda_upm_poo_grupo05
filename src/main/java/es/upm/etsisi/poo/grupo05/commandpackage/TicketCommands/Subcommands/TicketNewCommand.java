package es.upm.etsisi.poo.grupo05.commandpackage.TicketCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.UserMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.Product;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.ProductService;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.ServiceType;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.TicketElement;
import es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage.EnterprisePrinter;
import es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage.NormalPrinter;
import es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage.Receipt;
import es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage.TicketType;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Client;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

import static es.upm.etsisi.poo.grupo05.ExceptionHandler.*;

/**
 * Class for the ticket new command, it will create a new ticket
 */
public class TicketNewCommand extends Command {
    private UserMap userMap;
    private ProductMap productMap;
    public TicketNewCommand(String name, UserMap userMap, ProductMap productMap) {
        super(name);
        this.userMap = userMap;
        this.productMap = productMap;
    }

    @Override
    public boolean apply(String[] args) {

        try{
            String ticketID=null, userID, cashierID;
            TicketType ticketType = TicketType.PRODUCT;
            switch (args.length){
                case 4:
                    ticketID = args[0];
                    cashierID = args[1];
                    userID = args[2];
                    ticketType = getTicketType(args[3]);
                    break;
                case 3:
                    if(isTicketId(args[0])){
                        ticketID= args[0];
                        cashierID = args[1];
                        userID = args[2];
                    }else{
                        cashierID = args[0];
                        userID = args[1];
                        ticketType = getTicketType(args[2]);
                    }
                    break;
                case 2:
                    cashierID = args[0];
                    userID = args[1];
                    break;
                default:
                    System.out.println(getInputMismatchExceptionMessage());
                    return false;
            }
                User cashier = userMap.getUserMap().get(cashierID);

                if (!(cashier instanceof Cashier)) {
                    System.out.println(getNotInstanceOfCashierMessage());
                    return false;
                }
                if( (DNIAcceptable(userID) && ticketType!= TicketType.PRODUCT) || userMap.getUserMap().get(userID) instanceof Cashier) {
                    System.out.println(getInputMismatchExceptionMessage());
                    return false;
                }
                if(ticketType== TicketType.COMBINED) {
                    Receipt<TicketElement> receipt = new Receipt<>(ticketID, cashierID, userID, productMap, ticketType);
                    if (cashier.getReceiptMap().newReceipt(receipt)) {
                        System.out.println(receipt.provisionalPrice());
                        System.out.println("ticket new: ok\n");
                    }
                }
                if(ticketType== TicketType.SERVICE) {
                    Receipt<ProductService> receipt = new Receipt<>(ticketID, cashierID, userID, productMap, ticketType);

                    if (cashier.getReceiptMap().newReceipt(receipt)) {
                        System.out.println(receipt.provisionalPrice());
                        System.out.println("ticket new: ok\n");
                    }
                }

                if(ticketType== TicketType.PRODUCT) {
                    Receipt<Product> receipt = new Receipt<>(ticketID, cashierID, userID, productMap, ticketType);

                    if (cashier.getReceiptMap().newReceipt(receipt)) {
                        System.out.println(receipt.provisionalPrice());
                        System.out.println("ticket new: ok\n");
                    }
                }
        }catch(IllegalArgumentException e){
            System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
        }catch (NullPointerException e){
            System.out.println(ExceptionHandler.getNullArgument());
        }
        return false;
    }
    private boolean isTicketId(String id){
        boolean solucion=true;
        if(id.length()<=6) {
            for(int i=0;i<id.length() && solucion;i++){
                if(!Character.isDigit(id.charAt(i))){
                    solucion=false;
                }
            }
        }else{
            solucion = false;
        }
        return solucion;
    }
    private TicketType getTicketType(String line){
        if(line.equals("-p")) {
            return TicketType.PRODUCT;
        }else{
            if(line.equals("-s")){
                return TicketType.SERVICE;
            }else{
                if(line.equals("-c")){
                    return TicketType.COMBINED;
                }else{
                    System.out.println(getTicketTypeUnacceptable());
                    return null;
                }
            }
        }
    }
    private boolean DNIAcceptable(String DNI){
        if(userMap.getUserMap().containsKey(DNI)){
            return false;
        }else{
            if(DNI.length()==9 && DNI.matches(".*[a-zA-Z]$"))return true;
            else{
                return false;
            }
        }
    }
}
