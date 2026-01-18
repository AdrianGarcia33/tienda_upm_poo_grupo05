package es.upm.etsisi.poo.grupo05;

/**
 * Class for unifying all exception messages to make the code less redundant. Said methods will be static, so as to be accesed anywhere.
 */
public class ExceptionHandler {
    private static final String NULL_POINTER_EXCEPTION_MESSAGE = "Error: Inexistent Object";
    private static final String CLASS_CAST_EXCEPTION_MESSAGE = "Error: Incorrect typing of an object";
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "Error: Incorrect type of data";
    private static final String INPUT_MISMATCH_EXCEPTION_MESSAGE = "Error: Incorrect input of data";
    private static final String ARRAY_INDEX_OUT_OF_BOUNDS_MESSAGE = "Error: Issue found while accesing data or reading parameters";
    private static final String FILE_NOT_FOUND_MESSAGE = "Error: Cannot find the input file";
    private static final String EMAIL_UNACCEPTABLE= "ERROR: The email is not acceptable";
    private static final String NOT_INSTANCE_OF_CASHIER_MESSAGE = "Error: No such cashier with this ID";
    private static final String ID_OF_PRODUCTS_EXISTS = "Error: A product of the same id already exits";
    private static final String ID_OF_PRODUCTS_NOT_EXISTS = "Error: The product of said id was not found";
    private static final String NULL_ARGUMENT = "Error: Some data for creating the object is incorrect";
    private static final String DATE_TIME_PARSE_EXCEPTION = "DateTimeParseException (yyyy-MM-dd)";
    private static final String IO_EXCEPTION_MESSAGE = "Error: FileNotFound";
    private static final String TICKET_TYPE_UNACCPTABLE = "ERROR: Unknown ticket type";
    private static final String TICKET_NOT_EXISTS = "Error: The ticket was not found";
    private static final String DATE_IS_NOT_VALID = "Error: The date is not valid";
    private static final String PRICE_CANNOT_BE_NEGATIVE = "Error: Price cannot be negative";
    private static final String LIST_FULL = "Error: The list is already full";
    private static final String NAME_OUT_OF_RANGE_MESSAGE = "Error: The name of the product is invalid";
    private static final String INVALID_ID_MESSAGE = "Error: The product id is invalid";

    //Messages for printing errors about the persistence
    private static final String JSON_PARSE_EXCEPTION = "Error: Unknown type of product";
    private static final String NULL_POINTER_PERSISTENCE = "Error: Empty catalog/services/userlist, can not update correctly";

    public static String getNullPointerExceptionMessage() {
        return NULL_POINTER_EXCEPTION_MESSAGE;
    }

    public static String getClassCastExceptionMessage() {
        return CLASS_CAST_EXCEPTION_MESSAGE;
    }

    public static String getIllegalArgumentExceptionMessage() {
        return ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE;
    }

    public static String getInputMismatchExceptionMessage() {
        return INPUT_MISMATCH_EXCEPTION_MESSAGE;
    }

    public static String getArrayIndexOutOfBoundsMessage() {
        return ARRAY_INDEX_OUT_OF_BOUNDS_MESSAGE;
    }

    public static String getFileNotFoundMessage() {
        return FILE_NOT_FOUND_MESSAGE;
    }

    public static String getIdOfProductsExists() {
        return ID_OF_PRODUCTS_EXISTS;
    }

    public static String getIdOfProductsNotExists() {
        return ID_OF_PRODUCTS_NOT_EXISTS;
    }

    public static String getNullArgument() {
        return NULL_ARGUMENT;
    }

    public static String getNotInstanceOfCashierMessage() {
        return NOT_INSTANCE_OF_CASHIER_MESSAGE;
    }

    public static String getEmailUnacceptable(){ return  EMAIL_UNACCEPTABLE; }

    public static String getDateTimeParseException() {return DATE_TIME_PARSE_EXCEPTION;}

    public static String getIoExceptionMessage() {return IO_EXCEPTION_MESSAGE;}

    public static String getTicketTypeUnacceptable(){ return TICKET_TYPE_UNACCPTABLE; }

    public static String getTicketNotExists() { return TICKET_NOT_EXISTS; }

    public static String getJsonParseException() {
        return JSON_PARSE_EXCEPTION;
    }

    public static String getNullPointerPersistence() {
        return NULL_POINTER_PERSISTENCE;
    }

    public static String getTemporallyInvalidMessage(){return "ERROR: Service in not temporally valid";}

    public static String getProductNotFound(){return "ERROR: The product couldn't be found on the ticket";}

    public static String getTicketNotFromCashier(){ return "ERROR: The ticket doesn't belong to the cashier with provided ID";}

    public static String getDateIsNotValid(){return DATE_IS_NOT_VALID;}

    public static String getPriceCannotBeNegative(){return PRICE_CANNOT_BE_NEGATIVE;}

    public static String getListFull(){return LIST_FULL;}

    public static String getNameOutOfRangeMessage(){return NAME_OUT_OF_RANGE_MESSAGE;}

    public static String getInvalidIdMessage(){return INVALID_ID_MESSAGE;}
}

