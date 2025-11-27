package es.upm.etsisi.poo.grupo05.resourcespackage.productpackage;

/**
 * Enumeration representing product categories and their associated discount multipliers.
 */
public enum Category{
    MERCH(1f),
    STATIONERY(0.95f),
    CLOTHES(0.93f),
    BOOK(0.9f),
    ELECTRONICS(0.97f);

    private float afterDiscount;

    /**
     * Constructor for Category.
     * @param afterDiscount The price multiplier after applying the discount (e.g., 0.95 for 5% off).
     */
    Category(float afterDiscount){
        this.afterDiscount = afterDiscount;
    }

    /**
     * Gets the price multiplier for this category.
     * @return The multiplier to apply to the base price.
     */
    public float getAfterDiscount(){
        return afterDiscount;
    }
}

