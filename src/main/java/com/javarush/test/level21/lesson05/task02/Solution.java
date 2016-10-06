package com.javarush.test.level21.lesson05.task02;

import java.util.HashSet;
import java.util.Set;

/* Исправить ошибку
Сравнение объектов Solution не работает должным образом. Найти ошибку и исправить.
Метод main не участвует в тестировании.
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public int hashCode()
    {
        return  31 * ((first==null) ? 0 : first.hashCode()) + ((last==null) ? 0 : last.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Solution))
            return false;
        Solution n = (Solution) o;
        boolean equalFirst = false;
        boolean equalLast = false;

        if (n.first == null && first == null)
            equalFirst = true;
        else if (n.first == null || first == null)
            return false;
        else
            equalFirst = n.first.equals(first);

        if (n.last == null && last == null)
            equalLast = true;
        else if (n.last == null || last == null)
            return false;
        else
            equalLast = n.last.equals(last);


        return  equalFirst && equalLast;

    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
       // s.add(new Solution("Mickey", "Mouse"));
        s.add(new Solution(null, "Mouse"));
       // System.out.println(s.contains(new Solution("Mickey", "Mouse")));
        System.out.println(s.contains(new Solution(null, "Mouse")));
        System.out.println(s.contains(new Solution("Mickey", "Mouse")));

    }
}
