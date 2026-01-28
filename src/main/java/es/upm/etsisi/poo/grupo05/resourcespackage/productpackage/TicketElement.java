package es.upm.etsisi.poo.grupo05.resourcespackage.productpackage;

/**
 * Abstract base class representing a generic element in the system.
 * It serves as a parent for Product and ProductService.
 */
public abstract class TicketElement {
    protected int id;

    /**
     * Constructs a new TicketElement.
     * @param id
     */
    public TicketElement(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

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
