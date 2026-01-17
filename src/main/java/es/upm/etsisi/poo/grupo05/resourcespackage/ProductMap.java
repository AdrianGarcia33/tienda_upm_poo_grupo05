package es.upm.etsisi.poo.grupo05.resourcespackage;

import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.BasicProducts;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.Category;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.Product;
import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.ProductService;

import java.util.*;

import static es.upm.etsisi.poo.grupo05.ExceptionHandler.*;

/**
 * Class made for storing all the data from our repertoire of products.
 */
public class ProductMap {
    private HashMap<Integer, Product> productMap;
    private HashMap<Integer, ProductService> serviceMap;
    private int number_products;
    private int max_products;

    //Builder
    public ProductMap(int max_products) {
        this.max_products = max_products;
        productMap = new HashMap<>();
        serviceMap = new HashMap<>();
        this.number_products = 0;
    }

    //Getters and Setters
    public HashMap<Integer, Product> getproductMap() {
        return productMap;
    }

    public void setProductMap(HashMap<Integer, Product> productMap) {
        this.productMap = productMap;
    }

    public int getNumber_products() {
        return number_products;
    }

    public void setNumber_products(int number_products) {
        this.number_products = number_products;
    }

    public int getMax_products() {
        return max_products;
    }

    public void setMax_products(int max_products) {
        this.max_products = max_products;
    }

    public HashMap<Integer, Product> getProductMap() {
        return this.productMap;
    }

    public HashMap<Integer, ProductService> getServiceMap() {
        return serviceMap;
    }

    public void setServiceMap(HashMap<Integer, ProductService> serviceMap) {
        this.serviceMap = serviceMap;
    }

    /**
     * As the name suggests, it returns a Product given a certain id.
     *
     * @param id An integer which identifies an object in the list
     * @return
     */
    public Product getProduct(int id) {
        Product p = productMap.get(id);
        if (p != null) {
            return p;
        } else {
            System.out.println(getNullPointerExceptionMessage());
            return null;
        }
    }

    /**
     * As the name suggests, it returns a ProductService given a certain id.
     *
     * @param id An integer which identifies an object in the list
     * @return
     */
    public ProductService getService(int id) {
        ProductService p = serviceMap.get(id);
        if (p != null) {
            return p;
        } else {
            System.out.println(getNullPointerExceptionMessage());
            return null;
        }

    }

    /**
     * Checks if a certain product in on the list
     *
     * @param id An integer which identifies an object in the list
     * @return
     */
    public boolean hasProduct(int id) {
        return productMap.containsKey(id);
    }

    /**
     * Checks if a certain productService in on the list
     *
     * @param id An integer which identifies an object in the list
     * @return
     */
    public boolean hasService(int id) {
        return serviceMap.containsKey(id);
    }


    /**
     * Method made to add a product into our catalog
     *
     * @param product Product to add
     * @return
     */
    public boolean addProduct(Product product) {
        boolean resultado = false;
        boolean problem = false;
        if (product != null) {
            int id = product.getId();

            if (id <= 0) {
                System.out.println(getInvalidIdMessage());
                problem = true;
            }
            if (product.getName().isEmpty() || product.getName().length() > 100) {
                System.out.println(getNameOutOfRangeMessage());
                problem = true;
            }
            if (product.getBasePrice() < 0) {
                System.out.println(getPriceCannotBeNegative());
                problem = true;
            }
            if (number_products >= max_products) {
                System.out.println(getListFull());
                problem = true;
            }
            if (problem) {
                return resultado;
            }

            if (productMap.containsKey(id)) {
                System.out.println(getIdOfProductsExists());
            } else {
                productMap.put(id, product);
                resultado = true;
                number_products++;
            }
        } else {
            System.out.println(getNullPointerExceptionMessage());
        }

        return resultado;

    }

    /**
     * Method made to add a productService into our catalog
     *
     * @param product ProductService to add
     * @return
     */
    public boolean addService(ProductService product) {
        boolean resultado = false;
        if (product != null) {
            if (product.isTemporallyValid()) {
                serviceMap.put(product.getId(), product);
                resultado = true;
            } else System.out.println(getDateIsNotValid());
        } else System.out.println(getNullPointerExceptionMessage());
        return resultado;
    }

    /**
     * Method to remove said product from said id
     *
     * @param id An integer which identifies an object in the list
     * @return
     */
    public boolean removeProduct(int id) {
        boolean resultado = false;

        if (productMap.get(id) != null) {
            productMap.remove(id);
            resultado = true;
            number_products--;
        } else {
            System.out.println(getNullPointerExceptionMessage());
        }

        return resultado;
    }

    /**
     * Method made to update the data of a certain product
     *
     * @param id
     * @param name
     * @param price
     * @param category
     * @return
     */
    public boolean updateProduct(int id, String name, float price, Category category) {
        boolean resultado = false;

        if (productMap.get(id) != null) {
            BasicProducts p = (BasicProducts) productMap.get(id);

            if (name != null) {
                p.setName(name);
            }

            if (price < 0) {
                System.out.println(getPriceCannotBeNegative());
            } else {
                p.setBasePrice(price);
            }

            if (category != null) {
                p.setCategory(category);
            }
            resultado = true;

        } else {
            System.out.println(getNullPointerExceptionMessage());
        }

        return resultado;
    }

    /**
     * Method made to give a visual representation of our catalog
     *
     * @return
     */
    public String printList() {
        StringBuilder catalog = new StringBuilder();
        catalog.append("Catalog: ");
        for (Product product : productMap.values()) {
            if (product != null) {
                catalog.append(" " + product.toString() + "\n");
            }
        }
        for (ProductService service : serviceMap.values()) {
            if (service != null) {
                catalog.append(" " + service.toString() + "\n");
            }
        }

        int lastLine = catalog.lastIndexOf("\n");
        if (lastLine != -1) {
            catalog.delete(lastLine, catalog.length());
        }
        return catalog.toString();
    }

    /**
     * Generates an ID random that does not exist yet.
     * We use 6 digits as standard
     */
    public int generateId() {
        int newId;
        do {
            newId = (int) (Math.random() * 100000) + 1;
        } while (this.hasProduct(newId));
        return newId;
    }


}
