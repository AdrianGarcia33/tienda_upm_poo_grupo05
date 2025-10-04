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

    public Receipt() {
        this.ticket = new LinkedList<>();
        this.numberItems = 0;
        max_items = 100;
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

        if (numberItems < max_items) {  //creo que esto puede dar problemas, si queremos añadir 3 ud y solo queda 1 espacio hay que añadir solo 1 o no se añade?
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
                // Si no está el producto
                if (!added) {
                    product.setQuantity(quantity);
                    ticket.add(product);
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
    public boolean removeItem(int id) { // Una pregunta, en este metodo no habría que mirar si el ciente quiere
                                        //quitar x cantidad de producto? porque aquí solo está la opción
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
    // El print es horrible y me lo ha hecho la ia usa una arraylist pq se puede ordenar
    // y decia q las linked list estan mal optimizadas
    public String print() {
        List<Product> ticketArray = new ArrayList<>(ticket);
        ticketArray.sort(Comparator.comparing(Product::getName));

        StringBuilder sb = new StringBuilder();
        double totalPrice = 0.0;
        double totalDiscount = 0.0;
        double finalPrice = 0.0;
        for(Product p : ticketArray){
           sb.append(p.toString());
           totalPrice += p.getPrice()*p.getQuantity();
           finalPrice +=p.getTotalPrice();
        }
        totalPrice = totalPrice - finalPrice;
        sb.append("Total price: " + totalPrice + "\n");
        sb.append("Total discount: " + totalDiscount + "\n");
        sb.append("Final price: " + finalPrice + "\n");
        reset();
        return sb.toString();
    }
}
