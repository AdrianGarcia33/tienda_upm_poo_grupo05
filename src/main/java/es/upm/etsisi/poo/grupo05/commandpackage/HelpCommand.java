package es.upm.etsisi.poo.grupo05.commandpackage;


public class HelpCommand extends Command{
    public HelpCommand(String name) {
        super(name);
    }

    @Override
    public boolean apply(String[] args) {
        StringBuilder menu = new StringBuilder();
        //Hay que actualizar el help
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
        return false;
    }
}
