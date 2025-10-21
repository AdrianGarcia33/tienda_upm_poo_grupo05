package es.upm.etsisi.poo.grupo05;

import java.util.HashMap;

public class UserList {
    private HashMap<String, User> userMap;
    private int numCahier;
    private int numClients;
    public boolean addUser(User user) {
        if(user!=null) {
            if (user.getCashier()) {
                //if is cashier

            } else {
                //if is client

            }
        }
    }
}
