package es.upm.etsisi.poo.grupo05.resourcespackage.userpackage;

import es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage.Receipt;

public class Cashier extends User {
    public Cashier(String id, String name, String email) {
        super(id, name, email);
    }

    public String tickets(){
    StringBuilder tickets = new StringBuilder();
    tickets.append(super.getId()+"'s tickets: \n");
        for(Receipt r : super.getReceiptMap().values()){
            tickets.append(r.toString()+"\n");
        }
    return  tickets.toString();
    }
    @Override
    public String toString(){
        return "Worker ID: "+super.getId()+", Name: "+super.getName()+", Email: "+super.getEmail();
    }
}

