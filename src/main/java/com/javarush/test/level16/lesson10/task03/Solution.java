package com.javarush.test.level16.lesson10.task03;

/* Снова interrupt
Создай нить TestThread.
В методе main создай экземпляр нити, запусти, а потом прерви ее используя метод interrupt().
*/

import static java.lang.Thread.sleep;

public class Solution
{
    public static void main(String[] args) throws InterruptedException
    {
        //Add your code here - добавь код тут
        TestThread tread = new TestThread();
        tread.start();
        System.out.println("Start");
        //sleep(1);
        tread.interrupt();
        System.out.println("Stop");
    }

    //Add your code below - добавь код ниже
    public static class TestThread extends Thread
    {
        @Override
        public void run()
        {
            while (!isInterrupted())
            {
                System.out.println("go!");
            }
        }

    }
}
