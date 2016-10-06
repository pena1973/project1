package com.javarush.test.level20.lesson10.home05;

import java.io.*;
import java.util.logging.Logger;

/* Сериализуйте Person
Сериализуйте класс Person.
*/
public class Solution  {

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        //Serializing the singleton instance
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("b.tmp"));
        Person person = new Person("firstName", "lastName", "country", Sex.MALE);
        oos.writeObject(person);
        oos.close();

        //Recreating the instance by reading the serialized object data store
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("b.tmp"));
        Object person1 = ois.readObject();
        ois.close();
    }

    public static class Person implements Serializable
    {
        private static final long serialVersionUID = 1L;
        String firstName;
        String lastName;
        transient String fullName;
        transient final String greetingString;
        String country;
        Sex sex;
        transient PrintStream outputStream;
        transient Logger logger;

        Person(String firstName, String lastName, String country, Sex sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.greetingString = "Hello, ";
            this.country = country;
            this.sex = sex;
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }


        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            fullName = String.format("%s, %s", lastName, firstName);
            outputStream = System.out;
            logger = Logger.getLogger(String.valueOf(Person.class));
        }


    }

    enum Sex {
        MALE,
        FEMALE
    }
}
