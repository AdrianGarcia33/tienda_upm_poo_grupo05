package es.upm.etsisi.poo.grupo05.resourcespackage.productpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
     * Returns the string representation of the product, listing all added custom texts.
     */
    @Override
    public String toString() { //como todavía no sabemos le formato, lo dejo así
        StringBuilder result = new StringBuilder("{class:PersonalizedProduct, id:"+id+", name:'"+name+"', category:"+category+", price:"+basePrice+", maxPersonal:"+maxPersonalizedTexts);
        if (num_personalization != 0) {
            result.append(", personalizationList:[");

            List<String> StringPersonalizations = new ArrayList<>();
            for (String p : personalizations) {
                if (p != null) {
                    StringPersonalizations.add(p);
                }
            }
            result.append(String.join(", ", StringPersonalizations));

            result.append("]");
        }
        result.append("}");
        if(discount) {
            result.append(" **discount-").append(String.format(Locale.US,"%.3f", basePrice * (1 - afterDiscount)));
        }

        return result.toString();
    }
}
