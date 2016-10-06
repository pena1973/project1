package com.javarush.test.level08.lesson08.task01;

import java.util.HashSet;
import java.util.Set;

/* 20 слов на букву «Л»
Создать множество строк (Set<String>), занести в него 20 слов на букву «Л».
*/

public class Solution
{
    public static void main(String[] args) throws Exception{
        HashSet<String> s = createSet();
    }

    public static HashSet<String> createSet()
    {
        //Напишите тут ваш код

        HashSet<String> set = new HashSet<String>();
        set.add("лошадь");
        set.add("люди");
        set.add("лень");
        set.add("линза");
        set.add("лось");
        set.add("лужа");
        set.add("лыжи");
        set.add("лебедь");
        set.add("лоно");
        set.add("линька");
        set.add("лак");
        set.add("лик");
        set.add("лом");
        set.add("лор");
        set.add("лодка");
        set.add("ложка");
        set.add("ловкость");
        set.add("ловец");
        set.add("ласка");
        set.add("лизалка");
        return set;
    }
}
