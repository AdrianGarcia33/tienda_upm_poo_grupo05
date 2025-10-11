package es.upm.etsisi.poo.grupo05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 */
public class App {
    private static ProductList productList;
    private static Receipt receipt;
    private static Scanner scanner;


    /**
     * Method whose job is to detect commands from an entry string in order to call subsequent functions. While
     * it doesn't detect exit, it will always return false
     *
     * @param line Entry Strig
     */
    public static boolean detect(String line) {
        try {
            String parts[] = line.split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
//            for(int i=0;i<parts.length;i++) {
//                System.out.println(parts[i]);
//            }
            switch (parts[0]) {
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
    private static void handleProdCommand(String[] parts) {
        try {
            switch (parts[1]) {

                case "add":
                    //Variables for creating new product
                    int id = Integer.parseInt(parts[2]);
//                    String line = String.join(" ", parts);
                    String name = parts[3];
                    name= name.replaceAll("\"","");
                    Category category = Category.valueOf(parts[4].toUpperCase());
                    float price =  Float.parseFloat(parts[5]);
//                    Matcher matcher = Pattern.compile("\"([^\"]+)\"").matcher(line);
//                    String name = "";
//                    int nameEnd = -1;
//                    if (matcher.find()) {
//                        name = matcher.group(1);
//                        nameEnd = matcher.end();
//                    } else {
//                        name = parts[3].replace("\"", "");
//                        nameEnd = line.indexOf(parts[3]) + parts[3].length();
//                    }
//
//                    String rest = line.substring(nameEnd).trim();
//                    String[] restParts = rest.split(" ");
//                    Category category = Category.valueOf(restParts[0].toUpperCase());
//                    float price = Float.parseFloat(restParts[1]);
                    Product p = new Product(id, name, price, category, 0);

                    checkSuccesful(productList.addProduct(p), parts);
                    break;

                case "list":
                    System.out.println(productList.printList());
                    System.out.println("prod list: ok"); //regardless of what, it will print, so always succesful
                    break;

                case "update":
                    id = Integer.parseInt(parts[2]);
                    float originalprice = productList.getProduct(id).getPrice();
                    switch (parts[3].toUpperCase()) {
                        case "NAME":
                            String[] restofString = Arrays.copyOfRange(parts, 4, parts.length);
                            name = String.join(" ", restofString);
                            name = name.replace("\"", "");
                            checkSuccesful(productList.updateProduct(id, name, originalprice, null ), parts);
                            break;

                        case "PRICE":
                            price = Float.parseFloat(parts[4]);
                            checkSuccesful(productList.updateProduct(id, null, price, null ), parts);
                            break;

                        case "CATEGORY":
                            category = Category.valueOf(parts[4].toUpperCase());
                            checkSuccesful(productList.updateProduct(id, null, originalprice, category), parts);
                            break;

                        default:
                            System.out.println("Unknown command");
                            break;
                    }

                    break;

                case "remove":
                    id = Integer.parseInt(parts[2]);
                    System.out.println(productList.getProduct(id).toString());
                    checkSuccesful(productList.removeProduct(id, receipt), parts);
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
    private static void handleTicketCommand(String[] parts) {
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
    public static void checkSuccesful(boolean check, String[] parts) {
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
                            System.out.println(productList.getProduct(id).toString());
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


    /**
     * Runs the app, and if a file is provided as argument, reads commands from it
     * @param args
     */
        public static void main (String[] args) {
            int max_products = 200 ;
            productList = new ProductList(max_products);
            receipt = new Receipt(productList);
            try {
                boolean imprimir_comando = false;
                if (args.length > 0) {
                    scanner = new Scanner(new File(args[0]));
                    imprimir_comando = true;
                } else {
                    scanner = new Scanner(System.in);
                }

                System.out.println("Welcome to the ticket module App");
                System.out.println("Ticket module. Type 'help' to see commands.");
                boolean stop = false;

                while (!stop) {
                    System.out.print("tUPM>");
                    String line = scanner.nextLine();
                    if (imprimir_comando) {
                        System.out.println(line);
                    }
                    if (detect(line)) {
                        stop = true;
                    }
                }

                System.out.println("App Closed, have a nice day ");
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }
}



