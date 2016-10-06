package com.javarush.test.level21.lesson05.task03;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* Ошибка в equals/hashCode
Исправьте ошибки реализаций методов equals и hashCode для класса Solution
*/
public class Solution {
    private int anInt;
    private String string;
    private double aDouble;
    private Date date;
    private Solution solution;

    public Solution(int anInt, String string, double aDouble, Date date, Solution solution) {
        this.anInt = anInt;
        this.string = string;
        this.aDouble = aDouble;
        this.date = date;
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // если одинаковая ссылка истина

        if (!(o instanceof Solution)) return false; // если объект не солюшн  и если он нуль то ложь

        Solution solution1 = (Solution) o;
        if (anInt != solution1.anInt) return false; // если не равно значение int


        // проверяю значение string

        if (!(solution1.string == null && string == null))
        {
            if (solution1.string == null || string == null) return false;
            else if (!solution1.string.equals(string)) return false;
        }

        // проверяю значение aDouble
        if (Double.compare(solution1.aDouble, aDouble) != 0) return false;

        // проверяю значение date
        if (!(solution1.date == null && date == null))
        {
            if (solution1.date == null || date == null) return false;
            else if (!solution1.date.equals(date)) return false;
        }
        // проверяю значение solution
        if (!(solution1.solution == null && solution == null))
        {
            if (solution1.solution == null || solution == null) return false;
            else if (!solution1.solution.equals(solution)) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = anInt;
        temp = aDouble != +0.0d ? Double.doubleToLongBits(aDouble) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (string != null ? string.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (solution != null ? solution.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) throws ParseException
    {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy", Locale.ENGLISH);

        Solution solution1 = new Solution(10, "string", 10d, new Date(), null);
        Solution solution2 = new Solution(10, "string", 10d, sdf.parse("31.12.1958"), null);
        Solution solution3 = new Solution(10, "string", 10d, sdf.parse("31.12.1958"), solution2);


        System.out.println(solution1.hashCode());
        System.out.println(solution2.hashCode());
        System.out.println(solution3.hashCode());
       // System.out.println(solution3.equals(solution2));

    }
}
