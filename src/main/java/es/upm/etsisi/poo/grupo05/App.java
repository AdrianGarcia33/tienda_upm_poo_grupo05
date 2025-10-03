package es.upm.etsisi.poo.grupo05;

import java.util.*;

/**
 * Hello world!
 */
public class App {


    /**
     * Method whose job is to detect commands from an entry string in order to call subsequent functions. While
     * it doesn't detect exit, it will always return false
     *
     * @param line Entry Strig
     */
    public static boolean detect(String line) {
        String parts[] = line.split(" ");
        switch (parts[0]) {
            case "prod":
                switch (parts[1]) {
                    case "add":

                        break;
                    case "list":

                        break;
                    case "update":

                        break;
                    case "remove":

                        break;
                    default:
                        System.out.println("Unknown command with prod prefix");
                        break;
                }
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

                break;
            case "help":
                help();
                break;
            case "exit":
                return true;

            default:
                System.out.println("Comando no reconocido.");
                break;
        }
        return false;
    }

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
            Receipt receipt = new Receipt();
            ProductList productList = new ProductList(max_products);
            Scanner scanner = new Scanner(System.in);

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

            System.out.println("App Closed, ");

        }
}



