package es.upm.etsisi.poo.grupo05;
import java.util.LinkedList;

public class ticket {
    LinkedList<product> list = new LinkedList<>();
    public product add(int ID, String name, float price, Category category, int quantity) {
        product prodAdd=  new product(ID,name,price,category,quantity);
        list.add(prodAdd);
        return prodAdd;
    }

}
