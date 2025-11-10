package es.upm.etsisi.poo.grupo05.productpackage;

public class PersonalizedProducts extends BasicProducts{
    private int maxPersonalizedTexts;
    private String[] personalizations;
    private int num_personalization;

    //Creo que personalizations se tiene que quedar primero vacio, y en receipt add dependiendo del numero de personalizaciones añadiremos o no

    public PersonalizedProducts(int id, String name, float price, Category category, int quantity, int maxPersonalizedTexts) {
        super(id, name, price, category, quantity);
        this.maxPersonalizedTexts = maxPersonalizedTexts;
        this.personalizations = new String[maxPersonalizedTexts]; //Creamos el array con tamaño el numero maximo de personalizaciones
        this.num_personalization = 0;
    }

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

    @Override
    public String toString() { //como todavía no sabemos le formato, lo dejo así
        StringBuilder result = new StringBuilder(super.toString()+"\n");

        for (String personalization : personalizations) {
            result.append("- "+personalization+"\n");
        }

        return result.toString();
    }
}
