package com.javarush.test.level16.lesson13.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Клубок
1. Создай 5 различных своих нитей c отличным от Thread типом:
1.1. нить 1 должна бесконечно выполняться;
1.2. нить 2 должна выводить "InterruptedException" при возникновении исключения InterruptedException;
1.3. нить 3 должна каждые полсекунды выводить "Ура";
1.4. нить 4 должна реализовать интерфейс Message, при вызове метода showWarning нить должна останавливаться;
1.5. нить 5 должна читать с консоли цифры пока не введено слово "N", а потом вывести в консоль сумму введенных цифр.
2. В статическом блоке добавь свои нити в List<Thread> threads в перечисленном порядке.
3. Нити не должны стартовать автоматически.
*/

public class Solution
{
    public static List<Thread> threads = new ArrayList<Thread>(5);

    static
    {
        threads.add(new Thread1());
        threads.add(new Thread2());
        threads.add(new Thread3());
        threads.add(new Thread4());
        threads.add(new Thread5());
    }

    public static void main(String[] args) throws InterruptedException
    {
        Thread1 t1 = (Thread1) threads.get(0);
        Thread2 t2 = (Thread2) threads.get(1);
        Thread3 t3 = (Thread3) threads.get(2);
        Thread4 t4 = (Thread4) threads.get(3);
        Thread5 t5 = (Thread5) threads.get(4);
        t1.start();
        t2.start();
        t2.interrupt();
        t3.start();
        t4.start();
        t4.showWarning();
        t5.start();
    }

    public static class Thread1 extends Thread
    {
        @Override
        public void run()
        {
            while (true)
            {
            }
        }
    }

    public static class Thread2 extends Thread
    {
        @Override
        public void run()
        {
            {
                while (true)
                    try
                    {
                        sleep(10);
                        isInterrupted();
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println("InterruptedException");
                    }
            }
        }
    }

    public static class Thread3 extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                while (true)
                {
                    System.out.println("Ура");
                    sleep(500);
                }
            }
            catch (InterruptedException e)
            {
            }
        }
    }


    public static class Thread4 extends Thread implements Message {
        public void run() {
            while (!isInterrupted()) {
            }
        }
        public void showWarning() {
            this.interrupt();
            try
            {
                this.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }


    public static class Thread5 extends Thread
    {
        private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private String line;
        private int summa;

        @Override
        public void run()
        {
            try
            {
                while (!(line = reader.readLine()).equals("N"))
                {
                    summa = summa + Integer.parseInt(line);
                }
            }
            catch (IOException e)
            {
            }

            System.out.println(summa);
        }

    }

}
