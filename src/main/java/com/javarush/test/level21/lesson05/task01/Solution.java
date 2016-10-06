package com.javarush.test.level21.lesson05.task01;

import java.util.HashSet;
import java.util.Set;

/* Equals and HashCode
В классе Solution исправить пару методов equals/hashCode в соответствии с правилами реализации этих методов.
Метод main не участвует в тестировании.
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Solution n) {
        return n.first.equals(first) && n.last.equals(last);
    }

    @Override
    public boolean equals(Object obj)
    {

        if (obj == null)
            return false;

        if (!obj.getClass().equals(this.getClass()))
            return false;

        Solution n = (Solution) obj;

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

    @Override
    public int hashCode() {
         return  31 * ((first==null) ? 0 : first.hashCode()) + ((last==null) ? 0 : last.hashCode());
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        //s.add(new Solution("Donald", "Duck"));
        s.add(new Solution(null, "null"));
      //  System.out.println(s.contains(new Solution("Donald", "Duck")));
       // System.out.println(s.contains(new Solution("Duck", "Donald")));
        System.out.println(s.contains(new Solution(null, "null")));

    }
}
