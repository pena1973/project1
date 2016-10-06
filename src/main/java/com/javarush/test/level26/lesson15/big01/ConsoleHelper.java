package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper
{
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    public static  void  writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException
    {
        String s = "";

        try {s = bufferedReader.readLine();
            if (s.equalsIgnoreCase("exit"))
                throw new InterruptOperationException();
        }
        catch (IOException e) {e.printStackTrace();}

        return s;

    }

    public static String askCurrencyCode() throws InterruptOperationException
    {
        String test;
        writeMessage(res.getString("choose.currency.code"));

        while (true)
        {
            test = readString();
            if (test.length() == 3)
                break;
               else

            writeMessage("Incorrect input! Retry input, please:");

        }
        test = test.toUpperCase();
        return test;
    }


    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        String[] array;
        int nom;
        int kol;
        writeMessage("Input denomination and count, please:");

        while (true)
        {
            String s = readString();

            try
            {
                array = s.split(" ");
                nom = Integer.parseInt(array[0]);
                kol = Integer.parseInt(array[1]);

                if (nom <= 0 || kol <= 0 || array.length > 2)
                {
                    //writeMessage("Incorrect input! Retry input, please:");
                    writeMessage(res.getString("choose.denomination.and.count.format"));

                    continue;
                }
                else break;
            }
             catch (Exception e){}

        }


        return array;
    }


    public static Operation askOperation() throws InterruptOperationException
    {
        // Если пользователь вводит 1, то выбирается команда INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT;
        while (true)
        {
            writeMessage(res.getString("choose.operation"));
            writeMessage("1 - " + res.getString("operation.INFO"));
            writeMessage("2 - " + res.getString("operation.DEPOSIT"));
            writeMessage("3 - " + res.getString("operation.WITHDRAW"));
            writeMessage("4 - " + res.getString("operation.EXIT"));

            String line = readString();
            int i= Integer.parseInt(line);

            if ( i>0 & i<5 ){
                return Operation.getAllowableOperationByOrdinal(i);
            }
            else
                writeMessage("Incorrect input! Retry input, please:");
        }

    }

}