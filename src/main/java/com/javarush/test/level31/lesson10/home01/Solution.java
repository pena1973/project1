package com.javarush.test.level31.lesson10.home01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Properties;

/* Читаем конфиги
Реализовать метод getProperties, который должен считывать свойства из переданного файла fileName.
fileName может иметь любое расширение - как xml, так и любое другое, или вообще не иметь.
Нужно обеспечить корректное чтение свойств.
При возникновении ошибок должен возвращаться пустой объект.
Метод main не участвует в тестировании.
Подсказка: возможно, Вам понадобится File.separator.
*/
public class Solution
{
    public static void main(String[] args)
    {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("src/com/javarush/test/level31/lesson10/home01/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/test/level31/lesson10/home01/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/test/level31/lesson10/home01/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName)
    {
        Path path = Paths.get(fileName).toAbsolutePath();
//        String fileSeparator = File.separator;
//        Iterator<Path> iter = path.iterator();
//        String resultPath = path.getRoot().toString();
//        while (iter.hasNext()) {
//            resultPath += fileSeparator + iter.next();
//        }

        File file = new File(path.toString());
        // определяюсь с расширением
        String ext = "";
        String name = file.getName();
        int i = name.lastIndexOf('.');
        if (i != -1)
        {
            ext = name.substring(i + 1, name.length());
        }

        // разбираю пропертис
        if (ext.equals("txt"))
        {
            try
            {
                //создаем объект Properties и загружаем в него данные из файла.
                Properties properties = new Properties();
                properties.load(new FileReader(file));
                return properties;
            }
            catch (IOException e)
            {
                return new Properties();
            }
        }
        else if (ext.equals("xml"))
        {
            try
            {
                //создаем объект Properties и загружаем в него данные из файла.
                Properties properties = new Properties();
                properties.loadFromXML(new FileInputStream(file));
                return properties;
            }
            catch (IOException e)
            {
                return new Properties();
            }

        } else
        {
            try
            {
                //создаем объект Properties и загружаем в него данные из файла.
                Properties properties = new Properties();
                properties.load(new FileReader(file));
                return properties;
            }
            catch (IOException e)
            {
                return new Properties();
            }

        }

    }
}
