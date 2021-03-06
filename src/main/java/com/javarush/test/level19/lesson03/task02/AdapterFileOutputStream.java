package com.javarush.test.level19.lesson03.task02;

/* Адаптер
Используйте класс AdapterFileOutputStream, чтобы адаптировать FileOutputStream к новому интерфейсу AmigoStringWriter
*/

import java.io.FileOutputStream;
import java.io.IOException;

public class AdapterFileOutputStream implements AmigoStringWriter{
    FileOutputStream stream;

    public AdapterFileOutputStream(FileOutputStream stream)
    {
        this.stream = stream;
    }

    @Override
    public void flush() throws IOException
    {
        this.stream.flush();
    }

    @Override
    public void writeString(String s) throws IOException
    {
        this.stream.write(s.getBytes());
    }

    @Override
    public void close() throws IOException
    {
        this.stream.close();
    }
}

