package es.upm.etsisi.poo.grupo05.resourcespackage.receiptpackage;

import es.upm.etsisi.poo.grupo05.resourcespackage.productpackage.*;
import es.upm.etsisi.poo.grupo05.resourcespackage.ProductMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Representa un ticket de compra parametrizado.
 * Mantiene todas las funciones auxiliares de gestión de ID, NIF y descuentos.
 */
public class Receipt<T extends TicketElement> {
    private String id;
    private final LocalDateTime openingDate;
    private LocalDateTime closingDate;
    private TicketState ticketState;

    private final String cashId;
    private final String clientId;

    private final TicketType type;

    private List<T> ticket;
    private int numberItems;
    private int max_items;
    private ProductMap productMap;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm");
    private static final Random RANDOM = new Random();
    private static final Set<String> idsGenerated = new HashSet<>();

    public Receipt(String id, String cashId, String clientId, ProductMap productMap, TicketType type) {
        this.openingDate = LocalDateTime.now();
        if (id == null) {
            this.id = generateId(openingDate);
        } else {
            if (!idsGenerated.add(id)) {
                throw new IllegalArgumentException("The receipt ID is already in use");
            }
            this.id = id;
        }

        this.cashId = cashId;
        this.clientId = clientId;
        this.productMap = productMap;
        this.type = type;

        if (checkNIF(clientId) && type != TicketType.PRODUCT) {
            throw new IllegalArgumentException("Error: Non-company clients cannot create Service or Combined tickets.");
        }

        this.ticket = new LinkedList<>();
        this.numberItems = 0;
        this.max_items = 100;
        this.ticketState = TicketState.EMPTY;

    }

    public String getCashId() {
        return cashId;
    }

    public String getId() { return id; }
    public TicketState getTicketState() { return ticketState; }
    public List<T> getTicketItems() { return ticket; }
    public String getClientId() { return clientId; }
    public TicketType getTicketType() { return type; }

    public T getProduct(int id) {
        for (T p : ticket) {
            if (p.getId() == id) return p;
        }
        return null;
    }
    // --- MÉTODOS DE GESTIÓN DE ITEMS ---

    /**
     * Añade un producto al ticket manteniendo la lógica original de copias y tipos.
     */
    public boolean addProduct(T product, int quantity, String[] personalizations) {
        if (product == null || ticketState == TicketState.CLOSED) return false;

        if (this.type == TicketType.PRODUCT && product instanceof ProductService) {
            System.out.println("Error: Cannot add Service to a PRODUCT-only ticket.");
            return false;
        }

        if (this.type == TicketType.SERVICE && product instanceof Product) {
            System.out.println("Error: Cannot add Product to a SERVICE-only ticket.");
            return false;
        }

        boolean result = false;
        boolean added = false;

        // Caso 1: Es un Producto (Básico, Personalizado o Evento)
        if (product instanceof Product) {

                //Si es un producto basico
                if ((numberItems + quantity) <= max_items) {
                    // Comprobar si ya existe para actualizar cantidad (BasicProducts)
                    for (T p : ticket) {
                        if (p.getId() == product.getId()) {
                            if (p instanceof BasicProducts basicProducts && !(p instanceof PersonalizedProducts)) {
                                basicProducts.setQuantity(basicProducts.getQuantity() + quantity);
                                numberItems += quantity;
                                added = true;
                                result = true;
                            } else if (p instanceof Lunch || p instanceof Meeting) {
                                throw new IllegalArgumentException("Event already exists");
                            }
                        }
                    }

                    if (!added) {
                        T copy = null;
                        if (product instanceof PersonalizedProducts pProd) {
                            PersonalizedProducts pCopy = new PersonalizedProducts(pProd.getId(), pProd.getName(),
                                    pProd.getBasePrice(), pProd.getCategory(), quantity, personalizations.length);
                            if (pCopy.addPersonalizations(personalizations)) {
                                pCopy.setBasePrice(pCopy.getBasePrice() * (1 + 0.1f * personalizations.length));
                                copy = (T) pCopy;
                                numberItems += quantity;
                            }
                        } else if (product instanceof BasicProducts bProd) {
                            BasicProducts bCopy = new BasicProducts(bProd);
                            bCopy.setQuantity(quantity);
                            copy = (T) bCopy;
                            numberItems += quantity;
                        } else if (product instanceof Lunch lunch) {
                            Lunch lCopy = new Lunch(lunch);
                            lCopy.setActualParticipants(quantity);
                            copy = (T) lCopy;
                            numberItems += 1;
                        } else if (product instanceof Meeting meeting) {
                            Meeting mCopy = new Meeting(meeting);
                            mCopy.setActualParticipants(quantity);
                            copy = (T) mCopy;
                            numberItems += 1;
                        }

                        if (copy != null) {
                            for(int i=0; i<quantity; i++) ticket.add(copy);
                            result = true;
                        }
                    }
                } else {
                    System.out.println("Error: you have reached the maximum number of items");
                }
            // Caso 2: Es un Servicio
        } else if (product instanceof ProductService service) {
            if (service.isTemporallyValid()) {
                ticket.add(product);
                numberItems += 1;
                result = true;
            }
        }

        if (result) {
            checkDiscount();
            this.ticketState = TicketState.ACTIVE;
        }
        return result;
    }

