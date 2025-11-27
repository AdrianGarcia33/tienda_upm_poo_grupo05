package es.upm.etsisi.poo.grupo05.commandpackage.ProdCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.Events;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.Lunch;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ProdAddFoodCommand extends Command {
    private ProductMap productMap;

    public ProdAddFoodCommand(String name, ProductMap productmap) {
        super(name);
        this.productMap = productmap;
    }

    @Override
    public boolean apply(String[] args) {
        String line = String.join(" ", args).trim();
        String[] parts = line.split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        try {
            int id;
            String name;
            float price;
            LocalDate expirationDate;
            int maxPeople;
            boolean idProvided = false;

            if (parts.length == 5) {
                id = Integer.parseInt(parts[0]);
                name = parts[1].replace("\"", "");
                price = Float.parseFloat(parts[2]);
                expirationDate = LocalDate.parse(parts[3]);
                maxPeople = Integer.parseInt(parts[4]);

                if (productMap.hasProduct(id)) {
                    System.out.println(ExceptionHandler.getIdOfProductsExists());
                    return false;
                }

            }
            else if (parts.length == 4) {
                id = productMap.generateId();
                name = parts[0].replace("\"", "");
                price = Float.parseFloat(parts[1]);
                expirationDate = LocalDate.parse(parts[2]);
                maxPeople = Integer.parseInt(parts[3]);

            } else {
                return false;
            }

            if (price >= 0 && maxPeople > 0 && maxPeople <= Events.getLimitParticipants() && id >= 0) {

                Lunch food = new Lunch(id, name, price, expirationDate, maxPeople);

                if (food.isTemporallyValid()) {
                    productMap.addProduct(food);
                    System.out.println("prod addFood: ok");
                } else {
                    System.out.println("Error: La comida debe planificarse con al menos 3 días de antelación.");
                }
            } else {
                if (maxPeople > Events.getLimitParticipants()) {
                    System.out.println("Error: El número máximo de participantes permitido es " + Events.getLimitParticipants());
                } else {
                    System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
                }
            }

        } catch (NumberFormatException e) {
            System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha inválido (use yyyy-MM-dd).");
        } catch (Exception e) {
            System.out.println(ExceptionHandler.getNullPointerExceptionMessage());
        }

        return false;
    }
}