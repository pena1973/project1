package com.javarush.test.level19.lesson03.task05;

import java.util.HashMap;
import java.util.Map;

/* Закрепляем адаптер
Адаптировать Customer и Contact к RowItem.
Классом-адаптером является DataAdapter.
Инициализируйте countries перед началом выполнения программы. Соответствие кода страны и названия:
UA Ukraine
RU Russia
CA Canada
*/

public class Solution
{
    private static Map<String, String> countries = new HashMap<String, String>();

   static
    {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "CA");
    }

    public static void main(String args[])
    {
        Customer customer1 = new Customer()
        {
            @Override
            public String getCountryName()
            {
                return "Ukraine";
            }

            @Override
            public String getCompanyName()
            {
                return "JavaRush Ltd.";
            }
        };


        Contact сontact1 = new Contact()
        {
            @Override
            public String getName()
            {
                return "Ivanov, Ivan";
            }

            @Override
            public String getPhoneNumber()
            {
                return "+38(050)123-45-67";
            }

        };

        DataAdapter dataAdapter = new DataAdapter(customer1, сontact1);
        //  Contact contact = new IncomeDataAdapter(incomeData);

        System.out.println(dataAdapter.getCountryCode());
        System.out.println(dataAdapter.getCompany());
        System.out.println(dataAdapter.getContactFirstName());
        System.out.println(dataAdapter.getContactLastName());
        System.out.println(dataAdapter.getDialString());

    }


    public static class DataAdapter implements RowItem
    {
        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact)
        {
            this.contact = contact;
            this.customer = customer;
        }

        @Override
        public String getCountryCode()
        {
            customer.getCountryName();
            for (Map.Entry<String, String> entry : countries.entrySet()){
                if (entry.getValue().equals(customer.getCountryName()))
                {
                    return entry.getKey();
                }
            }
            return null;
        }

        @Override
        public String getCompany()
        {
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName()
        {
            String name = contact.getName();
            String[] arr = name.split(",");
            return arr[1].trim();
        }

        @Override
        public String getContactLastName()
        {
            String name = contact.getName();
            String[] arr = name.split(",");
            return arr[0].trim();

        }

        @Override
        public String getDialString()
        {
            String phone = contact.getPhoneNumber();
            phone = phone.replace('(',',');
            phone = phone.replace(')',',');
            phone = phone.replace('-',',');
            String[] arr = phone.split(",");
            phone="callto://";
            for (int i = 0; i <arr.length ; i++)
                phone = phone+arr[i];

            return phone;
        }
    }

    public static interface RowItem
    {
        String getCountryCode();        //example UA

        String getCompany();            //example JavaRush Ltd.

        String getContactFirstName();   //example Ivan

        String getContactLastName();    //example Ivanov

        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer
    {
        String getCompanyName();        //example JavaRush Ltd.

        String getCountryName();        //example Ukraine
    }

    public static interface Contact
    {
        String getName();               //example Ivanov, Ivan

        String getPhoneNumber();        //example +38(050)123-45-67
    }
}