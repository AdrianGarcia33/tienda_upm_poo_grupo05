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

    public boolean addItem(int id, int quantity) {
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
        return result;
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
        sb.append("===== TICKET =====\n");

        double total = 0.0;
        for (Product p : ticketArray) {
            double precioProd = p.getTotalPrice();
            sb.append(p.getName())
                    .append(" x").append(p.getQuantity())
                    .append(" -> ").append(String.format("%.2f", precioProd)).append(" €\n");
            total += precioProd;
        }

        sb.append("------------------\n");
        sb.append("TOTAL: ").append(String.format("%.2f", total)).append(" €\n");

        reset();
        return sb.toString();
    }
}
