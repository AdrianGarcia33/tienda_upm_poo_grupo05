package es.upm.etsisi.poo.grupo05;

/**
 * Class for unifying all exception messages to make the code less redundant. Said methods will be static, so as to be accesed anywhere.
 */
public class ExceptionHandler {
    private static final String NULL_POINTER_EXCEPTION_MESSAGE = "Error: Inexistent Object";
    private static final String CLASS_CAST_EXCEPTION_MESSAGE = "Error: Incorrect typing of an object";
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "Error: Incorrect type of data";
    private static final String INPUT_MISMATCH_EXCEPTION_MESSAGE = "Error: Incorrect input of data";
    private static final String ARRAY_INDEX_OUT_OF_BOUNDS_MESSAGE = "Error: Issue found while accesing data";
    private static final String FILE_NOT_FOUND_MESSAGE = "Error: Cannot find the input file";
    private static final String EMAIL_UNACCEPTABLE= "ERROR: The email is not acceptable";
    private static final String NOT_INSTANCE_OF_CASHIER_MESSAGE = "Error: No such cashier with this ID";
    private static final String ID_OF_PRODUCTS_EXISTS = "Error: A product of the same id already exits";
    private static final String ID_OF_PRODUCTS_NOT_EXISTS = "Error: The product of said id was not found";
    private static final String NULL_ARGUMENT = "Error: Some data for creating the object is incorrect";
    private static final String DATE_TIME_PARSE_EXCEPTION = "DateTimeParseException (yyyy-MM-dd)";
    private static final String IO_EXCEPTION_MESSAGE = "Error: FileNotFound";
    private static final String TICKET_TYPE_UNACCPTABLE = "ERROR: Unknown ticket type";

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
}

