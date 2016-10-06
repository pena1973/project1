package com.javarush.test.level21.lesson08.task03;

/* Запретить клонирование
Запретите клонировать класс B
Разрешите клонировать класс C
*/
public class Solution {
    public static void main(String[] args) throws CloneNotSupportedException
    {
        A a = new A(1,2);
        Object cloneA = a.clone();

        System.out.println(a);
        System.out.println(cloneA);

        B b = new B(3,4,"nnn");
        Object cloneB = b.clone();

        System.out.println(b);
        System.out.println(cloneB);

        C c = new C(6,7,"n23nn");
        Object cloneC = c.clone();

        System.out.println(c);
        System.out.println(cloneC);

    }

    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException
        {
            if (this!=null)
            return new A(getI(),getJ());
            else return null;
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException
        {
            throw  new CloneNotSupportedException();
        }
    }

    public static class C extends B {
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException
        {
            if (this!=null)

            return new C( getI(), getJ(),(getName()==null? null : new String(getName())));
            else return null;


        }
    }
}
