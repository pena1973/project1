package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = br.readLine();
        //String fileName1 = "C:\\Users\\Sid927\\Desktop\\333.html";
        br.close();

        String tag = args[0];
        //String tag = "span";
        String inTag = "<" + tag;
        String outTag = "</" + tag + ">";
        int lenghtInTag = ("<" + tag).length();
        int lenghtOutTag = ("</" + tag + ">").length();

        BufferedReader reader = new BufferedReader(new FileReader(fileName1));

        String[] lines = new String[10];// здесь храню строки которые собираю
        //Инициализация
        for (int i = 0; i < 10; i++) lines[i] = "";

        String lineIn = "";
        int countDeep = 0;// счетчик глубины вложенности  тегов
        boolean needFlash = false;// флаг когда нужно записать готовые строки
        while ((lineIn = reader.readLine()) != null)
        {

            for (int i = 0; i < lineIn.length(); i++)
            {
                if (((i + lenghtInTag) <= lineIn.length()) && (inTag.equals(lineIn.substring(i, i + lenghtInTag)))) // нашли начало тега
                {
                    countDeep++;
                    needFlash = false;// пошел вперед снимаю флаг
                }

                if (((i + lenghtOutTag) <= lineIn.length()) && (outTag.equals(lineIn.substring(i, i + lenghtOutTag)))) // нашли конец тега
                {
                    countDeep--;
                    needFlash = true; // пошел обратно взвожу флаг
                    // проверяем  не пора ли сбросить в консоль
                    if ((countDeep == 0) & needFlash) // сбросим результ в консоль
                    {
                        flashResult(lines,outTag);
                    }
                }

                for (int y = 1; y <= countDeep; y++) // заполняем все строки выше глубины вложенности по одному символу
                {
                    lines[y] = lines[y] + lineIn.substring(i, i + 1);

                }
            }



        }
        reader.close();
    }
    public static void flashResult(String[] lines,String outTag){
        for (int i = 1; i < lines.length; i++)
        {
            if (lines[i] != "")
            {
                System.out.println(lines[i] + outTag);
                lines[i] = "";
            }
        }

    }

}
