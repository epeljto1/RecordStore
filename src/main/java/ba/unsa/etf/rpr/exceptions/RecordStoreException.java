package ba.unsa.etf.rpr.exceptions;

public class RecordStoreException extends Exception {
    public RecordStoreException(String message) {
        super(message);
    }

    public RecordStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
