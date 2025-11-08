package es.upm.etsisi.poo.grupo05;

import java.util.ArrayList;

public class Client extends User{
    private String creatorId;

    public Client (String id, String name, String email,String creatorId) {
        super(id, name, email);
        this.creatorId = creatorId;
    }
    public String getCreator() {
        return creatorId;
    }
    public void setCreator(String creatorId) {
        this.creatorId = creatorId;
    }

    public String toString(){
        return "DNI: "+super.getId()+", Name: "+super.getName()+", Email: "+super.getEmail()+ ", Registered by: "+ creatorId;
    }

}
