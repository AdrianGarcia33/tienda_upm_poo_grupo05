package es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage;

import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.*;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Represents a purchase receipt (ticket) containing a list of products and transaction details.
 */
public class Receipt {
    private String id;
    private final LocalDateTime openingDate;
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
     * Constructs a new Receipt. Generates a unique ID if null is provided.
     *
     * @param id         Unique identifier (can be null).
     * @param cashId     ID of the cashier creating the ticket.
     * @param clientId   ID of the client associated with the ticket.
     * @param productMap Reference to the global product catalog.
     */
    public Receipt(String id, String cashId, String clientId, ProductMap productMap) {
        this.openingDate = LocalDateTime.now();

        if (id == null) {
            this.id = generateId(openingDate);
        } else {
            if (!idsGenerated.add(id)) {
                throw new IllegalArgumentException("The receipt ID is already in use");
            }
            this.id = id;
        }

        this.cashId = cashId;
        this.clientId = clientId;
        this.productMap = productMap;

        this.ticket = new LinkedList<>();
        this.numberItems = 0;
        this.max_items = 100;
        this.ticketState = TicketState.BLANK;
    }

    /**
     * Getters and Setters.
     */
    public String getId() {
        return id;
    }

    public TicketState getTicketState() {
        return ticketState;
    }

    public void setTicketState(TicketState ticketState) {
        this.ticketState = ticketState;
    }

    public String getCashId() {
        return cashId;
    }

    public List<Product> getTicket() {
        return ticket;
    }


    /**
     * Generates and ID for the receipt
     *
     * @param openingDate Date used for the ID
     * @return random ticket ID
     */
    private static String generateId(LocalDateTime openingDate) {
        String prefix = openingDate.format(FORMATTER) + "-";
        String fullId;

        do {
            int rand = 10000 + RANDOM.nextInt(90000);
            fullId = prefix + rand;
        } while (!idsGenerated.add(fullId));

        return fullId;
    }

    /**
     * Resets the receipt, clearing all items.
     *
     * @return true if successful.
     */
    public boolean reset() {
        this.ticket = new LinkedList<>();
        this.numberItems = 0;
        return true;
    }

    /**
     * Adds a product to the receipt. Handles quantity updates for basic products and restrictions for events.
     *
     * @param id       Product ID.
     * @param quantity Amount to add.
     * @return true if successful.
     */
    public boolean addItem(int id, int quantity) {
        boolean result = false;
        boolean added = false;
        Product product = productMap.getProduct(id);

        // If the product is already on the ticket
        if (product != null) {
            if ((numberItems + quantity) <= max_items) {
                for (Product p : ticket) {
                    if (p.getId() == id) {
                        if (p instanceof BasicProducts basicProducts) {
                            basicProducts.setQuantity(basicProducts.getQuantity() + quantity);
                            numberItems += quantity;
                            result = true;
                            added = true;
                        } else if (p instanceof Lunch) {
                            throw new IllegalArgumentException("Lunch already exists");
                        } else if (p instanceof Meeting) {
                            throw new IllegalArgumentException("Meeting already exists");
                        }
                    }
                }
                // If there is not a product, we insert a copy of it
                if (!added) {
                    if (product instanceof BasicProducts) {
                        if (product instanceof PersonalizedProducts) {
                            PersonalizedProducts personalizedProducts = (PersonalizedProducts) product;
                            personalizedProducts.setQuantity(quantity);
                            ticket.add(personalizedProducts);
                            numberItems += quantity;
                            result = true;
                            return result;
                        }
                        BasicProducts productCopy = new BasicProducts((BasicProducts) product);//Por ahora lanza un classCastException, tenemos que asegurarnos de que funcione.
                        productCopy.setQuantity(quantity);
                        ticket.add(productCopy);
                        numberItems += quantity;
                        result = true;

                    } else if (product instanceof Lunch) {
                        Lunch productCopy = new Lunch((Lunch) product);
                        productCopy.setActualParticipants(quantity);
                        ticket.add(productCopy);
                        numberItems += 1;
                        result = true;
                    } else {
                        Meeting productCopy = new Meeting((Meeting) product);
                        productCopy.setActualParticipants(quantity);
                        ticket.add(productCopy);
                        numberItems += 1;
                        result = true;
                    }
                }
            }else System.out.println("Error: you have reached the maximum number of items");
        }
        checkDiscount();
        if (result) {
            this.ticketState = TicketState.ACTIVE;
        }
        return result;
    }

    /**
     * Adds a personalized product with custom texts to the receipt.
     *
     * @param id               Product ID.
     * @param quantity         Amount to add.
     * @param personalizations Array of custom text strings.
     * @return true if successful.
     */
    public boolean addPersonalizedItem(int id, int quantity, String[] personalizations) {
        boolean result = false;
        Product product = productMap.getProduct(id);

        if (product instanceof PersonalizedProducts productCopy) {
            if ((numberItems + quantity) <= max_items) {

                PersonalizedProducts copy = new PersonalizedProducts(productCopy.getId(), productCopy.getName(), productCopy.getBasePrice(), productCopy.getCategory()
                        , quantity, personalizations.length);

                if (copy.addPersonalizations(personalizations)) {
                    copy.setBasePrice(copy.getBasePrice() * (1 + 0.1f * personalizations.length));
                    ticket.add(copy);
                    numberItems += quantity;
                    ticketState = TicketState.ACTIVE;
                    checkDiscount();
                    result = true;
                    return result;
                }
            }else System.out.println("Error: you have reached the maximum number of items");
        }
        return result;
    }

