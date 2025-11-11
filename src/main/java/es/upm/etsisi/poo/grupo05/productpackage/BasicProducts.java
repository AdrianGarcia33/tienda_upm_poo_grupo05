package es.upm.etsisi.poo.grupo05.productpackage;

import java.util.Locale;

/**
 * Product Class, which comprises of the data for each objetct product
 */
public class BasicProducts extends Product{
    protected Category category;
    protected int quantity;
    protected boolean discount;
    protected float afterDiscount;
    protected float discountedprice;



    /**
     * Builder of this class
     * @param ID
     * @param name
     * @param price
     * @param category
     * @param quantity
     */
    public BasicProducts(int ID, String name, float price, Category category, int quantity) {
        super(ID, name, price);
        this.category = category;
        this.quantity = quantity;
        this.afterDiscount = category.getAfterDiscount();
        this.discountedprice = basePrice * afterDiscount;
    }

    /**
     * Builder of the product class
     * @param other
     */
    public BasicProducts(BasicProducts other) { //esto es basicamente el clone de la clase Object creo
        super(other.id, other.name, other.basePrice);
        this.category = other.category;
        this.quantity = other.quantity;
        this.discount = other.discount;
        this.afterDiscount = other.afterDiscount;
    }



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
     * Method that calculates the total price if the boolean discount is set to true
     * @return
     */
    public float getTotalPrice(int quantity) {
        float total = basePrice * quantity;
        if (discount == true) {
            total *= afterDiscount;
        }
        return total;
    }

    public boolean isTemporallyValid() {
        return true;
    }

    /**
     * Prints on screen a visualization of an object from this class
     * @return
     */
    public String toString(){
        StringBuilder result = new StringBuilder("{class:Product, id:"+id+", name:'"+name+"', category:"+category+", price:"+basePrice+"}");

        if(discount) {
            result.append(" **discount-").append(String.format(Locale.US,"%.1f", basePrice * (1 - afterDiscount)));
        }

        return result.toString();
    }
}