package es.upm.etsisi.poo.grupo05;

import java.util.Locale;

/**
 * Product Class, which comprises of the data for each objetct product
 */
public class Product {
    private int ID;
    private String name;
    private float price;
    Category category;
    private int quantity;
    boolean discount;
    float afterDiscount;
    /**
     * Builder of this class
     * @param ID
     * @param name
     * @param price
     * @param category
     * @param quantity
     */
    public Product(int ID, String name, float price, Category category, int quantity) {

        this.ID = ID;
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.afterDiscount = category.getAfterDiscount();
    }

    /**
     * Builder of the product class
     * @param other
     */
    public Product(Product other) {
        this.ID = other.ID;
        this.name = other.name;
        this.price = other.price;
        this.category = other.category;
        this.quantity = other.quantity;
        this.discount = other.discount;
        this.afterDiscount = other.afterDiscount;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
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

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
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
    public float getTotalPrice() {
        float total = price * quantity;
        if (discount == true) {
            total *= afterDiscount;
        }
        return total;
    }

    /**
     * Prints on screen a visualization of an object from this class
     * @return
     */
    public String toString(){
        StringBuilder result = new StringBuilder("{class:Product, id:"+ID+", name:'"+name+"', category:"+category+", price:"+price+"}");
                if(discount) {
                    result.append(" **discount-").append(String.format(Locale.US,"%.1f", price * (1 - afterDiscount)));
                }
                return result.toString();
    }
}