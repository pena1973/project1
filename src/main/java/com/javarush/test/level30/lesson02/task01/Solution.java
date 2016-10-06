package com.javarush.test.level30.lesson02.task01;

/* Осваиваем методы класса Integer
Используя метод Integer.parseInt(String, int) реализуйте логику метода convertToDecimalSystem,
который должен переводить переданную строку в десятичное число и возвращать его в виде строки.
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        int result = 0;
        if (s.substring(0, 2).equals("0x")){
            String number = s.substring(2, s.length());
            for (int i = 0; i <number.length(); i++)
            {
            result = (int) (result +(int) Double.parseDouble(number.substring(i,i+1))* Math.pow(16,(number.length()-i-1)));
            }
        }
        else if (s.substring(0, 2).equals("0b")){
            String number = s.substring(2, s.length());
            for (int i = 0; i <number.length(); i++)
            {
                result = (int) (result +(int) Double.parseDouble(number.substring(i,i+1))* Math.pow(2,(number.length()-i-1)));
            }
        }
        else if (s.substring(0, 1).equals("0")){
            String number = s.substring(1, s.length());
            for (int i = 0; i <number.length(); i++)
            {
                result = (int) (result +(int) Double.parseDouble(number.substring(i,i+1))* Math.pow(8,(number.length()-i-1)));
            }
        }
        else {result = Integer.parseInt(s); }

        return ""+result;
    }
}
