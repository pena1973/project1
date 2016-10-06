package com.javarush.test.level18.lesson10.home08;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Не забудьте закрыть все потоки
*/

public class Solution
{
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException
    {
        ArrayList<Thread> list = new ArrayList<Thread>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //fileName = "C:\Users\Sid927\Desktop\111.txt";
        String fileName = reader.readLine();

        while (!fileName.equals("exit"))
        {
            list.add(new ReadThread(fileName));
            list.get(list.size() - 1).start();
            fileName = reader.readLine();
        }
        reader.close();
    }

    public static class ReadThread extends Thread
    {
        public ReadThread(String fileName) throws FileNotFoundException
        {
            this.setName(fileName);
        }

        // implement file reading here - реализуйте чтение из файла тут
        @Override
        public void run()
        {
            try
            {
                FileInputStream file = new FileInputStream(this.getName());

                HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
                int max = 0;
                Integer maxByte = null;
                Integer b;
                Integer count;


                // выстаскиваем данные в карту - для подсчета,   в массив - для сортировки

                while (file.available() > 0)
                {
                    b = file.read();
                    count = map.get(b);
                    if (count == null) count = 0;

                    map.put(b, ++count);
                    // запоминаю наибольший байт
                    max = Math.max(max, count);
                    if (max == count)
                    {
                        maxByte = b;
                    }
                }
                // Записываю байт в мап
                resultMap.put(this.getName(), maxByte);
                file.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }


        }
    }
}
