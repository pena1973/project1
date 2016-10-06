package com.javarush.test.level22.lesson09.task01;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример, "мор"-"ром", "трос"-"сорт"
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException
    {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstFileName = reader.readLine();
        //String firstFileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
        BufferedReader filereader =  new BufferedReader(new FileReader(firstFileName));
        String s;
        Map<String,String> map = new HashMap<String,String>(); // храню набор слов чтоб не повторятся

        while ((s = filereader.readLine()) != null)
        {
            String[] arrs = s.split("\\s");

            for (int i = 0; i < arrs.length; i++){
                // проверка на слово перевертыш
            String str = arrs[i]; // получили слово
            StringBuilder builder = new StringBuilder(str); //использовала StringBuilder
            String revers = builder.reverse().toString(); // получили реверс

            if (arrs[i].length()>1 && !str.equals(revers))
                map.put(arrs[i],"");
            }
        }

        for (Map.Entry<String,String> m : map.entrySet())
        {
            if (m.getValue().equals("")) // если строка еще не обработана - ей реверс не сопоставлен
            {
                String str = m.getKey(); // получили слово
                StringBuilder builder = new StringBuilder(str); //использовала StringBuilder
                String revers = builder.reverse().toString(); // получили реверс
                if (map.get(revers) != null)
                {
                    if (map.get(revers).equals("")) // если реверсу не сопоставлено - сопрставим

                        map.put(revers, str);
                }
            }
        }
        // Записываем сопоставленные в result

        for (Map.Entry<String,String> m : map.entrySet())
        {
            if (m.getValue()!="") // если слово сопоставлено заносим в результат
            {
                Pair pair = new Pair();
                pair.first = m.getKey();
                pair.second = m.getValue();

                result.add(pair);
                System.out.println(result.get(result.size()-1));
            }
        }
        filereader.close();
        reader.close();

    }

    public static class Pair {
        String first;
        String second;

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
