package es.upm.etsisi.poo.grupo05.resourcespackage.productpackage;

public abstract class Product {
    protected int id;
    protected String name;
    protected float basePrice;

    public Product(int id, String name, float basePrice) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;

    }
    // Getters and Setters
    // NO SE SI DEBERIAN SER PUBLICOS
    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBasePrice() {
        return this.basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }


    //Metodos abstractos
    public abstract float getTotalPrice(int amount);

    public abstract boolean isTemporallyValid();

    @Override
    public abstract String toString();

}