    /**
     * Removes a product from the receipt by its ID.
     *
     * @param id Product ID.
     * @return true if successful.
     */
    public boolean removeItem(int id) {

        boolean result = false;
        Iterator<Product> it = ticket.iterator();
        while (it.hasNext() && !result) {
            Product p = it.next();
            if (p.getId() == id) {
                if (p instanceof BasicProducts) {
                    numberItems -= ((BasicProducts) p).getQuantity();
                } else {
                    numberItems -= 1;
                }
                it.remove();
                result = true;
            }
        }
        checkDiscount();
        if (ticket.isEmpty()) {
            this.ticketState = TicketState.BLANK;
        }
        return result;
    }

    /**
     * Finalizes the receipt, updates its state to CLOSED, and appends the closing timestamp to the ID.
     */
    private void closeTicket() {
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
     * Checks and applies discounts based on product categories.
     */
    private void checkDiscount() {
        for (Category category : Category.values()) {
            // Step 1: Count all products of this category
            int count = 0;
            for (Product p : ticket) {
                if (p instanceof BasicProducts) {
                    BasicProducts basicProduct = (BasicProducts) p;
                    if (basicProduct.getCategory() == category) {
                        count += basicProduct.getQuantity();
                    }
                }
            }

            // Step 2: Apply or remove discount based on the total count
            boolean applyDiscount = count > 1;
            for (Product p : ticket) {
                if (p instanceof BasicProducts) {
                    BasicProducts basicProduct = (BasicProducts) p;
                    if (basicProduct.getCategory() == category) {
                        basicProduct.setDiscount(applyDiscount);
                    }
                }
            }
        }
    }


    /**
     * Searches for a product in the ticket by its ID.
     *
     * @param id Product ID.
     * @return The product or null if not found.
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
     * Closes the ticket and generates the final bill string with totals.
     *
     * @return The formatted ticket string.
     */
    public String print() {
        if (ticketState == TicketState.ACTIVE || ticketState == TicketState.BLANK) {
            //Hay que actualizar el id en el gestor
            closeTicket();
        }
        return generateBillString();
    }

    private void checkTicketClosed() {
        if (ticketState == TicketState.CLOSED) {
            throw new IllegalArgumentException("Error: This ticket is already closed");
        }
    }

    /**
     * Generates a provisional bill string without closing the ticket.
     *
     * @return The formatted provisional ticket string.
     */
    public String provisionalPrice() {
        return generateBillString();
    }

    /**
     * Private helper to generate the formatted bill string to avoid code duplication.
     */
    private String generateBillString() {
        List<Product> ticketArray = new ArrayList<>(ticket);
        ticketArray.sort(Comparator.comparing(Product::getName));

        StringBuilder sb = new StringBuilder();
        sb.append("Ticket : ").append(id).append("\n");

        double totalPrice = 0.0;
        double finalPrice = 0.0;

        for (Product p : ticketArray) {
            float price = p.getBasePrice();
            if (p instanceof BasicProducts bp) {
                int quantity = bp.getQuantity();
                for (int i = 0; i < quantity; i++) {
                    sb.append("\t").append(bp.toString()).append("\n");
                }
                totalPrice += (price * quantity);
                finalPrice += p.getTotalPrice(quantity);
            } else {
                sb.append("\t").append(p.toString()).append("\n");

                // Calculate price based on actual participants
                int participants = ((Events) p).getActualParticipants();
                float eventTotal = p.getTotalPrice(participants);

                totalPrice += eventTotal;
                finalPrice += eventTotal;
            }
        }
        double totalDiscount = totalPrice - finalPrice;

        sb.append("\tTotal price: " + String.format(Locale.US, "%.3f", totalPrice) + "\n");
        sb.append("\tTotal discount: " + String.format(Locale.US, "%.6f", totalDiscount) + "\n");
        sb.append("\tFinal price: " + String.format(Locale.US, "%.3f", finalPrice) + "\n");

        return sb.toString();
    }

    /**
     *
     * TOSTRING ANTIGUO
     *
     *
     * List<Product> ticketArray = new ArrayList<>(ticket);
     *         ticketArray.sort(Comparator.comparing(Product::getName));
     *
     *         StringBuilder sb = new StringBuilder();
     *         sb.append("ticket: ").append(id).append("\n");
     *
     *         double totalPrice = 0.0;
     *         double finalPrice = 0.0;
     *
     *         for (Product p : ticketArray) {
     *             float price = p.getBasePrice();
     *             if (p instanceof BasicProducts bp) {
     *                 int quantity = bp.getQuantity();
     *                 for (int i = 0; i < quantity; i++) {
     *                     sb.append("\t").append(bp.toString()).append("\n");
     *                 }
     *                 totalPrice += (price * quantity);
     *                 finalPrice += p.getTotalPrice(quantity);
     *             } else {
     *                 sb.append("\t").append(p.toString()).append("\n");
     *                 totalPrice += price;
     *                 finalPrice += price;
     *             }
     *         }
     *         double totalDiscount = totalPrice - finalPrice;
     *         sb.append("\tTotal price: " + String.format(Locale.US, "%.3f", totalPrice) + "\n");
     *         sb.append("\tTotal discount: " + String.format(Locale.US, "%.6f", totalDiscount) + "\n");
     *         sb.append("\tFinal price: " + String.format(Locale.US, "%.3f", finalPrice) + "\n");
     */
}
