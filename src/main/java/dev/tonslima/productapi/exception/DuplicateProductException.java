package dev.tonslima.productapi.exception;

public class DuplicateProductException extends Throwable {
    public DuplicateProductException(String message) {
        super(message);
    }
}
