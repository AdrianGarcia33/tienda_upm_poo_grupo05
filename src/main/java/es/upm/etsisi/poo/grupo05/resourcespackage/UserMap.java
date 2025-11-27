package es.upm.etsisi.poo.grupo05.resourcespackage;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.Client;
import es.upm.etsisi.poo.grupo05.resourcespackage.userpackage.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class UserMap {
    /**
     * Class responsible for managing the storage and operations of system users.
     * It stores both Clients and Cashiers in a common data structure (HashMap).
     */
    private HashMap<String, User> UserMap;


    /**
     * Constructor of the class. Initializes the empty user map.
     */
    public UserMap(){
        UserMap = new HashMap<>();
    }

    /**
     * Adds a new user (Client or Cashier) to the system.
     * Checks that the user is not null and that a user with the same ID does not already exist.
     *
     * @param user The user object to add.
     * @return true if the user was successfully added, false otherwise.
     */
    public boolean addUser(User user){
        boolean resultado = false;
        if(user!=null){
            if(!UserMap.containsKey(user.getId())){
                UserMap.put(user.getId(), user);
                resultado = true;
            }else{
                System.out.println("Error: User ("+user.getId()+") already added");
            }
        }else{
            System.out.println("Error: User is null");
        }
        return resultado;
    }

    /**
     * Removes an existing user from the system.
     *
     * @param user The user object to remove.
     * @return true if the user was successfully removed, false if the user did not exist or was null.
     */
    public boolean removeUser(User user){
        boolean resultado = false;
        if(user!=null){
            if(UserMap.containsKey(user.getId())){
                UserMap.remove(user.getId());
                resultado = true;
            }else{
                System.out.println("Error: Client ("+user.getId()+") not added");
            }
        }else{
            System.out.println("Error: User does not exisit");
        }
        return resultado;
    }

    /**
     * Generates a list of users filtered by type (Client or Cashier) and sorted alphabetically by name.
     *
     * @param client boolean indicating the type of user to list: true for Clients, false for Cashiers.
     * @return A formatted String containing the list of the requested users.
     */
    public String UserList(boolean client){
        // Creo que tendriamos que hacer dos clases derivadas, una con cashList y otra con ClientList pero asi tampoco está mal
        //solo que me parece raro como he tenido que implementar este mtodo
        StringBuilder list = new StringBuilder();
        List<User> users = new ArrayList<>(UserMap.values());
        users.sort(Comparator.comparing(User::getName));
        //Añadir todos los elementos
        if(client){
            list.append("Client:\n");
            for(User u : users){
                if(u instanceof Client) list.append(u.toString()+"\n");
            }
            list.append("client list: ok\n");
        }else {
            list.append("Cash:\n");
            for (User u : users) {
                if (u instanceof Cashier) list.append(u.toString() + "\n");
            }
            list.append("cash list: ok\n");
        }

        return list.toString();
    }

    /**
     * Retrieves the complete map of stored users.
     *
     * @return The HashMap containing all registered users.
     */
    public HashMap<String, User> getUserMap() {
        return UserMap;
    }

}
