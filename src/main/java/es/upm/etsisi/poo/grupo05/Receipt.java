package es.upm.etsisi.poo.grupo05;

import java.util.*;

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

    /*
    Adrian, no te he tocado nada (por si acaso :b) Simplemente te he añadido los getter y setters, y he añadido
    un atributo, max_items, porque los tickets no pueden superar 100 items. En el metodo add, simplemente te he añadido
    un condicional mas grande q detecta eso
    - Haoyu
    */

    public boolean reset(){
        this.ticket = new LinkedList<>();
        this.numberItems = 0;
        return true;
    }

    public boolean add(int id, int quantity) {
        boolean result = false;
        boolean added = false;
        Product product = productList.getProduct(id);

        if (numberItems <= max_items) {

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
        }
        return result;
    }
}
