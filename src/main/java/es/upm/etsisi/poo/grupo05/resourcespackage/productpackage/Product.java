package es.upm.etsisi.poo.grupo05.resourcespackage.productpackage;

/**
 * Abstract base class representing a generic product in the system.
 * It serves as a parent for BasicProducts, PersonalizedProducts, and Events.
 */
public abstract class Product extends TicketElement {
    protected float basePrice;

    /**
     * Constructs a new Product.
     * @param id Unique identifier.
     * @param name Product name.
     * @param basePrice Base price per unit or per person.
     */
    public Product(int id, String name, float basePrice) {
        super(id);
        this.name = name;
        this.basePrice = basePrice;
    }

    /**
     * Getters and Setters
     */

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

}
