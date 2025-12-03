package es.upm.etsisi.poo.grupo05.resourcespackage.userpackage;

import es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage.Receipt;

/**
 * Represents a cashier user in the system.
 */
public class Cashier extends User {

    /**
     * Constructs a new Cashier.
     * @param id Unique identifier.
     * @param name Cashier's name.
     * @param email Cashier's email.
     */
    public Cashier(String id, String name, String email) {
        super(id, name, email);
    }


    /**
     * Returns the string representation of the cashier.
     */
    @Override
    public String toString(){
        return "Cash{identifier='" + super.getId() + "', name='" + super.getName() + "', email='" + super.getEmail() + "'}";
    }
}

