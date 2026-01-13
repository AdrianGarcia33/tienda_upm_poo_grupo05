package es.upm.etsisi.poo.grupo05.resourcespackage.productpackage;

public abstract class TicketElement {
    protected String name;
    protected int id;

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
