package ru.danil.NauJava.Controller.Exception;

public class ExceptionClass
{
    private String message;

    private ExceptionClass(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public static ExceptionClass create(Throwable e)
    {
        return new ExceptionClass(e.getMessage());
    }

    public static ExceptionClass create(String message)
    {
        return new ExceptionClass(message);
    }
}