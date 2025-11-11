package es.upm.etsisi.poo.grupo05;

import es.upm.etsisi.poo.grupo05.productpackage.Category;
import es.upm.etsisi.poo.grupo05.productpackage.Product;
import es.upm.etsisi.poo.grupo05.userpackage.Cashier;
import es.upm.etsisi.poo.grupo05.userpackage.Client;
import es.upm.etsisi.poo.grupo05.userpackage.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Handler {
    private ProductMap productMap;
    private UserList userMap;
    private Scanner scanner;


    public Handler(ProductMap productMap, UserList userMap, Scanner scanner) {
        this.productMap = productMap;
        this.userMap = userMap;
        this.scanner = scanner;
    }
    /**
     * Method whose job is to detect commands from an entry string in order to call subsequent functions. While
     * it doesn't detect exit, it will always return false
     *
     * @param line Entry Strig
     */
    public boolean detect(String line) {
        try {
            String parts[] = line.split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            switch (parts[0]) {
                case "client":
                    handleClientCommand(parts);
                    break;
                case "cash":

                    break;
                case "prod":
                    handleProdCommand(parts);
                    break;

                case "ticket":
                    handleTicketCommand(parts);
                    break;

                case "echo":
                    System.out.println("echo " + String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)));
                    System.out.println();
                    break;

                case "help":
                    help();
                    break;
                case "exit":
                    return true;

                default:
                    System.out.println("Unknown command.");
                    break;
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Lack of data or Incorrect command");
        } catch (InputMismatchException e) {
            System.out.println("Error: Error on the data");
        }

        return false;
    }

    /**
     * Secondary method to cleanup and not stack so many switches and cases
     * Handles the commands from the prefix prod
     * @param parts
     */

    private void handleClientCommand(String[] parts) {
        try{
            switch (parts[1]){
                case "add":
                    String nombre = parts[2];
                    String DNI = parts[3];
                    String email = parts[4];
                    Cashier cashier =(Cashier) userMap.getUserMap().get(parts[5]);
                    checkSuccesful(userMap.addUser(new Client(DNI,nombre,email,cashier)), parts);

                    break;
                case "remove":
                    DNI = parts[2];
                    checkSuccesful(userMap.removeUser(userMap.getUserMap().get(DNI)), parts);
                    break;

                case "list":
                    System.out.println(userMap.UserList(true));
                    System.out.println("client list: ok");
                    break;
            }
        } catch (NullPointerException e) {
        } catch (IllegalArgumentException exception) {
            System.out.println("Incorrect Data");
        }
    }
    private void handleCashCommand(String[] parts){
        try{
            switch(parts[1]){
                case "add":
                    if(parts.length==5){
                        String id = parts[2];
                        String name = parts[3];
                        String email = parts[4];
                        checkSuccesful(userMap.addUser(new Cashier(id,name,email)),parts);
                    }else if(parts.length==4){
                        String id = generateId();
                        String name = parts[2];
                        String email = parts[3];
                        checkSuccesful(userMap.addUser(new Cashier(id,name,email)),parts);
                    }
                    break;
                case "remove":
                    String id = parts[2];
                    checkSuccesful(userMap.removeUser(userMap.getUserMap().get(id)), parts);
                    break;
                case "list":
                    System.out.println(userMap.UserList(false));
                    break;
                case "tickets":
                    id=parts[2];
                    Cashier cashier =(Cashier) userMap.getUserMap().get(id);
                    System.out.println(cashier.tickets());
                    break;
            }
        } catch (NullPointerException e) {
        } catch (IllegalArgumentException exception) {
            System.out.println("Incorrect Data");
        }
    }
    private void handleProdCommand(String[] parts) {
        try {
            switch (parts[1]) {

                case "add":
                    //Variables for creating new product
                    int id = Integer.parseInt(parts[2]);
                    String name = parts[3];
                    name= name.replaceAll("\"","");
                    Category category = Category.valueOf(parts[4].toUpperCase());
                    float price =  Float.parseFloat(parts[5]);

                    Product p = new Product(id, name, price, category, 0);

                    checkSuccesful(productMap.addProduct(p), parts);
                    break;

                case "list":
                    System.out.println(productMap.printList());
                    System.out.println("prod list: ok"); //regardless of what, it will print, so always succesful
                    break;

                case "update":
                    id = Integer.parseInt(parts[2]);
                    float originalprice = productMap.getProduct(id).getBasePrice();
                    switch (parts[3].toUpperCase()) {
                        case "NAME":
                            String[] restofString = Arrays.copyOfRange(parts, 4, parts.length);
                            name = String.join(" ", restofString);
                            name = name.replace("\"", "");
                            checkSuccesful(productMap.updateProduct(id, name, originalprice, null ), parts);
                            break;

                        case "PRICE":
                            price = Float.parseFloat(parts[4]);
                            checkSuccesful(productMap.updateProduct(id, null, price, null ), parts);
                            break;

                        case "CATEGORY":
                            category = Category.valueOf(parts[4].toUpperCase());
                            checkSuccesful(productMap.updateProduct(id, null, originalprice, category), parts);
                            break;

                        default:
                            System.out.println("Unknown command");
                            break;
                    }

                    break;

                case "remove":
                    id = Integer.parseInt(parts[2]);
                    System.out.println(productMap.getProduct(id).toString());
                    checkSuccesful(productMap.removeProduct(id, receipt), parts);
                    break;

                default:
                    System.out.println("Unknown command with prod prefix");
                    break;
            }
        } catch (NullPointerException e) {
        } catch (IllegalArgumentException exception) {
            System.out.println("Incorrect Data");
        }


    }

    /** Auxiliary method to handle the commands with ticket prefix
     * @param parts
     */
    private void handleTicketCommand(String[] parts) {
        try {
            switch (parts[1]) {
                case "new":
                    checkSuccesful(receipt.reset(), parts);
                    break;
                case "add":
                    int prodId = Integer.parseInt(parts[2]);
                    int quantity = Integer.parseInt(parts[3]);
                    checkSuccesful(receipt.addItem(prodId, quantity), parts);
                    break;
                case "remove":
                    int ID=  Integer.parseInt(parts[2]);
                    checkSuccesful(receipt.removeItem(ID), parts);
                    break;
                case "print":
                    System.out.print(receipt.print());
                    System.out.println("ticket print: ok"+"\n");
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        } catch (NullPointerException e) {
        } catch (IllegalArgumentException exception) {
            System.out.println("Incorrect data");
        }

    }

    /** Second method to clean up the code, checks if the previous operation was succesful
     * and prints the corresponding message
     * @param check
     * @param parts
     */
    public void checkSuccesful(boolean check, String[] parts) {
        switch (parts[0]) {
            case "prod":
                switch (parts[1]) {
                    case "remove": //in a separate case or else it will say the product doest no exist
                        System.out.println(parts[0]+" "+parts[1]+": ok");
                        System.out.println("");
                        break;
                    default:
                        int id = Integer.parseInt(parts[2]);
                        if (check) {
                            System.out.println(productMap.getProduct(id).toString());
                            System.out.println(parts[0]+" "+parts[1]+": ok");
                            System.out.println("");
                        }
                        break;
                }
                break;
            case "ticket":
                if (check) {
                    System.out.print(receipt.print());
                    System.out.println(parts[0]+" "+parts[1]+": ok");
                    System.out.println("");
                }
                break;

        }

    }

    /**
     * Prints on screen a menu with the possible commands
     */
    public static void help () {
        StringBuilder menu = new StringBuilder();

        menu.append("Commands:\n");
        menu.append("   prod add <id> \"<name>\" <category> <price>\n");
        menu.append("   prod list\n");
        menu.append("   prod update <id> NAME|CATEGORY|PRICE <value>\n");
        menu.append("   prod remove <id>\n");
        menu.append("   ticket new\n");
        menu.append("   ticket add <prodId> <quantity>\n");
        menu.append("   ticket remove <prodId>\n");
        menu.append("   ticket print\n");
        menu.append("   echo <texto>\n");
        menu.append("   help\n");
        menu.append("   exit\n\n");
        menu.append("   Categories: MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS\n");
        menu.append("   Discounts if there are ≥2 units in the category: MERCH 0%, STATIONERY 5%, CLOTHES 7%, BOOK 10%, ELECTRONICS 3%.\n");
        menu.append("\n");

        System.out.print(menu);
    }
    private String generateId() {
        // This method must be called when no cashId is provided when called the command "cash add"
        //if id==null llamamos a este metodo, llamamos hsta que el id random no esté en el hashmap
        StringBuilder id= new StringBuilder();
        boolean contiene = true;
        while(contiene) {
            StringBuilder aux = new StringBuilder("UW");
            for (int i = 0; i < 7; i++) {
                aux.append((int) (Math.random()));
            }
            if(!userMap.getUserMap().containsKey(aux.toString())){
                id=aux;
                contiene = false;
            }
        }

        return String.valueOf(id);
    }

}
