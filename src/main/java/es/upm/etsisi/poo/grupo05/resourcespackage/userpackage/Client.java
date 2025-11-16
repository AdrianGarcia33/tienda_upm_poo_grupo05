package es.upm.etsisi.poo.grupo05.resourcespackage.userpackage;

public class Client extends User{
    private Cashier creator;

    public Client (String id, String name, String email,Cashier creator) {
        super(id, name, email);
        this.creator = creator;
    }
    public Cashier getCreator() {
        return creator;
    }

    @Override
    public String toString(){
        return "DNI: "+super.getId()+", Name: "+super.getName()+", Email: "+super.getEmail()+ ", Registered by: "+ creator.getId();
    }

}
