package com.javarush.test.level07.lesson09.task04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.lang.CharSequence;

/* Буква «р» и буква «л»
1. Создай список слов, заполни его самостоятельно.
2. Метод fix должен:
2.1. удалять из списка строк все слова, содержащие букву «р»
2.2. удваивать все слова содержащие букву «л».
2.3. если слово содержит и букву «р» и букву «л», то оставить это слово без изменений.
2.4. с другими словами ничего не делать.
Пример:
роза
лира
лоза
Выходные данные:
лира
лоза
лоза
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> list = new ArrayList<String>();
        list.add("роза"); //0
        list.add("роза1"); //0
        list.add("лира"); //1
        list.add("лоза"); //2
        list = fix(list);

        for (String s : list)
        {
            System.out.println(s);
        }
    }

    public static ArrayList<String> fix(ArrayList<String> list) {
        //add your code here -  добавь код тут
        CharSequence r = "р".subSequence(0, 1);
        CharSequence l = "л".subSequence(0, 1);
        //  можно через "л".indexOf() искать

        //for (String s : list)
        int size = list.size();
        for(int i = 0; i < size; i++)
        {
           if (list.get(i).contains(r) && (list.get(i).contains(l)==false)){
               list.remove(i); i--; size--;
           }
           else  if (list.get(i).contains(l) && (list.get(i).contains(r)==false)){
                list.add(i + 1, list.get(i)); i++; size++;
           }
        }

        return list;
    }
}