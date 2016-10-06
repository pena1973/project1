package com.javarush.test.level08.lesson08.task02;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* Удалить все числа больше 10
Создать множество чисел(Set<Integer>), занести туда 20 различных чисел.
Удалить из множества все числа больше 10.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        HashSet<Integer> s = createSet();
        s = removeAllNumbersMoreThan10(s);

        Iterator<Integer> iterator = s.iterator();
        while (iterator.hasNext())
        {
            int num = iterator.next();

            System.out.println(num);
        }
    }

    public static HashSet<Integer> createSet()
    {
        //Напишите тут ваш код
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < 20; i++)
        {
            set.add(i);
        }
        return set;
    }

    public static HashSet<Integer> removeAllNumbersMoreThan10(HashSet<Integer> set)
    {
        //Напишите тут ваш код

        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext())
        {
            int num = iterator.next();
            if (num > 10)
                iterator.remove();
        }
        return set;

    }
}
