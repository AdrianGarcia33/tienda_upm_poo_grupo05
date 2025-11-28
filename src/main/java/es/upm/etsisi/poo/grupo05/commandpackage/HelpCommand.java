package es.upm.etsisi.poo.grupo05.commandpackage;

/**
 * Class made for printing out and options menu for the user
 */
public class HelpCommand extends Command {

    public HelpCommand(String name) {
        super(name);
    }

    @Override
    public boolean apply(String[] args) {
        StringBuilder menu = new StringBuilder();

        menu.append("Commands:\n");
        // Clientes
        menu.append("  client add \"<nombre>\" <DNI> <email> <cashId>\n");
        menu.append("  client remove <DNI>\n");
        menu.append("  client list\n");

        // Cajeros
        menu.append("  cash add [<id>] \"<nombre>\"<email>\n");
        menu.append("  cash remove <id>\n");
        menu.append("  cash list\n");
        menu.append("  cash tickets <id>\n");

        // Tickets
        menu.append("  ticket new [<id>] <cashId> <userId>\n");
        menu.append("  ticket add <ticketId><cashId> <prodId> <amount> [--p<txt> --p<txt>]\n");
        menu.append("  ticket remove <ticketId><cashId> <prodId>\n");
        menu.append("  ticket print <ticketId> <cashId>\n");
        menu.append("  ticket list\n");

        // Productos
        menu.append("  prod add <id> \"<name>\" <category> <price>\n");
        menu.append("  prod update <id> NAME|CATEGORY|PRICE <value>\n");
        menu.append("  prod addFood [<id>] \"<name>\" <price> <expiration:yyyy-MM-dd> <max_people>\n");
        menu.append("  prod addMeeting [<id>] \"<name>\" <price> <expiration:yyyy-MM-dd> <max_people>\n");
        menu.append("  prod list\n");
        menu.append("  prod remove <id>\n");

        // General
        menu.append("  help\n");
        menu.append("  echo \"<text>\"\n");
        menu.append("  exit\n\n");

        // Footer
        menu.append("Categories: MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS\n");
        menu.append("Discounts if there are ≥2 units in the category: MERCH 0%, STATIONERY 5%, CLOTHES 7%, BOOK 10%, ELECTRONICS 3%.\n");
        menu.append("\n");

        System.out.print(menu);
        return false;
    }
}