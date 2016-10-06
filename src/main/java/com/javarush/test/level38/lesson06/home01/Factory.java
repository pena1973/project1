package com.javarush.test.level38.lesson06.home01;

public class Factory
{
    public static Throwable getException(Enum e)
    {
        String message = "";
        if (e == null) return new IllegalArgumentException();

        message = e.name().replaceAll("\\_", " ").toLowerCase();
        message = message.substring(0, 1).toUpperCase() + message.substring(1, message.length());

        if (e.getDeclaringClass() == ExceptionApplicationMessage.class)
            return new Exception(message);
        else if (e.getDeclaringClass() == ExceptionDBMessage.class)
            return new RuntimeException(message);
        else if (e.getDeclaringClass() == ExceptionUserMessage.class)
            return new Error(message);
        else return new IllegalArgumentException();
    }
}
