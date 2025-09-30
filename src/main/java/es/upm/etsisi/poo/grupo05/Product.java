package es.upm.etsisi.poo.grupo05;

public class Product {
    private int ID;
    private String name;
    private float price;
    Category category;
    private int quantity;
    boolean discount;


    public Product(int ID, String name, float price, Category category, int quantity){
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public float getPrecio() {
        return price;
    }

}