// java
package es.upm.etsisi.poo.grupo05.commandpackage.ProdCommands.Subcommands;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import es.upm.etsisi.poo.grupo05.commandpackage.Command;
import es.upm.etsisi.poo.grupo05.ExceptionHandler;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.BasicProducts;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.Category;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.PersonalizedProducts;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.Product;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProdAddCommand extends Command {
    private ProductMap productMap;

    public ProdAddCommand(String name, ProductMap productmap) {
        super(name);
        this.productMap = productmap;
    }

    @Override
    public boolean apply(String[] args) {
        try {
            String line = String.join(" ", args).trim();
            // Colapsar comillas duplicadas \"\"... -> \"
            line = line.replaceAll("\"{2,}", "\"");

            // id opcional al inicio, nombre entre " ", categoria, precio, maxPers opcional entre [ ]
            Pattern p = Pattern.compile("^(?:\\s*(\\d+)\\s+)?\"([^\"]+)\"\\s+(\\S+)\\s+(\\d+(?:\\.\\d+)?)(?:\\s*\\[(\\d+)\\])?\\s*$");
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

            int id;
            float price = -1;
            Category category = null;
            int maxPers = 0;

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
                        productMap.addProduct(product);
                    } else {
                        PersonalizedProducts product = new PersonalizedProducts(id, name, price, category, 0, maxPers);
                        productMap.addProduct(product);
                    }
                } else {
                    System.out.println(ExceptionHandler.getNullArgument());
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(ExceptionHandler.getIllegalArgumentExceptionMessage());
        } catch (NullPointerException e) {
            System.out.println(ExceptionHandler.getNullPointerExceptionMessage());
        }
        return false;
    }
}
