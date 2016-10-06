package com.javarush.test.level32.lesson04.home01;

import java.io.*;

/* Читаем из потока
Реализуйте логику метода getAllDataFromInputStream. Он должен вернуть StringWriter, содержащий все данные из переданного потока.
Возвращаемый объект ни при каких условиях не должен быть null.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("C:\\Users\\Sid927\\Desktop\\тестовая\\1\\1231332\\55.txt"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {

        StringWriter stringWriter = new StringWriter();
        if (is == null) return stringWriter;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        char[] cbuf = new char[1];
        while (reader.read(cbuf)>0){
            stringWriter.write(cbuf);
        }
        return stringWriter;

    }
}
