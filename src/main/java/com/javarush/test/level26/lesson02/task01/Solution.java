package com.javarush.test.level26.lesson02.task01;


import java.util.Arrays;
import java.util.Comparator;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution
{
    public static void main(String[] args)
    {
        Integer[] array = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6};
        // медиана
        array = sort(array);
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
    }

    public static Integer[] sort(Integer[] array)
    {

        final double median;
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        Arrays.sort(array);

        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        int middle = array.length / 2;
        if (array.length % 2 == 1)
            median = array[middle];
        else
            median = (array[middle - 1] + array[middle]) / 2.0;

        System.out.println(median);

        Comparator<Integer> comparatorByMedian = new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                int result1 = Math.abs((int) ((double) o1 - median));
                int result2 = Math.abs((int) ((double) o2 - median));

                // если Удаленность =0
                if ((result1 - result2) == 0) return o1 - o2;
                else return result1 - result2;
            }
        };

        Arrays.sort(array, comparatorByMedian);

        return array;
    }


}
