package Exceptions;

public class OutOfTableException extends  Exception{

    public OutOfTableException(String message) {
        super("OutOfTableException : "+ message);
    }
}
