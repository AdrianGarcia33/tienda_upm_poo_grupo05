package es.upm.etsisi.poo.grupo05;

public class ProductList {
    private Product[] productlist = null;
    private int number_products;
    private int max_products;

    //Builder
    public ProductList (int max_products){
        this.max_products = max_products;
        productlist = new Product[max_products];
        int number_products = 0;
    }

    //Getters y Setters por default
    public Product[] getProductlist() {
        return productlist;
    }

    public void setProductlist(Product[] productlist) {
        this.productlist = productlist;
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
     * Metodo que devuelve el producto
     * @param id
     * @return devuelve el producto tras utilizar el id como identificador
     */
    public Product getProduct (int id) {
        if (productlist[id] != null) {
            return productlist[id];

        } else {
            System.out.println("Error: el producto no existe");
            return null;
        }
    }


    /**
     * Es la función encargada de añadir un producto a nuestro diccionario
     * @param product
     * @return
     */
    public boolean addProduct (Product product) { // No se si es mejor meter un objeto de tipo producto (Y utilizar el constructor de la clase)
        // O meter id, cantidad etc. como parametros en este metodo y crear el objeto en aqui
        boolean resultado = false;

        if (product != null) {
            productlist[product.getID()] = product;
            resultado = true;
            System.out.println("Producto añadido correctamente");
        } else {
            System.out.println("Error: el producto no existe");
        }
        number_products++;
        return resultado;

    }

    public boolean removeProduct (int id) {
        boolean resultado = false;
        if (id < max_products && productlist[id] != null) {
            productlist[id] = null;
            resultado = true;
        } else {
            System.out.println("Error: el producto no existe");
        }
        number_products--;
        return resultado;
    }

    public boolean updateProduct(int id, String name, float price, Category category, int value) {
        boolean resultado = false;
        if (id < max_products && productlist[id] != null) {
            Product product = productlist[id];
            product.setName(name);
            product.setPrice(price);
            product.setCategory(category);
            product.setQuantity(value);
            resultado = true;
        } else {
            System.out.println("Error: el producto no existe");
        }
        return resultado;
    }

    public String printList() {
        StringBuilder catalog = new StringBuilder();
        catalog.append("Catalog: \n");
        for (int i = 0; i < max_products; i++) {
            if (productlist[i] != null) {
                catalog.append(productlist[i].toString()+"\n");
            }
        }
        return catalog.toString();
    }








}
