package com.javarush.test.level22.lesson13.task02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/* Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
        String firstFileName = args[0];
        String secondFileName = args[1];
        //String firstFileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
        //String secondFileName = "C:\\Users\\Sid927\\Desktop\\222.txt";

        Charset win = Charset.forName("Windows-1251");
        Charset utf = Charset.forName("UTF-8");

        FileInputStream  fileWin = new FileInputStream(firstFileName);
        FileOutputStream fileUTF = new FileOutputStream(secondFileName);

        byte[]buffer = new byte[fileWin.available()];
        fileWin.read(buffer);

        String s = new String(buffer,utf);
        buffer = s.getBytes(win);
        fileUTF.write(buffer);

        fileWin.close();
        fileUTF.close();

    }
}
