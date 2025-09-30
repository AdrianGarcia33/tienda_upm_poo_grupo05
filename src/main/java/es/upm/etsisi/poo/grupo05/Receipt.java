package es.upm.etsisi.poo.grupo05;

import java.util.*;

public class Receipt {
    private List<Product> ticket;
    private int quantity;

    public Receipt() {
        this.ticket = new LinkedList<>();
        this.quantity = 0;
    }

    public boolean reset(){
        this.ticket = new LinkedList<>();
        this.quantity = 0;
        return true;
    }
    public boolean add(Product product, int quantity) {
        boolean result = false;
        if(product != null || quantity > 0){
            for(Product p : ticket){

            }
        }
        return result;

    }
}
