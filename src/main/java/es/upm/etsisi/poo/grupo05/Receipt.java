package es.upm.etsisi.poo.grupo05;

import java.util.*;

public class Receipt {
    private List<Product> ticket;
    private int numberItems;
    private ProductList productList;

    public Receipt() {
        this.ticket = new LinkedList<>();
        this.numberItems = 0;
    }

    public boolean reset(){
        this.ticket = new LinkedList<>();
        this.numberItems = 0;
        return true;
    }

    public boolean add(int id, int quantity) {
        boolean result = false;
        boolean added = false;
        Product product = productList.getProduct(id);

        // Si el producto ya esta
        if (product != null && quantity > 0) {
            for(Product p : ticket){
                if (p.getID() == id) {
                    p.setQuantity(p.getQuantity() + quantity);
                    numberItems += quantity;
                    result = true;
                    added = true;
                }
            }
            // Si no esta el producto
            if (!added) {
                product.setQuantity(quantity);
                ticket.add(product);
                numberItems += quantity;
                result = true;
            }
        }
        return result;
    }
}
