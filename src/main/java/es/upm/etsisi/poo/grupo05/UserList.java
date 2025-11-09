package es.upm.etsisi.poo.grupo05;
import java.util.HashMap;

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
        if(client){
            list.append("Client List:\n");
            for(User u : UserMap.values()){
                if(!u.getId().contains("UW")) list.append(u.toString()+"\n");
            }
        }else {
            list.append("Cashier List:\n");
            for (User u : UserMap.values()) {
                if (u.getId().contains("UW")) list.append(u.toString() + "\n");
            }
        }
        return String.valueOf(list);
    }

    public HashMap<String, User> getUserMap() {
        return UserMap;
    }

}
