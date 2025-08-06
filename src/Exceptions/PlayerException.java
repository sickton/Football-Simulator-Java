package Exceptions;

public class PlayerException extends RuntimeException {
    public PlayerException(String message) {
        super(message);
    }

    public PlayerException()
    {
        this("Invalid player");
    }
}
