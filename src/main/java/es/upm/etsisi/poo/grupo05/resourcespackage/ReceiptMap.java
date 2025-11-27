package es.upm.etsisi.poo.grupo05.resourcespackage;

import es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage.Receipt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ReceiptMap {
    private HashMap<String, Receipt> receiptmap;
    private int num_receipt;

    public ReceiptMap () {
        this.receiptmap = new HashMap<>();
        this.num_receipt = 0;
    }

    public HashMap<String, Receipt> getReceiptmap() {
        return receiptmap;
    }

    public void setReceiptmap(HashMap<String, Receipt> receiptmap) {
        this.receiptmap = receiptmap;
    }

    public boolean newReceipt(Receipt receipt) {
        String id = receipt.getId();
        if (!receiptmap.containsKey(id)) {
            receiptmap.put(id, receipt);
            num_receipt++;
            return true;
        } else {
            return false;
        }
    }

    public boolean contains(String id) {
        return receiptmap.containsKey(id);
    }

    public boolean addItemtoReceipt(String receipt_id, int prod_id, int quantity) {
        if(receiptmap.containsKey(receipt_id)) {
            Receipt receipt = receiptmap.get(receipt_id);
            if (receipt.addItem(prod_id, quantity)) {
                System.out.println(receipt.provisionalPrice());
                return true;
            }
        } else {
            System.out.println("Error: TicketNotFound");
        }
        return false;
    }

    public boolean addPersonalizedItemtoReceipt(String receipt_id, int prod_id, int quantity, String[] personalizations) {
        if(receiptmap.containsKey(receipt_id)) {
            Receipt receipt = receiptmap.get(receipt_id);
            if (receipt.addPersonalizedItem(prod_id, quantity, personalizations)) {
                System.out.println(receipt.provisionalPrice());
                return true;
            }
        } else {
            System.out.println("Error: TicketNotFound");
        }
        return false;
    }

    public boolean removeItemFromReceipt(String receipt_id, int prod_id) {
        if (receiptmap.containsKey(receipt_id)) {
            Receipt receipt = receiptmap.get(receipt_id);
            if (receipt.removeItem(prod_id)) {
                return true;
            }
        } else {
            System.out.println("Error: TicketNotFound");
        }
        return false;
    }

    public String print(String receipt_id) {
        String salida = "";
        if(receiptmap.containsKey(receipt_id)) {
            Receipt receipt = receiptmap.get(receipt_id);
            salida = receipt.print();
        } else {
            System.out.println("Error: TicketNotFound");
        }
        return salida;
    }

    public boolean removeItemsFromAllReceipts(int prod_id) {
        for (Receipt receipt : receiptmap.values()) {
            receipt.removeItem(prod_id);
        }
        return true;
    }


    public String list() {
        ArrayList<Receipt> receiptlist = new ArrayList<>(num_receipt);
        StringBuilder result = new StringBuilder();
        for (Receipt receipt : receiptmap.values()) {
            receiptlist.add(receipt);
        }
        receiptlist.sort(Comparator.comparing(Receipt::getCashId));
        result.append("Ticket List:\n");
        for (int i = 0; i < receiptlist.size(); i++) {
            result.append("\t").append(receiptlist.get(i).getId()).append(" - ").append(receiptlist.get(i).getTicketState()).append("\n");
        }
        return result.toString();
    }

}
