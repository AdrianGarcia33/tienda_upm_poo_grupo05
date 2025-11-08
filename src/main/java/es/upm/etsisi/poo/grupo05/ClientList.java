package es.upm.etsisi.poo.grupo05;
import java.util.HashMap;

public class ClientList{
    private HashMap<String, Client> clientMap;

    public ClientList(){
        clientMap = new HashMap<>();
    }

    public boolean addClient(Client client){
        boolean resultado = false;
        if(client!=null){
            if(!clientMap.containsKey(client.getId())){
                clientMap.put(client.getId(), client);
                resultado = true;
            }else{
                System.out.println("Error: Client ("+client.getId()+") already added");
            }
        }else{
            System.out.println("Error: Client is null");
        }
        return resultado;
    }
    public boolean removeClient(Client client){
        boolean resultado = false;
        if(client!=null){
            if(clientMap.containsKey(client.getId())){
                clientMap.remove(client.getId());
                resultado = true;
            }else{
                System.out.println("Error: Client ("+client.getId()+") not added");
            }
        }else{
            System.out.println("Error: Client is null");
        }
        return resultado;
    }
    public String clientList(){
        StringBuilder list = new StringBuilder();
        list.append("Client List:\n");
        for(Client c : clientMap.values()){
            list.append(c.toString()+"\n");
        }
        return list.toString();
    }

}
