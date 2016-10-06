package com.javarush.test.level20.lesson10.home03;

import java.io.*;

/* Найти ошибки
Почему-то при сериализации/десериализации объекта класса B возникают ошибки.
Найдите проблему и исправьте ее.
Класс A не должен реализовывать интерфейсы Serializable и Externalizable.
Сигнатура класса В не содержит ошибку :)
*/
public class Solution implements Serializable
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        //Serializing the singleton instance
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("b.tmp"));
        Solution.B b = new Solution().new B("S");
        oos.writeObject(b);
        oos.close();

        //Recreating the instance by reading the serialized object data store
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("b.tmp"));
        Object o = ois.readObject();
        ois.close();
    }

    public static class A
    {
        protected String name = "A";

        public A(String name)
        {
            this.name += name;
        }

        public A()
        {
        }
    }

    public class B extends A implements Serializable
    {
        public B(String name)
        {
            super(name);
            this.name += name;
        }

        public B()
        {
        }

        private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException
        {
            s.defaultReadObject();
            name = (String) s.readObject();
        }

        private void writeObject(ObjectOutputStream s) throws IOException
        {
            s.defaultWriteObject();
            s.writeObject(this.name);
        }
    }
}
