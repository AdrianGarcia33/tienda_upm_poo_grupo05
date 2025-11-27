package es.upm.etsisi.poo.grupo05.resourcespackage.userpackage;

import es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage.Receipt;

public class Cashier extends User {
    public Cashier(String id, String name, String email) {
        super(id, name, email);
    }

//    public String tickets(){
//    StringBuilder tickets = new StringBuilder();
//    tickets.append("Tickets: \n");
//        tickets.append(super.getReceiptMap().toString());
//        tickets.append("cash tickets: ok\n");
//    return  tickets.toString();
//    }
    @Override
    public String toString(){
        return "Cash{identifier='" + super.getId() + "', name='" + super.getName() + "', email='" + super.getEmail() + "'}";
    }
}

