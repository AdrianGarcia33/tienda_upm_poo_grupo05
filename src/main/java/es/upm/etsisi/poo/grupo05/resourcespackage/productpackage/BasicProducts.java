package es.upm.etsisi.poo.grupo05.resourcespackage.productpackage;

import java.util.Locale;

/**
 * Represents standard products (e.g., Books, Clothes) that belong to a specific category.
 * These products can have discounts applied based on the quantity purchased.
 */
public class BasicProducts extends Product{
    protected Category category;
    protected int quantity;
    protected boolean discount;
    protected float afterDiscount;
    protected float discountedprice;



    /**
     * Constructs a new BasicProduct.
     * @param ID Unique identifier.
     * @param name Product name.
     * @param price Unit price.
     * @param category Product category.
     * @param quantity Initial quantity.
     */
    public BasicProducts(int ID, String name, float price, Category category, int quantity) {
        super(ID, name, price);
        this.category = category;
        this.quantity = quantity;
        this.afterDiscount = category.getAfterDiscount();
        this.discountedprice = basePrice * afterDiscount;
    }

    /**
     * Copy constructor. Creates a new BasicProducts instance from an existing one.
     * @param other The product object to copy.
     */
    public BasicProducts(BasicProducts other) { //esto es basicamente el clone de la clase Object creo
        super(other.id, other.name, other.basePrice);
        this.category = other.category;
        this.quantity = other.quantity;
        this.discount = other.discount;
        this.afterDiscount = other.afterDiscount;
    }



    /**
     * Getters and Setters
     */
    public Category getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean getDiscount() {
        return discount;
    }

    public float getAfterDiscount() {
        return afterDiscount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public void setAfterDiscount(float afterDiscount) {
        this.afterDiscount = afterDiscount;
    }



    /**
     * Calculates the total price for a given quantity.
     * Applies the category discount.
     * @param quantity The quantity of items.
     * @return The total price.
     */
    public float getTotalPrice(int quantity) {
        float total = basePrice * quantity;
        if (discount == true) {
            total *= afterDiscount;
        }
        return total;
    }

    /**
     * Checks temporal validity.
     * @return Always true for basic products as they do not expire.
     */
    public boolean isTemporallyValid() {
        return true;
    }


    /**
     * Returns the string representation of the product
     */
    public String toString(){
        StringBuilder result = new StringBuilder("{class:Product, id:"+id+", name:'"+name+"', category:"+category+", price:"+basePrice+"}");

        if(discount) {
            result.append(" **discount-").append(String.format(Locale.US,"%.2f", basePrice * (1 - afterDiscount)));
        }

        return result.toString();
    }
}