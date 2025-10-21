package es.upm.etsisi.poo.grupo05;
import java.util.*;

/**
 * Class made for storing all the data from our repertoire of products.
 */
public class ProductList {
    private HashMap<Integer, Product> productMap;
    private int number_products;
    private int max_products;

    //Builder
    public ProductList (int max_products){
        this.max_products = max_products;
        productMap = new HashMap<>();
        int number_products = 0;
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

    /**
     * As the name suggests, it returns a Product given a certain id.
     * @param id An integer which identifies an object in the list
     * @return
     */
    public Product getProduct (int id) {
        Product p = productMap.get(id);
        if (p != null) {
            return p;
        } else {
            System.out.println("Error: el producto no existe");
            return null;
        }
    }


    /**
     * Method made to add a product into our catalog
     * @param product
     * @return
     */
    public boolean addProduct (Product product) {
        boolean resultado = false;
        boolean problem = false;
        if (product != null) {
        int id = product.getID();
        if (id <= 0) {
            System.out.println("Error: product ID is out of range");
            problem = true;
        }
        if(product.getName().isEmpty() || product.getName().length()>100){
            System.out.println("Error: product NAME is out of range");
            problem = true;
        }
        if(product.getPrice()<0){
            System.out.println("Error: product PRICE is out of range");
            problem = true;
        }
        if (number_products >= max_products) {
            System.out.println("Error: product list is full");
            problem = true;
        }
        if(problem){return resultado;}

            if (productMap.containsKey(id)) {
                System.out.println("This type of product already exists, please try to use the update product command ");
            } else {
                productMap.put(id, product);
                resultado = true;
                number_products++;
            }
        } else {
            System.out.println("Error: this product does not exist");
        }

        return resultado;

    }

    /**
     * Method to remove said product from said id
     * @param id
     * @param receipt We do this, so if we remove something from the catalog, it also dissapears from the current receipt
     * @return
     */
    public boolean removeProduct (int id, Receipt receipt) {
        boolean resultado = false;

        if (productMap.get(id) != null) {
            productMap.remove(id);
            resultado = true;
            number_products--;
            if (receipt != null) {
                receipt.removeItem(id); // Remove from receipt as well
            }
            number_products--;
        } else {
            System.out.println("Error: this product does not exist");
        }

        return resultado;
    }

    /**
     * Method made to update the data of a certain product
     * @param id
     * @param name
     * @param price
     * @param category
     * @return
     */
    public boolean updateProduct(int id, String name, float price, Category category) {
        boolean resultado = false;

            if (productMap.get(id) != null) {
                Product p = productMap.get(id);

                if (name != null) {
                    p.setName(name);
                }

                if (price < 0) {
                    System.out.println("Error: product price cannot be negative");
                } else {
                    p.setPrice(price);
                }

                if (category != null) {
                    p.setCategory(category);
                }
                resultado = true;

            } else {
                System.out.println("Error: product does not exist");
            }

        return resultado;
    }

    /**
     * Method made to give a visual representation of our catalog
     * @return
     */
    public String printList() {
        StringBuilder catalog = new StringBuilder();
        catalog.append("Catalog: \n");
        for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
            catalog.append(" "+entry.getValue().toString()+"\n");
            /* Slight Explanation
            Entry = set of <key, value>. Here, value is our objects of the class Product.
            when one does entry.getvalue, it tkaes that. Entryset is all pairs of <key, value> of our hash
            */
        }

        int lastLine = catalog.lastIndexOf("\n");
        if (lastLine != -1) {
            catalog.delete(lastLine, catalog.length());
        }
        return catalog.toString();
    }








}
