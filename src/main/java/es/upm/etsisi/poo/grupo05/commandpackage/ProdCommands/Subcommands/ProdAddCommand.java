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


    //El metodo como tal
    @Override
    public boolean apply(String[] args) {//solo nos queda los datos que necesitamos
        try {
            String line = String.join(" ", args).trim();
            Pattern p = Pattern.compile("^\\[(\\d+)\\]\\s*\"([^\"]+)\"\\s+(\\S+)\\s+(\\d+(?:\\.\\d+)?)\\s*(?:\\[(\\d+)\\])?$");
            Matcher m = p.matcher(line);

            if (!m.find()) {
                return false; // formato no reconocido
            }

            String idStr = m.group(1);
            String name = m.group(2);
            String categoryStr = m.group(3);
            String priceStr = m.group(4);
            String maxPersStr = m.group(5); //Este es opcional

            int id = -1;
            float price = -1;
            Category category = null;
            int maxPers = 0;

            if (idStr != null) {
                id = Integer.parseInt(idStr);
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
                if ((id != -1) && (price != -1) && (category != null)) {
                    if (maxPers == 0) { //Producto sin personalizaciones
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
