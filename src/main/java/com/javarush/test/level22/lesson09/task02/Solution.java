package com.javarush.test.level22.lesson09.task02;

import java.util.HashMap;
import java.util.Map;

/* Формируем Where
Сформируйте часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}
Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution {
    public static void main(String[] args)
    {
        Map<String,String> params = new HashMap<String,String>();
        params.put("name","Ivanov");
        params.put("country","Ukraine");
        params.put("city","Kiev");
        System.out.println(getCondition(params).toString());
    }
    public static StringBuilder getCondition(Map<String, String> params) {

        StringBuilder builder = new StringBuilder("");
        for (Map.Entry<String,String> m : params.entrySet())
        {
            if (m.getValue()!=null)
            {
                if (builder.length()>0)
                builder.append(" and ").append(m.getKey()).append(" = '").append(m.getValue()+"'");
                else builder.append(m.getKey()).append(" = '").append(m.getValue()+"'");
            }
        }
        return builder;
    }
}
