package com.javarush.test.level33.lesson15.big01.strategies;
import com.javarush.test.level33.lesson15.big01.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket
{
    private Path path;

    public FileBucket()
    {
        try
        {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
        }
        catch (IOException e) {}
        path.toFile().deleteOnExit();
    }


    public long getFileSize()
    {
        return path.toFile().length();
    }

    public void putEntry(Entry entry) {
        try (ObjectOutputStream s = new ObjectOutputStream(Files.newOutputStream(path))) {
                s.writeObject(entry);
        }
        catch (Exception e)
        {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry() {
        if (getFileSize() == 0) return null;

        try (ObjectInputStream s = new ObjectInputStream(Files.newInputStream(path)))
        {
            return (Entry) s.readObject();
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
                return null;
            }
        }

    public void remove(){
        try {
            Files.delete(path);
        }
        catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }
}
