package es.upm.etsisi.poo.grupo05;

import java.util.*;

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
            String parts[] = line.split(" ");
            switch (parts[0]) {
                case "prod":
                    handleProdCommand(parts);
                    break;

                case "ticket":
                    switch (parts[1]) {
                        case "new":
                            // Handle ticket new
                            break;
                        case "add":
                            // Handle ticket add
                            break;
                        case "remove":
                            // Handle ticket remove
                            break;
                        case "print":
                            // Handle ticket print
                            break;
                        default:
                            System.out.println("Unknown command");
                            break;
                    }
                    break;

                case "echo":
                    System.out.println("echo " + String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)));
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
                    String name = parts[3].replace("\"", "");
                    float price = Float.parseFloat(parts[5]);
                    Category category = Category.valueOf(parts[4].toUpperCase());

                    Product p = new Product(id, name, price, category, 0);
                    checkSuccesful(productList.addProduct(p), parts);
                    break;

                case "list":
                    System.out.println(productList.printList());
                    break;

                case "update":
                    id = Integer.parseInt(parts[2]);
                    float originalprice = productList.getProduct(id).getPrice();
                    switch (parts[3].toUpperCase()) {
                        case "NAME":
                            name = parts[4];
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
                    checkSuccesful(productList.removeProduct(id), parts);
                    break;

                default:
                    System.out.println("Unknown command with prod prefix");
                    break;
            }
        } catch (NullPointerException e) {
        } catch (IllegalArgumentException exception) {
            System.out.println("Error a la hora de introducir los datos");
        }

    }

    private static void handleTicketCommand(String[] parts) {

    }

    public static void checkSuccesful(boolean check, String[] parts) {
        switch (parts[0]) {
            case "prod":
                if (check) {
                    System.out.println(productList.printList());
                    System.out.println(parts[0]+" "+parts[1]+": ok");
                }
                break;

            case "ticket":
                if (check) {
                    System.out.println(receipt.print());
                    System.out.println(parts[0]+" "+parts[1]+": ok");
                }

        }

    }

    /**
     * Prints on screen a menu with the possible commands
     */
        public static void help () {
            StringBuilder menu = new StringBuilder();

            menu.append("Commands:\n");
            menu.append("   prod add <id> <name> <category> <price>\n");
            menu.append("   prod list\n");
            menu.append("   prod update <id> NAME|CATEGORY|PRICE <value>\n");
            menu.append("   prod remove <id>\n");
            menu.append("   ticket new\n");
            menu.append("   ticket add <prodId> <quantity>\n");
            menu.append("   ticket remove <prodId>\n");
            menu.append("   ticket print\n");
            menu.append("   echo <texto>");
            menu.append("   help\n");
            menu.append("   exit\n\n");
            menu.append("   Categories: MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS\n");
            menu.append("   Discounts if there are ≥2 units in the category: MERCH 0%, STATIONERY 5%, CLOTHES 7%, BOOK 10%, ELECTRONICS 3%.\n");

            System.out.print(menu);
        }




        public static void main (String[] args) {
            int max_products = 200 ;
            receipt = new Receipt();
            productList = new ProductList(max_products);
            scanner = new Scanner(System.in);

            System.out.println("Welcome to the ticket module App");
            System.out.println("Ticket module. Type 'help' to see commands.");
            boolean stop = false;

            while (!stop) {
                System.out.print("tUPM> ");
                String line = scanner.nextLine();
                if (detect(line)) {
                    stop = true;
                }
            }

            System.out.println("App Closed, have a nice day ");

        }
}



