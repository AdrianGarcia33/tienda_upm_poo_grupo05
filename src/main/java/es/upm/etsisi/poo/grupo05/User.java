package es.upm.etsisi.poo.grupo05;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class User {
    private String id;
    private String email;
    private String Name;
    private LinkedList<Receipt> receipts;

    public User(String id, String email, String Name) {
        this.id = id;
        this.email = email;
        this.Name = Name;
        this.receipts = new LinkedList<>();
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

    public LinkedList<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(LinkedList<Receipt> receipts) {
        this.receipts = receipts;
    }

    public abstract String toString();
}
