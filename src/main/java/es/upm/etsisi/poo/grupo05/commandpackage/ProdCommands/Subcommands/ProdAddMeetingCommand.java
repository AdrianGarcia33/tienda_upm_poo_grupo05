package es.upm.etsisi.poo.grupo05.commandpackage.ProdCommands.Subcommands;

import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.Events;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.Meeting;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProdAddMeetingCommand extends Command {
    private ProductMap productMap;

    public ProdAddMeetingCommand(String name, ProductMap productmap) {
        super(name);
        this.productMap = productmap;
    }

    @Override
    public boolean apply(String[] args) {
        try {
            String line = String.join(" ", args).trim();

            Pattern p = Pattern.compile("^\\[(\\d+)\\]\\s*\"([^\"]+)\"\\s+(\\d+(?:\\.\\d+)?)\\s+(\\d{4}-\\d{2}-\\d{2})\\s+(\\d+)$");
            Matcher m = p.matcher(line);

            if (!m.find()) {
                return false; // Formato no reconocido
            }

            String idStr = m.group(1);
            String name = m.group(2);
            String priceStr = m.group(3);
            String dateStr = m.group(4);
            String maxPeopleStr = m.group(5);

            int id = Integer.parseInt(idStr);
            float price = Float.parseFloat(priceStr);
            int maxPeople = Integer.parseInt(maxPeopleStr);

            LocalDate expirationDate = LocalDate.parse(dateStr);

            if (productMap.hasProduct(id)) {
                System.out.println(ExceptionHandler.getIdOfProductsExists());
            } else {
                if (price >= 0 && maxPeople > 0 && maxPeople <= Events.getLimitParticipants() && id >= 0) {

                    Meeting meeting = new Meeting(id, name, price, expirationDate, maxPeople);

                    if (meeting.isTemporallyValid()) {
                        productMap.addProduct(meeting);
                    } else {
                        System.out.println("Error: La reunión requiere al menos 12 horas de planificación.");
                    }
                } else {
                    // if (maxPeople > Events.getLimitParticipants()) {
                    //    System.out.println("Error: El número máximo de participantes permitido es " + Events.getLimitParticipants());
                    // } else {
                    System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
                }
            }

        } catch (NumberFormatException e) {
            System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha inválido. Use 'yyyy-MM-dd'.");
        } catch (Exception e) {
            System.out.println(ExceptionHandler.getNullPointerExceptionMessage());
        }

        return false;
    }
}