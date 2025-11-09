package es.upm.etsisi.poo.grupo05;

public abstract class Product {
    protected String id;
    protected String name;
    protected float basePrice;

    public Product(String id, String name, float basePrice) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
    }
    // Getters and Setters
    // NO SE SI DEBERIAN SER PUBLICOS

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract float getTotalPrice(int amount);

    public abstract boolean isTemporallyValid();

    @Override
    public abstract String toString();

}
