package es.upm.etsisi.poo.grupo05;

import java.util.*;

/**
 * Class which records the items a client wants to purchase
 */
public class Receipt {
    private List<Product> ticket;
    private int numberItems;
    private ProductList productList;
    private int max_items;

    public Receipt(ProductList productList) {
        this.ticket = new LinkedList<>();
        this.numberItems = 0;
        max_items = 100;
        this.productList = productList;
    }

    //Getters and Setters

    public List<Product> getTicket() {
        return ticket;
    }

    public void setTicket(List<Product> ticket) {
        this.ticket = ticket;
    }

    public int getMax_items() {
        return max_items;
    }

    public void setMax_items(int max_items) {
        this.max_items = max_items;
    }

    public ProductList getProductList() {
        return productList;
    }

    public void setProductList(ProductList productList) {
        this.productList = productList;
    }

    public int getNumberItems() {
        return numberItems;
    }

    public void setNumberItems(int numberItems) {
        this.numberItems = numberItems;
    }

    //Methods


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
        Product product = productList.getProduct(id);

        if (numberItems < max_items) {
            // Si el producto ya está
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
                    Product productCopy = new Product(product);
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
        while (it.hasNext()) {
            Product p = it.next();
            if (p.getID() == id) {
                numberItems -= p.getQuantity();
                it.remove();
                result = true;
            }
        }
        checkDiscount();
        return result;
    }

    /**
     * Iterative methods which goes through the list 5 times and checks if there are more than one
     * product of the same category. If so, sets the boolean discount to true. Complexity O(n)
     */
    private void checkDiscount() {
        for (Category category : Category.values()) {
            int count = 0;
            for (Product p : ticket) {
                if (p.getCategory() == category) {
                    count += p.getQuantity();
                }
            }
            if (count > 1) {
                for (Product p : ticket) {
                    if (p.getCategory() == category) {
                        p.setDiscount(true);
                    }
                }
            } else {
                for (Product p : ticket) {
                    if (p.getCategory() == category) {
                        p.setDiscount(false);
                    }
                }
            }
        }
    }


    public Product getProduct(int id) {
        for (Product p : ticket) {
            if (p.getID() == id) {
                return p;
            }
        }
        // Podemos lanzar una excepcion si no os gusta devolver null
        return null;
    }

    public String print() {

        List<Product> ticketArray = new ArrayList<>(ticket);
        ticketArray.sort(Comparator.comparing(Product::getName));

        StringBuilder sb = new StringBuilder();
        double totalPrice = 0.0;
        double totalDiscount = 0.0;
        double finalPrice = 0.0;
        for(Product p : ticketArray){
            int quantity = p.getQuantity();
            float price = p.getPrice();
            for (int i = 0; i < quantity; i++) {
                sb.append(p.toString()+"\n");
            }
            totalPrice += (price * quantity);
            finalPrice += p.getTotalPrice();
            if (p.getDiscount()) {
                totalDiscount = totalPrice-finalPrice;
            }

        }
        sb.append("Total price: " + String.format("%.1f", totalPrice) + "\n");
        sb.append("Total discount: " + String.format(Locale.US,"%.1f", totalDiscount)  + "\n");
        sb.append("Final price: " + String.format(Locale.US,"%.1f", finalPrice) + "\n");


        return sb.toString();
    }
}
