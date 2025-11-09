package es.upm.etsisi.poo.grupo05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Client extends User{
    private Cashier creator;

    public Client (String id, String name, String email,Cashier creator) {
        super(id, name, email);
        this.creator = creator;
    }
    public Cashier getCreator() {
        return creator;
    }


    public void addReceipt(Receipt newreceipt){
        LinkedList<Receipt> receipts = this.getReceipts();
        receipts.add(newreceipt);
        this.setReceipts(receipts);
    }
    public String toString(){
        return "DNI: "+super.getId()+", Name: "+super.getName()+", Email: "+super.getEmail()+ ", Registered by: "+ creator.getId();
    }

}
