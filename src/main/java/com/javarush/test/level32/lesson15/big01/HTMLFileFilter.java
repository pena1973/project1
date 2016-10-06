package com.javarush.test.level32.lesson15.big01;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter
{
    @Override
    public boolean accept(File f)
    {
        if (f.isDirectory()) return true;

        String nameFile = f.getName().toLowerCase();
        if (nameFile.indexOf(".htm") != -1)
            return true;
        else return false;
    }

    @Override
    public String getDescription()
    {
        return "HTML и HTM файлы";
    }

}
