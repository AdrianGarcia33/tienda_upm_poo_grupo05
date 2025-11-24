package es.upm.etsisi.poo.grupo05.commandpackage;

public class HelpCommand extends Command {

    public HelpCommand(String name) {
        super(name);
    }

    @Override
    public boolean apply(String[] args) {
        StringBuilder menu = new StringBuilder();

        menu.append("Commands:\n");

        menu.append(" Users Management:\n");
        menu.append("   client add \"<name>\" <DNI> <email> <cashId>\n");
        menu.append("   client remove <DNI>\n");
        menu.append("   client list\n");
        menu.append("   cash add [<id>] \"<name>\" <email>\n");
        menu.append("   cash remove <id>\n");
        menu.append("   cash list\n");
        menu.append("   cash tickets <id>\n");

        menu.append(" Product Management:\n");
        menu.append("   prod add [<id>] \"<name>\" <category> <price> [<maxPers>]\n");
        menu.append("   prod addFood [<id>] \"<name>\" <price> <expiration: yyyy-MM-dd> <max_people>\n");
        menu.append("   prod addMeeting [<id>] \"<name>\" <price> <expiration: yyyy-MM-dd> <max_people>\n");
        menu.append("   prod update <id> NAME|CATEGORY|PRICE <value>\n");
        menu.append("   prod remove <id>\n");
        menu.append("   prod list\n");

        menu.append(" Ticket Management:\n");
        menu.append("   ticket new [<id>] <cashId> <userId>\n");
        menu.append("   ticket add <ticketId> <cashId> <prodId> <amount> [--p <txt> ...]\n");
        menu.append("   ticket remove <ticketId> <cashId> <prodId>\n");
        menu.append("   ticket print <ticketId> <cashId>\n");
        menu.append("   ticket list\n");

        menu.append(" General:\n");
        menu.append("   echo \"<text>\"\n");
        menu.append("   help\n");
        menu.append("   exit\n\n");

        menu.append("   Categories: MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS\n");
        menu.append("   Discounts if there are ≥2 units in the category: MERCH 0%, STATIONERY 5%, CLOTHES 7%, BOOK 10%, ELECTRONICS 3%.\n");
        menu.append("\n");

        System.out.print(menu);
        return false;
    }
}