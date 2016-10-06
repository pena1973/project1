package com.javarush.test.level08.lesson08.task03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* Одинаковые имя и фамилия
Создать словарь (Map<String, String>) занести в него десять записей по принципу «Фамилия» - «Имя».
Проверить сколько людей имеют совпадающие с заданным имя или фамилию.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        HashMap map = createMap();
        System.out.println(getCountTheSameFirstName(map, "Ната"));
        System.out.println(getCountTheSameLastName(map, "Иванова"));
    }

    public static HashMap<String, String> createMap()
    {
        //Напишите тут ваш код
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Иванова","Ната");
        map.put("Петрова","Ира");

        map.put("Сидорова","Лена");
        map.put("Краснова","Рита");

        map.put("Чернова","Ната");
        map.put("Серова","Маша");

        map.put("Рыжова","Даша");
        map.put("Белова","Глаша");

        map.put("Климова","Ната");
        map.put("Ивановская","Таня");
        return map;
    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name)
    {
        //Напишите тут ваш код
        int count = 0;
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String, String> pair = iterator.next();
            String value = pair.getValue();
            if (value.equalsIgnoreCase(name))
            count++;
        }
        return count;
    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String familiya)
    {
        //Напишите тут ваш код
        int count = 0;
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String, String> pair = iterator.next();
            String key = pair.getKey();
            if (key.equalsIgnoreCase(familiya))
            count++;
        }
        return count;
    }
}
