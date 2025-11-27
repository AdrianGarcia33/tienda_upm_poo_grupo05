package es.upm.etsisi.poo.grupo05.resourcespackage.userpackage;

/**
 * Represents a client user in the system.
 */
public class Client extends User{
    private Cashier creator;

    /**
     * Constructs a new Client.
     * @param id Unique identifier (DNI).
     * @param name Client's name.
     * @param email Client's email.
     * @param creator The Cashier who registered the client.
     */
    public Client (String id, String name, String email,Cashier creator) {
        super(id, name, email);
        this.creator = creator;
    }

    /**
     * Gets the cashier who created this client.
     */
    public Cashier getCreator() {
        return creator;
    }

    /**
     * Returns the string representation of the client.
     */
    @Override
    public String toString(){
        return "Client{identifier='"+super.getId()+"', name='"+super.getName()+"', email='"+super.getEmail()+"', cash="+creator.getId()+"}";
    }

}

