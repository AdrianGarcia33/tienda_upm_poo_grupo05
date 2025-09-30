package es.upm.etsisi.poo.grupo05;

public class Product {
    private int ID;
    private String name;
    private float price;
    Category category;
    private int quantity;
    boolean discount;
    float discountPercentage;


    public Product(int ID, String name, float price, Category category, int quantity) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.category = category;
        switch (category) {
            case PAPELERIA:
                this.discountPercentage = 0.95f;
                break;
            case ROPA:
                this.discountPercentage = 0.93f;
                break;
            case LIBRO:
                this.discountPercentage = 0.9f;
                break;
            case ELECTRONICA:
                this.discountPercentage = 0.97f;
                break;
            default:
                this.discountPercentage = 1f;
                break;
        }
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

    public float getDiscountPercentage() {
        return discountPercentage;
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

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }


    public float getTotalPrice() {
        float total = price * quantity;
        if (discount == true) {
            total *= discountPercentage;
        }
        return total;
    }
    public String toString(){
        StringBuilder result = new StringBuilder("{class:Product, id:"+ID+", name:'"+name+"', category:"+category+", price:"+price+"}");
                if(discount) {
                    result.append(", **discountPercentage: -").append(1-discountPercentage);
                }
                return result.toString();
    }
}