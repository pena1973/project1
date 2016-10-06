package com.javarush.test.level22.lesson13.task03;

/* Проверка номера телефона
Метод checkTelNumber должен проверять, является ли аргумент telNumber валидным номером телефона.
Критерии валидности:
1) если номер начинается с '+', то он содержит 12 цифр
2) если номер начинается с цифры или открывающей скобки, то он содержит 10 цифр
3) может содержать 0-2 знаков '-', которые не могут идти подряд
4) может содержать 1 пару скобок '(' и ')'  , причем если она есть, то она расположена левее знаков '-'
5) скобки внутри содержат четко 3 цифры
6) номер не содержит букв
7) номер заканчивается на цифру

Примеры:
+380501234567 - true
+38(050)1234567 - true
+38050123-45-67 - true
050123-4567 - true

+38)050(1234567 - false
+38(050)1-23-45-6-7 - false
050ххх4567 - false
050123456 - false
(0)501234567 - false
*/
public class Solution {



    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null) return false;
        if (telNumber.equals("")) return false;

        //1) если номер начинается с '+', то он содержит 12 цифр
        if (telNumber.matches("\\+{1}(.*)")){ //если номер начинается с '+'
        if (!(telNumber.replaceAll("\\D","").length()==12)) // если не содержит 12 цифр  возврат ложь
               return false;
        }

        //2) если номер начинается с цифры или открывающей скобки, то он содержит 10 цифр
        if ((telNumber.matches("\\d{1}(.*)")  ||   telNumber.matches("\\({1}(.*)"))){//если номер начинается с цифры или открывающей скобки
              if (!(telNumber.replaceAll("\\D","").length()==10)) // если он не содержит 10 цифр возврат ложь
         return false;
        }

        //3) может содержать 0-2 знаков '-', которые не могут идти подряд
        if (telNumber.replaceAll("[^\\-]","").length()>2)  // удаляю все символы кроме -
            return false;

        if (telNumber.matches("(.*)\\-{2}(.*)"))  // если подряд --
            return false;

        //4) может содержать 1 пару скобок '(' и ')'  , причем если она есть, то она расположена левее знаков '-'
        if (telNumber.matches("(.*)\\)(.*)\\((.*)"))  // если скобки перевернуты  )(
            return false;

        if (telNumber.matches("(.*)\\((.*)\\)(.*)"))  // есть пара скобок правильных()
        {  // провепяем одна ли пара
            if (telNumber.replaceAll("[^\\(]","").length()>1) // больше одной скобки (
                return false;
            if (telNumber.replaceAll("[^\\)]","").length()>1) // больше одной скобки )
                return false;

            // провепяем - слева от скобок
            String s = telNumber.replaceAll("[^\\(\\)\\-]","");

            if (!(s.substring(0,1).equals("(")))  //если первый  символ не (
            return false;

            if (!(s.substring(1,2).equals(")"))) // если второй  символ не )
                return false;

            //5) скобки внутри содержат четко 3 цифры
            if (!telNumber.matches("(.*)\\(\\d{3}\\)(.*)"))  // скобки внутри содержат четко 3 цифры
                return false;
        }


        //6) номер не содержит букв
        if (telNumber.replaceAll("[a-zA-Z]","").length()!=telNumber.length()) // содержит буквы
            return false;

        //7) номер заканчивается на цифру
        if (!telNumber.matches("(.*)\\d"))
            return false;

        // + номер не должен содержать пробелов точек запятых
        if (telNumber.replaceAll("\\.","").replaceAll(" ","").replaceAll("\\,","").length()!=telNumber.length()) // содержит буквы
            return false;
        // + номер не должен содержать больше одного +
        if (telNumber.replaceAll("[^+]","").length()>1) // содержит буквы
            return false;


            return true;
    }

    public static void testNumber(String number, boolean expected)
    {
        boolean result = checkTelNumber(number);
        System.out.format("%-8s%-20s%-13b%-10b\n", (result == expected ? "OK" : "Fail"), number, result ,expected);
    }
    public static void main(String[] args)
    {
        System.out.format("%-8s%-20s%-13s%-10s\n", "Result", "TellNumber", "AutoCheck", "ManualCheck");
        System.out.format("****************************************************\n");
        testNumber(null, false);
        testNumber("", false);
        testNumber("+380501234567", true);
        testNumber("+3805012345q67", false);
        testNumber("+3805012345 67", false);
        testNumber("+3805012345.67", false);
        testNumber("+3805012345,67", false);
        testNumber("1-23456789-0", true);
        testNumber("1-23(456)789-0", false);
        testNumber("1-234567(89-0)", false);
        testNumber("1-2345678(9-0)", false);
        testNumber("(1-2)3456789-0", false);
        testNumber("+38051202(345)-7", true);
        testNumber("(345)0512027", true);
        testNumber("+-313450531202", true);
        testNumber("+-313450531202-", false);
        testNumber("+380501212334567", false);
        testNumber("+3805012asd34567", false);
        testNumber("+38(050)1234567", true);
        testNumber("+38(150)1234567", true);
        testNumber("+3+8(050)1234567", false);
        testNumber("+38(050)12-34-567", true);
        testNumber("+38(050)12-34567", true);
        testNumber("+38(050)112-34567", false);
        testNumber("+38(050)12-34(5)67", false);
        testNumber("+3(8)(050)12-34567", false);
        testNumber("+38050123-45-67", true);
        testNumber("+38050123-45-6789", false);
        testNumber("050123-4567", true);
        testNumber("050123-45678", false);
        testNumber("+38)050(1234567", false);
        testNumber("+38(050)1-23-45-6-7", false);
        testNumber("050ххх4567", false);
        testNumber("050123456", false);
        testNumber("(0)501234567", false);
        testNumber("+38-(050)1234567", false);
        testNumber("38-(050)34567", false);
        testNumber("-38-(050)34567", false);
        testNumber("38-(050)34567-", false);
        testNumber("38(050)3(45)67", false);
        testNumber("(380)(050)3567", false);
        testNumber("+38(380)(050)3567", false);
        testNumber("8(380)(050)367", false);
        testNumber("8(380)4(050)67", false);
        testNumber("+38((050)1234567", false);
        testNumber("+5(0--5)1234567", false);
        testNumber("7-4-4123689-5", false);
        testNumber("+(012)123456789", true);
        testNumber("+(012)1-2345678-9", true);
        testNumber("+(012)-12345678-9", true);
        testNumber("+(012)-1-23456789", true);
        testNumber("+(012)1234567", false);
        testNumber("+(01-2)123456789", false);
        testNumber("+(012)12345678--9", false);
        testNumber("+(012)--123456789", false);
        testNumber("+38(050)-1234567", true);
        testNumber("+38050(123)(456)7", false);
        testNumber("051202(345)-7", true);
        testNumber("+38051202(345)7", true);
        testNumber("+380501234(567)", false);
        testNumber("+38050123425-1", true);
        testNumber("+380501234251", true);
        testNumber("(050)34(125)6-7", false);
    }
}
