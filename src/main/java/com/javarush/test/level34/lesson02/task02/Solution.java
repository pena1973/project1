package com.javarush.test.level34.lesson02.task02;

/* Факториал с помощью рекурсии
Почитать про вычисление факториала.
Реализовать логику метода factorial, где n - это число, факториал которого нужно вычислить.
Не создавайте статические переменные и поля класса.
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.factorial(9));     //362880
        System.out.println(solution.factorial(0));     //1
        System.out.println(solution.factorial(1));     //1
    }

    public int factorial(int n) {
        if (n<0) throw new IllegalArgumentException ("x должен быть >=0"); // исключительная ситуация
        if (n<=1) return 1;    // здесь рекурсия прекращается
        else return n*factorial(n-1);  // рекурсия - метод вызывает сам себя


    }
}

