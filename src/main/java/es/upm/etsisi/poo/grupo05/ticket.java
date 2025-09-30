package es.upm.etsisi.poo.grupo05;
import java.util.LinkedList;

public class ticket {
    LinkedList<Product> list = new LinkedList<>();
    public Product add(int ID, String name, float price, Category category, int quantity) {
        Product prodAdd=  new Product(ID,name,price,category,quantity);
        list.add(prodAdd);
        return prodAdd;
    }

}
