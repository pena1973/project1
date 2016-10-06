package com.javarush.test.level20.lesson10.bonus01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {

    static private long[][] pows = new long[10][12];
    static private int[] number = new int[58];

    static
    {
        // готовим массивы степеней i- цифра, j - степень

        for (int i = 1; i <= 9; i++)
        {
            for (int j = 1; j <= 11; j++)
            {
                pows[i][j] = (long) Math.pow(i, j);
            }
        }

        // готовлю массив цифр и их char
        for (int i = 0; i < 58; i++) number[i] = 0;
        number[48] = 0;
        number[49] = 1;
        number[50] = 2;
        number[51] = 3;
        number[52] = 4;
        number[53] = 5;
        number[54] = 6;
        number[55] = 7;
        number[56] = 8;
        number[57] = 9;

    }

    public static void main(String[] args)
    {
        Long t0 = System.currentTimeMillis();
        //long n = 2147483647;
        int n = 10000;
        int[] numbers = getNumbers(n);
        Long t1 = System.currentTimeMillis();
        System.out.println("time: " + (t1 - t0) / 1000d + " sec");
        System.out.println("memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " mb");
        for (int i = 0; i < numbers.length; i++)
        {
            System.out.print(numbers[i] + ", ");
        }
        System.out.println();
    }

    public static int[] getNumbers(int N) {

        HashMap<Long, Long> map = new HashMap<>();


        //: рассматриваются все числа,
        // у которых любая цифра не меньше предыдущей
        // и не больше последующей.
        // Например: 12, 1557, 333 и т.д. :
        //

        String strN = "";
        long res = 0;
        long res1 = 0;


        // собираю число

        for (int i10 = 0; i10 <= 9; i10++)
        {
            for (int i9 = i10; i9 <= 9; i9++)
            {
                for (int i8 = i9; i8 <= 9; i8++)
                {
                    for (int i7 = i8; i7 <= 9; i7++)
                    {
                        for (int i6 = i7; i6 <= 9; i6++)
                        {
                            for (int i5 = i6; i5 <= 9; i5++)
                            {
                                for (int i4 = i5; i4 <= 9; i4++)
                                {
                                    for (int i3 = i4; i3 <= 9; i3++)
                                    {
                                        for (int i2 = i3; i2 <= 9; i2++)
                                        {
                                            for (int i1 = i2; i1 <= 9; i1++)
                                            {
                                                strN = "" + i10 + i9 + i8 + i7 + i6 + i5 + i4 + i3 + i2 + i1;
                                                if (N >= Long.parseLong(strN))
                                                {
                                                    for (int i = 1; i <= strN.length(); i++)
                                                    {
                                                        res = pows[i10][i] + pows[i9][i] + pows[i8][i] + pows[i7][i] + pows[i6][i] + pows[i5][i] + pows[i4][i] + pows[i3][i] + pows[i2][i] + pows[i1][i];

                                                        res1 = resCalc("" + res);

                                                        if (res == res1 && N > res)
                                                        {

                                                            map.put(res, res);

                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        int[] result = new int[map.size()-1];
        int count = 0;// счетчик окончсательногьо результата
        for (Map.Entry<Long, Long> e : map.entrySet())
        {
            if (e.getKey() != 0)
            {
                result[count++] = Integer.parseInt(""+e.getValue());
            }
        }
        Arrays.sort(result);
        return result;

    }

    private static long resCalc(String r)
    {
        long res1 = 0;
        char[] chars = r.toCharArray();
        for (int i = 0; i < chars.length; i++)
        {
            res1 = res1 + pows[number[chars[i]]][chars.length];
        }
        return res1;
    }

}
