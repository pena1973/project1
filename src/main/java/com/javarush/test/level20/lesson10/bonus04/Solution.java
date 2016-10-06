package com.javarush.test.level20.lesson10.bonus04;

import java.io.Serializable;
import java.util.*;

/* Свой список
Посмотреть, как реализован LinkedList.
Элементы следуют так: 1->2->3->4  и так 4->3->2->1
По образу и подобию создать Solution.
Элементы должны следовать так:
1->3->7->15
    ->8...
 ->4->9
    ->10
2->5->11
    ->12
 ->6->13
    ->14
Удалили 2 и 9
1->3->7->15
    ->8
 ->4->10
Добавили 16,17,18,19,20 (всегда добавляются на самый последний уровень к тем элементам, которые есть)
1->3->7->15
       ->16
    ->8->17
       ->18
 ->4->10->19
        ->20
Удалили 18 и 20
1->3->7->15
       ->16
    ->8->17
 ->4->10->19
Добавили 21 и 22 (всегда добавляются на самый последний уровень к тем элементам, которые есть.
Последний уровень состоит из 15, 16, 17, 19. 19 последний добавленный элемент, 10 - его родитель.
На данный момент 10 не содержит оба дочерних элемента, поэтому 21 добавился к 10. 22 добавляется в следующий уровень.)
1->3->7->15->22
       ->16
    ->8->17
 ->4->10->19
        ->21

Во внутренней реализации элементы должны добавляться по 2 на каждый уровень
Метод getParent должен возвращать элемент, который на него ссылается.
Например, 3 ссылается на 7 и на 8, т.е.  getParent("8")=="3", а getParent("13")=="6"
Строки могут быть любыми.
При удалении элемента должна удаляться вся ветка. Например, list.remove("5") должен удалить "5", "11", "12"
Итерироваться элементы должны в порядке добавления
Доступ по индексу запрещен, воспользуйтесь при необходимости UnsupportedOperationException
Должно быть наследование AbstractList<String>, List<String>, Cloneable, Serializable
Метод main в тестировании не участвует
*/
public class Solution extends AbstractList<String> implements  List<String>, Serializable, Cloneable
{
    
    private LinkedList<Node> linkedList = new LinkedList<>();
    int level = 0;// задаю общий счетчик уровней
    
    public static void main(String[] args)
    {
        List<String> list = new Solution();
    
        for (int i = 1; i < 16; i++)
        {
            list.add(String.valueOf(i));
        }
        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("11"));
    }

    public Solution()
    {
    }

    // Создали класс узел
    private static class Node<String>
    {// узел содержит имя, имя родителя, уровень
        String name; // имя
        Node<String> parent;// родител
        Node<String> descendant1;  // потомок
        Node<String> descendant2; // потомок
        int level; // уровень


        private Node(String name, Node<String> parent)
        {
            this.name = name;
            this.parent = parent;
            if (parent == null) level = 0;
            else level = parent.level+1;
        }

    }



    // Абстракт лист
    // переопределяем метод добавления в линкед лист
    @Override
    public boolean add(String s)
    {  //Добавить элемент
      //  super.add(s);
        Node newNode;
        if (level == 0) {
            newNode = new Node(s, null);
            level++;
            linkedList.add(newNode);
        } else {
            newNode = new Node(s,getParent(level));
            linkedList.add(newNode);
            if (getParent(level)==null)level++;// Если свободных родителей не осталось увеличиваем уровень
          }
        return true;

    }
    public Node getParent(int level)
    { // получаем родителя по уровню 
      // у которого есть вакантные места для потомков

        if (level == 0) {
            return null;
        // у нулевого уровня нет родителя
        } else {
            for (Node node : linkedList) {
                if (node.level==(level-1)){// у не нулевых уровней смотрим есть ли потомки
                if (node.descendant1 == null) return node;
                if (node.descendant2 == null) return node;
                }
            }
        }
        return null;
    }
    @Override
    public int size()
    { //!Размер
        return 0;
    }

    @Override
    public String get(int index)
    {
        return null;
    }

    @Override
    public String set(int index, String element)
    {
        return super.set(index, element);

    }

    @Override
    public void add(int index, String element)
    {
       // super.add(index, element);
    }

    @Override
    public String remove(int index)
    {
        return super.remove(index);
    }

    @Override
    public int indexOf(Object o)
    {
        return super.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o)
    {
        return super.lastIndexOf(o);
    }

    @Override
    public void clear()
    {//! очистить
        super.clear();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c)
    {
        return super.addAll(index, c);
    }

    @Override
    public Iterator<String> iterator()
    {//!Получить итератор
        return super.iterator();
    }

    @Override
    public ListIterator<String> listIterator()
    {
        return super.listIterator();
    }

    @Override
    public ListIterator<String> listIterator(int index)
    {
        return super.listIterator(index);
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex)
    {
        return super.subList(fromIndex, toIndex);
    }

    @Override
    public boolean equals(Object o)
    {//!Сравнение (сравнивает с другим списком)
        return super.equals(o);
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex)
    {
        throw new UnsupportedOperationException();
        //super.removeRange(fromIndex, toIndex);
    }

    // Абстракт коллекшен

    @Override
    public boolean isEmpty()
    { //!
        return super.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    { //!
        return super.contains(o);
    }

    @Override
    public Object[] toArray()
    { //!
        return super.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        return super.toArray(a);
    }

    @Override
    public boolean remove(Object o)
    {//! Убрать элемент по значению
        return super.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {//!Проверка содержания списка элементов
        return super.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends String> c)
    { //! Добавить все элементы из списка
        return super.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {//!Убрать все элементы по списку
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {//!Очистить
        return super.retainAll(c);
    }

    @Override
    public String toString()
    {//!Привести к строке (для тестов напишите возврат всего списка так, чтобы каждый элмент был в формате <(родитель)значение> )
        return super.toString();
    }

    public String getParent(String value)
    {
        //have to be implemented
        return null;
    }



}
