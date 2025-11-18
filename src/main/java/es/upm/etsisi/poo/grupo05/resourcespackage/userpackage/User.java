package es.upm.etsisi.poo.grupo05.resourcespackage.userpackage;

import es.upm.etsisi.poo.grupo05.resourcespackage.ReceiptMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage.Receipt;

import java.util.HashMap;

public abstract class User {
    private String id;
    private String email;
    private String name;
    private ReceiptMap receiptMap;

    public User(String id, String name, String email) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.receiptMap = new ReceiptMap();
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReceiptMap getReceiptMap() {
        return receiptMap;
    }

    public void setReceipts(ReceiptMap receiptMap) {
        this.receiptMap = receiptMap;
    }

    public abstract String toString();

    public boolean addReceipt(Receipt receipt) {
        boolean result = false;
        if(receiptMap!=null){
            if(receiptMap.contains(id)){
                System.out.println("Error: receipt already added");
            }else{
                result=true;
                receiptMap.newReceipt(receipt);
            }
        }
        return result;
    }
}
