package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.*;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public static void main(String[] args) throws Exception
    {
        Solution solution = new Solution();
        solution.fillInPropertiesMap();
    }

    public void fillInPropertiesMap() throws Exception
    {
        //implement this method - реализуйте этот метод
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = br.readLine();
        //String fileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
        br.close();

        FileInputStream inputStream = new FileInputStream(fileName);

        //В методе fillInPropertiesMap считайте имя файла с консоли
        // и заполните карту properties данными из файла.
        // сделали
        Properties propertiesMap = new Properties();
        propertiesMap.load(inputStream);
        for (Map.Entry<Object, Object> prMap :propertiesMap.entrySet()){
            properties.put(prMap.getKey().toString(),prMap.getValue().toString());
        }
        inputStream.close();

//        // сохранили в файл
//        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Sid927\\Desktop\\111.properties");
//        save(outputStream);
//        outputStream.close();
//        // читаю из файла в пропертис
//        FileInputStream inputStream1 = new FileInputStream("C:\\Users\\Sid927\\Desktop\\111.properties");
//        load(inputStream1);
//        inputStream.close();
    }

    public void save(OutputStream outputStream) throws Exception {
       // implement this method - реализуйте этот метод

        Properties pr = new Properties();
        pr.putAll(properties);
        pr.store(outputStream, "commente");
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties pr = new Properties();
        pr.load(inputStream);

        for(Map.Entry<Object, Object> pair : pr.entrySet())
        {
            properties.put(String.valueOf(pair.getKey()), String.valueOf(pair.getValue()));
        }
    }
}
