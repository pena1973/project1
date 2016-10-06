package com.javarush.test.level09.lesson11.home09;

import java.util.*;

/* Десять котов
Создать класс кот – Cat, с полем «имя» (String).
Создать словарь Map(<String, Cat>) и добавить туда 10 котов в виде «Имя»-«Кот».
Получить из Map множество(Set) всех имен и вывести его на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap()
    {
        //Напишите тут ваш код
        HashMap<String, Cat> map = new HashMap<String, Cat>();
        map.put("Бася",new Cat("Бася"));
        map.put("Вася",new Cat("Вася"));

        map.put("Кася",new Cat("Кася"));
        map.put("Кися",new Cat("Кися"));

        map.put("Тася",new Cat("Тася"));
        map.put("Дася",new Cat("Дася"));

        map.put("Митя",new Cat("Митя"));
        map.put("Китя",new Cat("Китя"));

        map.put("Витя",new Cat("Витя"));
        map.put("Дитя",new Cat("Дитя"));

        return map;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map)
    {
        //Напишите тут ваш код
        Set<Cat> set = new HashSet<Cat>(map.values());
        return  set;
    }

    public static void printCatSet(Set<Cat> set)
    {
        for (Cat cat:set)
        {
            System.out.println(cat);
        }
    }

    public static class Cat
    {
        private String name;

        public Cat(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return "Cat "+this.name;
        }
    }


}
