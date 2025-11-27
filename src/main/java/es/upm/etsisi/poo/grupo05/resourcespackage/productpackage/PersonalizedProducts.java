package es.upm.etsisi.poo.grupo05.resourcespackage.productpackage;

/**
 * Represents a product that can be customized with specific texts.
 * Extends BasicProducts to include a list of custom strings and specific pricing logic.
 */
public class PersonalizedProducts extends BasicProducts{
    private int maxPersonalizedTexts;
    private String[] personalizations;
    private int num_personalization;

    //Creo que personalizations se tiene que quedar primero vacio, y en receipt add dependiendo del numero de personalizaciones añadiremos o no

    /**
     * Constructs a new PersonalizedProducts instance.
     * @param id Product ID.
     * @param name Product name.
     * @param price Base price.
     * @param category Product category.
     * @param quantity Initial quantity.
     * @param maxPersonalizedTexts Maximum number of allowed custom texts.
     */
    public PersonalizedProducts(int id, String name, float price, Category category, int quantity, int maxPersonalizedTexts) {
        super(id, name, price, category, quantity);
        this.maxPersonalizedTexts = maxPersonalizedTexts;
        this.personalizations = new String[maxPersonalizedTexts]; //Creamos el array con tamaño el numero maximo de personalizaciones
        this.num_personalization = 0;
    }

    /**
     * Methods to add personalizations, in will not exceed the maximum
     * @param personalizations
     * @return
     */
    public boolean addPersonalizations(String[] personalizations) {
        //creo que hay que meter un NullPointerException aqui
        if (personalizations.length > this.personalizations.length) {
            return false;
            //Por entrada será siempre un array String[] de tamaño exacto al numero de cambios y con todas las celdas llenas
        } else {
            for (int i = 0; i < personalizations.length; i++) {
                this.personalizations[i] = personalizations[i];
                num_personalization++;
            }
            return true;
        }
    }

    /**
     * [cite_start]Calculates the total price, applying a 10% surcharge per personalized text added[cite: 17].
     * @param quantity The quantity of the product.
     * @return The final total price including surcharges and category discounts.
     */
    @Override
    public float getTotalPrice(int quantity) {
        float total = (float)((basePrice * quantity)+(basePrice*0.1*num_personalization)) ;
        if (discount == true) {
            total *= afterDiscount;
        }
        return total;
    }

    /**
     * Returns the string representation of the product, listing all added custom texts.
     */
    @Override
    public String toString() { //como todavía no sabemos le formato, lo dejo así
        StringBuilder result = new StringBuilder(super.toString()+"\n");
        for (String personalization : personalizations) {
            if (personalization != null) {
                result.append("- "+personalization+"\n");
            }
        }
        return result.toString();
    }
}
