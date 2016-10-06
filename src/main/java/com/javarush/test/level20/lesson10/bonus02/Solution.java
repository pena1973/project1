package com.javarush.test.level20.lesson10.bonus02;

import java.util.ArrayList;
import java.util.List;

/* Алгоритмы-прямоугольники
1. Дан двумерный массив N*N, который содержит несколько прямоугольников.
2. Различные прямоугольники не соприкасаются и не накладываются.
3. Внутри прямоугольник весь заполнен 1.
4. В массиве:
4.1) a[i, j] = 1, если элемент (i, j) принадлежит какому-либо прямоугольнику
4.2) a[i, j] = 0, в противном случае
5. getRectangleCount должен возвращать количество прямоугольников.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}


        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a) {

        int count = 0;
        for (int i = 0; i <a.length; i++)
        {
            for (int j = 0; j < a[i].length; j++)
            {
              if (a[i][j]==1){

                 // нашли входную точку, идем пока ноль не встретится
                  // находим прямоугольник и его из массива удаляем
                  a = searchDel(i,j,a);
                  count++;
              }
            }
        }
        return count;
    }

    public static  byte[][] searchDel( int i1,int j1,  byte[][] a)
    {   int i2=i1;// вторая координата по вертикали
        int j2=j1;// вторая координата по горизонтали
        // иду по вeртикали ищу координату
        for (int k = i1+1; k <a.length; k++)
        {
            if (a[k][j1]==0){
            break;
            }
            i2 = Math.max(i2,k);
        }

        // иду по горизонтали ищу координату
        for (int k = j1+1; k <a[i1].length; k++)
        {
            if (a[i1][k]==0){

                break;
            }
            j2 = Math.max(j2,k);
        }

        // удаляем прямоугольник
        for (int i = i1; i <=i2; i++)
        {
            for (int j = j1; j <= j2; j++)
            {
                    a[i][j] = 0;
            }
        }

        return a;
    }
}

