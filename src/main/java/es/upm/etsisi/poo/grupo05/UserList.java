package es.upm.etsisi.poo.grupo05;
import es.upm.etsisi.poo.grupo05.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.userpackage.Client;
import es.upm.etsisi.poo.grupo05.userpackage.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class UserList {
    private HashMap<String, User> UserMap;

    public UserList(){
        UserMap = new HashMap<>();
    }

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
            System.out.println("Error: Client is null");
        }
        return resultado;
    }

    public String UserList(boolean client){
        // Creo que tendriamos que hacer dos clases derivadas, una con cashList y otra con ClientList pero asi tampoco está mal
        //solo que me parece raro como he tenido que implementar este mtodo
        StringBuilder list = new StringBuilder();
        List<User> users = new ArrayList<>(UserMap.values());
        users.sort(Comparator.comparing(User::getName));
        //Añadir todos los elementos
        if(client){
            list.append("Client List:\n");
            for(User u : users){
                if(u instanceof Client) list.append(u.toString()+"\n");
            }
        }else {
            list.append("Cashier List:\n");
            for (User u : users) {
                if (u instanceof Cashier) list.append(u.toString() + "\n");
            }
        }
        return list.toString();
    }

    public HashMap<String, User> getUserMap() {
        return UserMap;
    }

}
