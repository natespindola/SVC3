package app.model.exception;

public class StringSizeException extends Exception
{
    public StringSizeException()
    {
        super("String is larger than allowed");
    }
}
