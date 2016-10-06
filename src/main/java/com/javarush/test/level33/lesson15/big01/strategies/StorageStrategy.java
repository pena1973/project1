package com.javarush.test.level33.lesson15.big01.strategies;

public interface StorageStrategy
{
   public boolean containsKey(Long key);      // – должен вернуть true, если хранилище  содержит переданный ключ.

   public boolean containsValue(String value);// - должен вернуть true, если хранилище содержит переданное значение.
   public void put(Long key, String value);   // – добавить в хранилище новую пару ключ –  значение.
   public Object getKey(String value);          // – вернуть ключ для переданного значения.
   public String getValue(Long key);          // – вернуть значение для переданного ключа.
}
