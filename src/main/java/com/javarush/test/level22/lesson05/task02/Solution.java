package com.javarush.test.level22.lesson05.task02;

/* Между табуляциями
Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
На некорректные данные бросить исключение TooShortStringException.
Класс TooShortStringException не менять.
*/
public class Solution {
    public static void main(String[] args) throws TooShortStringException
    {
            System.out.println(getPartOfString("A\tB\tC\tD"));
    }
    public static String getPartOfString(String string) throws TooShortStringException
    {
        try {
            int nomBegin = string.indexOf('\t');
            int nomEnd = string.indexOf('\t',nomBegin+1);
            return  string.substring(nomBegin+1, nomEnd);
        } catch (Exception e) {
                throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends Exception {
    }
}
