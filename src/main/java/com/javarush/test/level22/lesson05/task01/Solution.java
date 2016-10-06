package com.javarush.test.level22.lesson05.task01;

/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
*/
public class Solution {

    public static void main(String[] args) throws TooShortStringException {

//        System.out.println(getPartOfString(""));
//        System.out.println(getPartOfString("Мама"));
//        System.out.println(getPartOfString("Мама мыла"));
//       System.out.println(getPartOfString("Мама мыла раму"));
//        System.out.println(getPartOfString("Мама мыла раму мылом"));
//        System.out.println(getPartOfString("Мама мыла раму мылом "));
        System.out.println(getPartOfString("Мама мыла раму мылом тряпкой"));
       System.out.println(getPartOfString("Мама мыла раму мылом тряпкой "));
 //       System.out.println(getPartOfString("Мама мыла раму мылом тряпкой щеткой"));
         }
    public static String getPartOfString(String string) throws TooShortStringException {

        if (string==null){throw  new TooShortStringException();}

        String string1 = string.trim();

        int symbSpace1 = string1.indexOf(' ');// символ после первого пробела
        if (symbSpace1 ==-1){throw  new TooShortStringException();}

       int symbSpace2 = string1.indexOf(' ', symbSpace1 + 1);// символ после второго пробела
       if (symbSpace2 == -1){throw  new TooShortStringException();}

       int symbSpace3 = string1.indexOf(' ', symbSpace2 + 1);// символ после третьего пробела
       if (symbSpace3 == -1){throw  new TooShortStringException();}

       int symbSpace4 = string1.indexOf(' ', symbSpace3 + 1);// символ после четвертго пробела
       if (symbSpace4 == -1){throw  new TooShortStringException();}

       int symbSpace5 = string1.indexOf(' ', symbSpace4 + 1);// символ пятого пробела
       if (symbSpace5 == -1) return string.substring(symbSpace1 + 1);

       return string.substring(symbSpace1 + 1, symbSpace5);
    }

    public static class TooShortStringException extends Exception {
    }
}
