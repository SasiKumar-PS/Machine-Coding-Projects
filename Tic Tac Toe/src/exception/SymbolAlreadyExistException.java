package exception;

public class SymbolAlreadyExistException extends RuntimeException {
    public SymbolAlreadyExistException(String message) {
        super(message);
    }
}
