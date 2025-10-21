package es.upm.etsisi.poo.grupo05;

public class User {
    private int id;
    private boolean cashier;
    private String email;
    private String Name;


    public User(int id, boolean cashier, String email, String Name) {
        this.id = id;
        this.cashier = cashier;
        this.email = email;
        this.Name = Name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getCashier() {
        return cashier;
    }

    public void setCashier(boolean cashier) {
        this.cashier = cashier;
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

}
