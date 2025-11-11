package es.upm.etsisi.poo.grupo05;

import es.upm.etsisi.poo.grupo05.receiptpackage.Receipt;
import es.upm.etsisi.poo.grupo05.receiptpackage.TicketState;

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

    public boolean addItemtoReceipt(String receipt_id, int prod_id, int quantity) {
        if(receiptmap.containsKey(receipt_id)) {
            Receipt receipt = receiptmap.get(receipt_id);
            if (receipt.addItem(prod_id, quantity)) {
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
            receipt.setTicketState(TicketState.CLOSED);
            salida = receipt.toString();
        } else {
            System.out.println("Error: TicketNotFound");
        }
        return salida;
    }


    public String list() {
        ArrayList<Receipt> receiptlist = new ArrayList<>(num_receipt);
        StringBuilder result = new StringBuilder();
        for (Receipt receipt : receiptmap.values()) {
            receiptlist.add(receipt);
        }
        receiptlist.sort(Comparator.comparing(Receipt::getCashId));
        for (int i = 0; i < receiptlist.size(); i++) {
            result.append(receiptlist.get(i).getId()+"\n");
        }
        return result.toString();
    }

}
