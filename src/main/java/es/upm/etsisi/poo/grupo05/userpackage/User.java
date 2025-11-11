package es.upm.etsisi.poo.grupo05.userpackage;

import es.upm.etsisi.poo.grupo05.receiptpackage.Receipt;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class User {
    private String id;
    private String email;
    private String Name;
    private HashMap<String,Receipt> receipts;

    public User(String id, String email, String Name) {
        this.id = id;
        this.email = email;
        this.Name = Name;
        this.receipts = new HashMap<>();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public HashMap<String,Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(HashMap<String, Receipt> receipts) {
        this.receipts = receipts;
    }

    public abstract String toString();

    public boolean addReceipt(Receipt receipt) {
        boolean result = false;
        if(receipts!=null){
            if(receipts.containsKey(receipt.getId())){
                System.out.println("Error: receipt already added");
            }else{
                result=true;
                receipts.put(receipt.getId(), receipt);
            }
        }
        return result;
    }
}
