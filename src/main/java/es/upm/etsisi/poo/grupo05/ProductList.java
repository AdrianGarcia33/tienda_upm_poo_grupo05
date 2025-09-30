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
    boolean addProduct (Product product) {
        boolean resultado = false;

        if (product != null) {
            productlist[product.getID()] = product;
            resultado = true;
            System.out.println("Producto añadido correctamente");
        } else {
            System.out.println("Error: el producto no existe");
        }

        return resultado;

    }








}
