package com.javarush.test.level34.lesson08.bonus01;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        if (cache.get(key) != null) return cache.get(key);
        Constructor<V> constructor = clazz.getDeclaredConstructor(key.getClass());
        cache.put(key,  constructor.newInstance(key));
        return cache.get(key);

//        return null;
    }

    public boolean put(V obj) {
        //TODO add your code here
          Method method = null;
        try
        {
            method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);
            cache.put((K) method.invoke(obj), obj);
            return true;
        }
        catch (NoSuchMethodException e)
        {
        }
        catch (IllegalAccessException e)
        {
        }
        catch (InvocationTargetException e)
        {
        }
        return false;

//        return false;
    }

    public int size() {
        return cache.size();
    }
}
