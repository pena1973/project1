package com.javarush.test.level37.lesson10.big01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.InternalError;
import java.util.*;


public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable
{
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet()
    {
        this.map = new HashMap<E, Object>();
    }

    public AmigoSet(Collection<? extends E> collection)
    {
        int capasity = (int) Math.max(16, collection.size() / .75f);
        this.map = new HashMap<E, Object>(capasity);
        this.addAll(collection);
    }

    //    * Iterator<E> iterator() - очевидно, что это итератор ключей. Получи множество ключей в map, верни его итератор
    @Override
    public Iterator<E> iterator()
    {
        return map.keySet().iterator();
    }

    //    * int size() - это количество ключей в map, равно количеству элементов в map
    @Override
    public int size()
    {
        return map.size();
    }

    @Override
    public boolean add(E e)
    {
        return map.put(e, PRESENT) == null;
    }

//    * boolean isEmpty()

    @Override
    public boolean isEmpty()
    {
        return map.isEmpty();
    }

//    * boolean contains(Object o)

    @Override
    public boolean contains(Object o)
    {
        return map.containsKey(o);
    }

//    * void clear()

    @Override
    public void clear()
    {
        map.clear();
    }

//    * boolean remove(Object o)

    @Override
    public boolean remove(Object o)
    {
        if (map.containsKey(o))
        {
            map.remove(o);
            return true;
        }
        return false;
    }

    //    * Склонируй множество, склонируй его мапу
//    * В случае возникновения исключений выбрось InternalError
//    * Убери лишнее пробрасывание исключения
//
//    Расширь модификатор доступа до public.
    @Override
    public Object clone()
    {
        try
        {
            AmigoSet<E> set = new AmigoSet<E>(this);
            return set;
        }
        catch (Exception e)
        {
            throw new InternalError();
        }
    }
//
//    . Реализуй свою логику сериализации и десериализации.
//    Вспоминай, какие именно приватные методы нужно добавить, чтоб сериализация пошла по твоему сценарию.
//    Для сериализации:
//        * сериализуй сет
//    * сериализуй capacity и loadFactor у объекта map, они понадобятся для десериализации.
//    Т.к. эти данные ограничены пакетом, то воспользуйся утилитным классом HashMapReflectionHelper, чтобы достать их.


    private void writeObject(ObjectOutputStream s) throws IOException
    {
        s.defaultWriteObject(); //это значит, что у объекта типа AmigoSet будут сериализованы ВСЕ поля, которые не transient (или не сериализуются по умолчанию).
        s.writeObject(map.size());
        for(E e:map.keySet()){
            s.writeObject(e);
        }
        s.writeObject(HashMapReflectionHelper.callHiddenMethod(map,"capacity"));
        s.writeObject(HashMapReflectionHelper.callHiddenMethod(map,"loadFactor"));
    }
//    Для десериализации:
//    * вычитай все данные
//    * создай мапу используя конструктор с capacity и loadFactor

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        int size = (int) s.readObject();
        Set<E> set = new HashSet<>();

        for (int i = 0; i < size; i++) {
            set.add((E) s.readObject());
        }
        int capacity =(int) s.readObject();
        float loadFactor = (float) s.readObject();
        map = new HashMap<>(capacity, loadFactor);
        for (E e : set) {
            map.put(e, PRESENT);
        }
    }
}
