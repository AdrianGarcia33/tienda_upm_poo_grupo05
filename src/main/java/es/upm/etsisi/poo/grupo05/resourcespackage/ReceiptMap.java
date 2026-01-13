package es.upm.etsisi.poo.grupo05.resourcespackage;

import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.PersonalizedProducts;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.TicketElement;
import es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage.Receipt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Class responsible for managing the collection of receipts (tickets) in the system.
 * It allows adding, removing, and modifying receipts, as well as managing items within them.
 */
public class ReceiptMap {
    private HashMap<String, Receipt> receiptmap;
    private int num_receipt;

    /**
     * Constructor of the class.
     */
    public ReceiptMap () {
        this.receiptmap = new HashMap<>();
        this.num_receipt = 0;
    }

    /**
     * Retrieves the complete map of stored receipts.
     *
     * @return The HashMap containing all receipts.
     */
    public HashMap<String, Receipt> getReceiptmap() {
        return receiptmap;
    }

    /**
     * Sets the map of receipts.
     *
     * @param receiptmap The new HashMap of receipts.
     */
    public void setReceiptmap(HashMap<String, Receipt> receiptmap) {
        this.receiptmap = receiptmap;
    }

    /**
     * Adds a new receipt to the map if its ID does not already exist.
     *
     * @param receipt The receipt object to add.
     * @return true if the receipt was successfully added, false otherwise.
     */
    public boolean newReceipt(Receipt receipt) {
        String id = receipt.getId();
        if (!receiptmap.containsKey(id)) {
            receiptmap.put(id, receipt);
            num_receipt++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a receipt with the given ID exists in the map.
     *
     * @param id The ID of the receipt to check.
     * @return true if the receipt exists, false otherwise.
     */
    public boolean contains(String id) {
        return receiptmap.containsKey(id);
    }

    /**
     * Adds a specific quantity of a standard product to a specific receipt.
     * If successful, it prints the provisional price of the receipt.
     *
     * @param receipt_id The ID of the receipt.
     * @param product    The product to add.
     * @param quantity   The amount of the product to add.
     * @return true if the item was added successfully, false if the receipt was not found.
     */
    public boolean addItemtoReceipt(String receipt_id, TicketElement product, int quantity) {
        if(receiptmap.containsKey(receipt_id)) {
            Receipt receipt = receiptmap.get(receipt_id);
            if (receipt.addProduct(product, quantity, new String[0])) {
                System.out.println(receipt.provisionalPrice());
                return true;
            }
        } else {
            System.out.println("Error: TicketNotFound");
        }
        return false;
    }

    /**
     * Adds a personalized product with custom texts to a specific receipt.
     * If successful, it prints the provisional price of the receipt.
     *
     * @param receipt_id       The ID of the receipt.
     * @param prod_id          The ID of the product to add.
     * @param quantity         The amount of the product to add.
     * @param personalizations Array of strings containing the customizations.
     * @return true if the item was added successfully, false if the receipt was not found.
     */
    public boolean addPersonalizedItemtoReceipt(String receipt_id, PersonalizedProducts prod_id, int quantity, String[] personalizations) {
        if(receiptmap.containsKey(receipt_id)) {
            Receipt receipt = receiptmap.get(receipt_id);
            if (receipt.addProduct(prod_id, quantity, personalizations)) {
                System.out.println(receipt.provisionalPrice());
                return true;
            }
        } else {
            System.out.println("Error: TicketNotFound");
        }
        return false;
    }

//    /**
//     * Removes a specific product from a specific receipt.
//     *
//     * @param receipt_id The ID of the receipt.
//     * @param prod_id    The ID of the product to remove.
//     * @return true if the removal was successful, false if the receipt was not found.
//     */
//    public boolean removeItemFromReceipt(String receipt_id, int prod_id) {
//        if (receiptmap.containsKey(receipt_id)) {
//            Receipt receipt = receiptmap.get(receipt_id);
//            if (receipt.removeItem()) {
//                return true;
//            }
//        } else {
//            System.out.println("Error: TicketNotFound");
//        }
//        return false;
//    }

    /**
     * Generates the printable string (invoice) for a specific receipt.
     *
     * @param receipt_id The ID of the receipt to print.
     * @return The string representation of the receipt, or an empty string if not found.
     */
    public String print(String receipt_id) {
        String salida = "";
        if(receiptmap.containsKey(receipt_id)) {
            Receipt receipt = receiptmap.get(receipt_id);
            salida = receipt.print();
        } else {
            System.out.println("Error: TicketNotFound");
        }
        return salida;
    }

    /**
     * Removes a specific product from all existing receipts in the map.
     *
     * @param prod_id The ID of the product to remove globally.
     * @return true after iterating through all receipts.
     */
    public boolean removeItemsFromAllReceipts(int prod_id) {
        for (Receipt receipt : receiptmap.values()) {
            receipt.removeItem(prod_id);
        }
        return true;
    }


    /**
     * Generates a list of all receipts currently in the map,
     * sorted by the Cashier ID associated with the receipt.
     *
     * @return A formatted String containing the list of receipts (ID and State).
     */
    public String list() {
        ArrayList<Receipt> receiptlist = new ArrayList<>(num_receipt);
        StringBuilder result = new StringBuilder();

        for (Receipt receipt : receiptmap.values()) {
            receiptlist.add(receipt);
        }

        receiptlist.sort(Comparator.comparing(Receipt::getCashId));

        for (int i = 0; i < receiptlist.size(); i++) {
            result.append("\t").append(receiptlist.get(i).getId()).append(" - ").append(receiptlist.get(i).getTicketState()).append("\n");
        }

        return result.toString();
    }

}
