package com.javarush.test.level21.lesson08.task02;

import java.util.Map;

/* Клонирование
Класс Plant не должен реализовывать интерфейс Cloneable
Реализуйте механизм глубокого клонирования для Tree.
*/
public class Solution
{
    public static void main(String[] args)
    {
        //Tree tree = new Tree("willow", new String[]{"s1", "s2", "s3", "s4"});
        Tree tree = new Tree("willow", null);
        Tree clone = null;
        try
        {
            clone = tree.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }

        System.out.println(tree);
        System.out.println(clone);

        System.out.println(tree.branches);
        System.out.println(clone.branches);
    }

    public static class Plant
    {
        private String name;

        public Plant(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return name;
        }
    }

    public static class Tree extends Plant implements Cloneable
    {
        private String[] branches;

        public Tree(String name, String[] branches)
        {
            super(name);
            this.branches = branches;
        }

        public String[] getBranches()
        {
            return branches;
        }

        @Override
        protected Tree clone() throws CloneNotSupportedException
        {
            Tree tree; // клон

            // имя
            String name = getName();
            name = (name == null) ? null : new String(name);

            // ветки
            if (getBranches() != null)
            {
                String[] str = getBranches();

                String[] newStr = new String[str.length];
                for (int i = 0; i < str.length; i++)
                    newStr[i] = (str[i] == null) ? null : new String(str[i]);
                tree = new Tree(name, newStr);
            }
            else
                tree = new Tree(name, null);

            return tree;
        }
    }
}
