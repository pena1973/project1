package com.javarush.test.level35.lesson08.bonus01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {


   public static <T> Map convert(List<? extends Convertable<T>> list) {
   Map<T, Convertable<T>> result = new HashMap<>();
       for (Convertable<T> value : list) {
            result.put(value.getKey(), value);
        }
   return result;
   }
}
