package es.upm.etsisi.poo.grupo05;

/**
 * Hello world!
 */
public class App {

    public static void help(){
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

    public static void main(String[] args) {
        System.out.println("Hola que tal");

    }
}
