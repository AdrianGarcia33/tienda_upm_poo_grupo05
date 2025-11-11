package es.upm.etsisi.poo.grupo05.receiptpackage;

import es.upm.etsisi.poo.grupo05.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.productpackage.Category;
import es.upm.etsisi.poo.grupo05.userpackage.Client;
import es.upm.etsisi.poo.grupo05.ProductMap;
import es.upm.etsisi.poo.grupo05.productpackage.BasicProducts;
import es.upm.etsisi.poo.grupo05.productpackage.Product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Class which records the items a client wants to purchase
 */
public class Receipt {
    private String id;
    private LocalDateTime openingDate;
    private LocalDateTime closingDate;
    private TicketState ticketState;

    private final String cashId;
    private final String clientId;

    private List<Product> ticket;
    private int numberItems;
    private int max_items;
    private ProductMap productMap;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm");
    private static final Random RANDOM = new Random();
    private static final Set<String> idsGenerated = new HashSet<>();

    /**
     * Builder of this class
     * @param productMap
     */
    public Receipt(String id, Cashier cashier, Client client, ProductMap productMap) {
        this.openingDate = LocalDateTime.now();

        if(id == null){
            this.id = generateId(openingDate);
        } else {
            if (!idsGenerated.add(id)) {
                throw new IllegalArgumentException("The receipt ID is already in use");
            }
            this.id = id;
        }

        this.cashId = cashier.getId();
        this.clientId = client.getId();
        this.productMap = productMap;

        this.ticket = new LinkedList<>();
        this.numberItems = 0;
        this.max_items = 100;
        this.ticketState = TicketState.BLANK;
    }

    //Getters and Setters
    public String getId() {
        return id;
    }

    public TicketState getTicketState() {
        return ticketState;
    }

    public String getCashId() {
        return cashId;
    }

    public List<Product> getTicket() {
        return ticket;
    }

    //Methods
    private static String generateId(LocalDateTime openingDate) {
        String prefix = openingDate.format(FORMATTER) + "-";
        String fullId;

        do{
            int rand = 10000 + RANDOM.nextInt(90000);
            fullId = prefix + rand;
        }while(!idsGenerated.add(fullId));

        return fullId;
    }

    /**
     * As the name suggests, it resets the ticket
     * @return true if it was succesful
     */
    public boolean reset(){
        this.ticket = new LinkedList<>();
        this.numberItems = 0;
        return true;
    }

    /**
     * Adds an item from the catalog to the ticket
     * @param id key of the product being added
     * @param quantity amount
     * @return true if succesful
     */
    public boolean addItem(int id, int quantity) {
        boolean result = false;
        boolean added = false;
        Product product = productMap.getProduct(id);

        if (numberItems + quantity < max_items) {
            // If the product is already on the ticket
            if (product != null && quantity > 0) {
                for(Product p : ticket){
                    if (p.getID() == id) {
                        p.setQuantity(p.getQuantity() + quantity);
                        numberItems += quantity;
                        result = true;
                        added = true;
                    }
                }
                // If there is not a product, we insert a copy of it
                if (!added) {
                    BasicProducts productCopy = new BasicProducts((BasicProducts) product); //Por ahora lanza un classCastException, tenemos que asegurarnos de que funcione.
                    productCopy.setQuantity(quantity);
                    ticket.add(productCopy);
                    numberItems += quantity;
                    result = true;
                }
            }
        }
        checkDiscount();
        return result;
    }

    /** It removes the item from
     * @param id
     * @return
     */
    public boolean removeItem(int id) {

        boolean result = false;
        Iterator<Product> it = ticket.iterator();
        while (it.hasNext() && !result) {
            Product p = it.next();
            if (p.getId() == id) {
                if(p instanceof BasicProducts){
                    numberItems -= ((BasicProducts) p).getQuantity();
                }else {numberItems -= 1;}
                it.remove();
                result = true;
            }
        }
        checkDiscount();
        if(ticket.isEmpty()){
            this.ticketState = TicketState.BLANK;
        }
        return result;
    }

    public void closeTicket() {
        checkTicketClosed();

        closingDate = LocalDateTime.now();
        ticketState = TicketState.CLOSED;

        String oldId = this.id;
        String sufix = "-" + closingDate.format(FORMATTER);

        this.id = oldId + sufix;

        idsGenerated.remove(oldId);
        idsGenerated.add(this.id);
    }

    /**
     * Iterative methods which goes through the list 5 times and checks if there are more than one
     * product of the same category. If so, sets the boolean discount to true. Complexity O(n)
     */
    private void checkDiscount() {
        for (Category category : Category.values()) {
            int count = 0;
            for (Product p : ticket) {
                if (p instanceof BasicProducts) {
                    BasicProducts basicProduct = (BasicProducts) p;
                    if (basicProduct.getCategory() == category) {
                        count += basicProduct.getQuantity();
                        basicProduct.setDiscount(count > 1);
                    }
                }
            }
        }
    }


    /**
     * Searches for a product by its id
     * @param id
     * @return the searched product or null if not found
     */
    public Product getProduct(int id) {
        for (Product p : ticket) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Print the ticket
     * @return string with the ticket information
     */
    public String print() {

        if(ticketState == TicketState.BLANK){
            return "BLANK TICKET";
        }

        if (ticketState == TicketState.ACTIVE) {
            //Hay que actualizar el id en el gestor
            closeTicket();
        }

        List<Product> ticketArray = new ArrayList<>(ticket);
        ticketArray.sort(Comparator.comparing(Product::getName));

        StringBuilder sb = new StringBuilder();
        sb.append("--- TICKET BILL ---\n");

        double totalPrice = 0.0;
        double totalDiscount = 0.0;
        double finalPrice = 0.0;
        for(Product p : ticketArray){
            float price = p.getBasePrice();
            if(p instanceof BasicProducts) {
                BasicProducts bp = (BasicProducts) p;
                int quantity = bp.getQuantity();
                for (int i = 0; i < quantity; i++) {
                    sb.append(bp.toString() + "\n");
                }
                totalPrice += (price * quantity);
                finalPrice += p.getTotalPrice(quantity);
            }else {
                sb.append(p.toString() + "\n");
                totalPrice += price;
                finalPrice += price;
            }
        }
        totalDiscount = totalPrice-finalPrice;
        sb.append("Total price: " + String.format(Locale.US,"%.1f", totalPrice) + "\n");
        sb.append("Total discount: " + String.format(Locale.US,"%.1f", totalDiscount)  + "\n");
        sb.append("Final price: " + String.format(Locale.US,"%.1f", finalPrice) + "\n");


        return sb.toString();
    }

    private void checkTicketClosed(){
        if (ticketState == TicketState.CLOSED) {
            throw new IllegalArgumentException("Error: This ticket is already closed");
        }
    }
}