    public boolean removeItem(int id) {
        boolean result = false;
        Iterator<T> it = ticket.iterator();
        while (it.hasNext() && !result) {
            T p = it.next();
            if (p.getId() == id) {
                if (p instanceof BasicProducts) {
                    BasicProducts bp = (BasicProducts) p;
                    numberItems = numberItems - bp.getQuantity();
                } else {
                    numberItems = numberItems - 1;
                }
                ticket.remove(p);
                result = true;
            }
        }
        checkDiscount();
        if (ticket.isEmpty()) this.ticketState = TicketState.EMPTY;
        return result;
    }

    public boolean reset() {
        this.ticket = new LinkedList<>();
        this.numberItems = 0;
        this.ticketState = TicketState.EMPTY;
        return true;
    }

    // --- FUNCIONES AUXILIARES DE LÓGICA ---

    private void checkDiscount() {
        for (Category category : Category.values()) {
            int count = 0;
            for (T p : ticket) {
                if (p instanceof BasicProducts bp && bp.getCategory() == category) {
                    count += bp.getQuantity();
                }
            }
            boolean applyDiscount = count > 1;
            for (T p : ticket) {
                if (p instanceof BasicProducts bp && bp.getCategory() == category) {
                    bp.setDiscount(applyDiscount);
                }
            }
        }
    }

    private void closeTicket() {
        checkTicketClosed();
        closingDate = LocalDateTime.now();
        ticketState = TicketState.CLOSED;

        String oldId = this.id;
        String sufix = "-" + closingDate.format(FORMATTER);
        this.id = oldId + sufix;

        idsGenerated.remove(oldId);
        idsGenerated.add(this.id);
    }

    private boolean checkNIF(String nif) {
        if (nif == null || nif.length() != 9) return false;
        for (int i = 0; i < 8; i++) {
            if (!Character.isDigit(nif.charAt(i))) return false;
        }
        return Character.isLetter(nif.charAt(8));
    }

    public boolean isCompanyClient() {
        return checkNIF(this.clientId);
    }

    private static String generateId(LocalDateTime openingDate) {
        String prefix = openingDate.format(FORMATTER) + "-";
        String fullId;
        do {
            int rand = 10000 + RANDOM.nextInt(90000);
            fullId = prefix + rand;
        } while (!idsGenerated.add(fullId));
        return fullId;
    }

    private void checkTicketClosed() {
        if (ticketState == TicketState.CLOSED) {
            throw new IllegalArgumentException("Error: This ticket is already closed");
        }
    }

    // --- GESTIÓN DE IMPRESIÓN (Inyección de Dependencia) ---

    /**
     * Cierra el ticket e inyecta el comportamiento de impresión.
     */
    public String print(ReceiptPrinter<T> printer) {
        if (ticketState == TicketState.ACTIVE || ticketState == TicketState.EMPTY) {
            //Hay que actualizar el id en el gestor
            // Un ticket combinado si no tiene productos no puede cerrarse
            if (this.type == TicketType.COMBINED) {
                boolean hasProduct = false;
                boolean hasService = false;
                for (T item : ticket) {
                    if (item instanceof Product) hasProduct = true;
                    if (item instanceof ProductService) hasService = true;
                }

                if (!hasProduct || !hasService) {
                    return "Error: Combined tickets cannot be closed without at least one Product and one Service.";
                }
            }
            closeTicket();
        }
        return printer.format(this);
    }

    /**
     * Genera un precio provisional sin cerrar el ticket.
     */
    public String provisionalPrice(ReceiptPrinter<T> printer) {
        // comprobar que printer usar
        return printer.format(this);
    }
}