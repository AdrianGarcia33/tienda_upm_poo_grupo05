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


    /**
     * Abstract method to calculate the total price of the product based on the amount.
     *
     * @param amount The quantity of the product.
     * @return The total price of the product.
     */
    //Metodos abstractos
    public abstract float getTotalPrice(int amount);

    /**
     * Abstract method to check if the product is temporally valid.
     * @return True if the product is temporally valid, false otherwise
     */
    public abstract boolean isTemporallyValid();

    /**
     * Abstract method to provide a string representation of the product.
     *
     * @return A string representation of the product.
     */
    @Override
    public abstract String toString();

}
