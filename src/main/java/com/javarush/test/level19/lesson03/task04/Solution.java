package com.javarush.test.level19.lesson03.task04;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1978

Подсказка: воспользуйтесь классом Calendar
*/

public class Solution
{
    public static void main(String args[]) throws IOException
    {
        //подготовили файл
        String fileName = "C:\\Users\\Sid927\\Desktop\\111.txt";
        Scanner file = new Scanner(new FileInputStream(fileName));
        PersonScannerAdapter personScannerAdapter = new PersonScannerAdapter(file);
        System.out.print(personScannerAdapter.read());
        personScannerAdapter.close();
    }

    public static class PersonScannerAdapter implements PersonScanner
    {
        private Scanner scanner;

        public PersonScannerAdapter(Scanner scanner)
        {
            this.scanner = scanner;
        }

        @Override
        public Person read() throws IOException
        {
            String string = scanner.nextLine();
            String[] line = string.split(" ");

            Calendar calendar = new GregorianCalendar(Integer.parseInt(line[5]), Integer.parseInt(line[4]) - 1, Integer.parseInt(line[3]));

            return new Person(line[1], line[2], line[0], calendar.getTime());
        }

        @Override
        public void close() throws IOException
        {
            scanner.close();
        }

    }
}
