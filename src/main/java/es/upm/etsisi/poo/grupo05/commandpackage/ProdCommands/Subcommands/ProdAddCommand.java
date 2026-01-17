// java
package es.upm.etsisi.poo.grupo05.commandpackage.ProdCommands.Subcommands;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class made for prod add command, it detects the correct arguments and adds it to the catalog
 */
public class ProdAddCommand extends Command {
    private ProductMap productMap;

    public ProdAddCommand(String name, ProductMap productmap) {
        super(name);
        this.productMap = productmap;
    }

    @Override
    public boolean apply(String[] args) {
        try {
            int id;
            float price = -1;
            Category category = null;
            int maxPers = 0;

            LocalDate maxDate = null;
            ServiceType serviceType = null;
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            if (args.length == 2){

                String date = args[0];
                String service = args[1];

                if (date != null){
                    maxDate = LocalDate.parse(date, dateFormat);
                }
                if (service != null){
                    serviceType = ServiceType.valueOf(service.toUpperCase());
                }

                ProductService productService = new ProductService(serviceType, maxDate);
                if (productMap.addService(productService)) {
                    System.out.println(productService.toString());
                    System.out.println("prod add: ok");
                }
                return false;
            }


            String line = String.join(" ", args).trim();

            line = line.replaceAll("\"{2,}", "\"");

            Pattern p = Pattern.compile("^(?:\\s*(\\d+)\\s+)?\"([^\"]+)\"\\s+(\\S+)\\s+(\\d+(?:\\.\\d+)?)(?:\\s+(\\d+))?\\s*$");
            Matcher m = p.matcher(line);

            if (!m.find()) {
                // formato no reconocido
                return false;
            }

            String idStr = m.group(1);           // puede ser null
            String name = m.group(2);
            String categoryStr = m.group(3);
            String priceStr = m.group(4);
            String maxPersStr = m.group(5);      // opcional

            if (idStr != null) {
                id = Integer.parseInt(idStr);
            } else {
                id = productMap.generateId();
            }

            if (priceStr != null) {
                price = Float.parseFloat(priceStr);
            }

            if (categoryStr != null) {
                category = Category.valueOf(categoryStr);
            }

            if (maxPersStr != null) {
                maxPers = Integer.parseInt(maxPersStr);
            }
            if (productMap.hasProduct(id)) {
                System.out.println(ExceptionHandler.getIdOfProductsExists());
            } else {
                if ((id > 0) && (price >= 0) && (category != null) && (name != null && !name.isEmpty())) {
                    if (maxPers == 0) {
                        BasicProducts product = new BasicProducts(id, name, price, category, 0);
                        System.out.println(product.toString());
                        productMap.addProduct(product);
                        System.out.println("prod add: ok");
                    } else {
                        PersonalizedProducts product = new PersonalizedProducts(id, name, price, category, 0, maxPers);
                        System.out.println(product.toString());
                        productMap.addProduct(product);
                        System.out.println("prod add: ok");
                    }
                } else {
                    System.out.println(ExceptionHandler.getNullArgument());
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
        } catch (NullPointerException e) {
            System.out.println(ExceptionHandler.getNullPointerExceptionMessage());
        } catch (DateTimeParseException e){
            System.out.println("Date/Time format is incorrect, use yyyy-MM-dd");
        }
        return false;
    }
}
