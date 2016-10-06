package com.javarush.test.level08.lesson08.task05;

import java.util.*;

/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        HashMap map = createMap();
        removeTheFirstNameDuplicates(map);

        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair: copy.entrySet())
        System.out.println(pair.getValue());

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

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map)
    {
        HashSet <String> setSingle =  new HashSet <String>();
        HashSet <String> setDouble =  new HashSet <String>();
        //Выясняю двойные
        Iterator<Map.Entry<String, String>> iterator1 = map.entrySet().iterator();
        while (iterator1.hasNext())
        {
            Map.Entry<String, String> pair1 = iterator1.next();
            String name = pair1.getValue();
            if (setSingle.contains(name))
                setDouble.add(name);
                setSingle.add(name);
        }
        // Убираю двойные

        Iterator <String> iterator = setDouble.iterator();
        while (iterator.hasNext())
        {
            String itr = iterator.next();
            removeItemFromMapByValue(map, itr);
        }

    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value)
    {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair: copy.entrySet())
        {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }
}
