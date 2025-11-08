package es.upm.etsisi.poo.grupo05;

import java.util.ArrayList;

public class Cashier extends User {
    public Cashier(String id, String name, String email) {
        super(id, name, email);
    }

    private String generateId() { // este metodo deberá ir en la clase donde se cree el cashier, si id es null se llama al metodo
        StringBuilder id = new StringBuilder("UW");
        boolean contiene = true;
        for (int i = 0; i < 7; i++) {
            id.append((int) (Math.random()));
        }
        return String.valueOf(id);

    }
    public String tickets(){
    StringBuilder tickets = new StringBuilder();
    tickets.append(super.getId()+"'s tickets: \n");
        for(Receipt r : super.getReceipts()){
            tickets.append(r.toString()+"\n");
        }
    return  String.valueOf(tickets);
    }
    public String toString(){
        return "Worker ID: "+super.getId()+", Name: "+super.getName()+", Email: "+super.getEmail();
    }
}

