package com.javarush.test.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Каждый сгенерированный символ пароля пишите сразу в ByteArrayOutputStream.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution
{
    public static void main(String[] args)
    {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        int num = 0;     // есть цифра в пароле
//        int mLetter = 0; // есть большая буква в пароле
//        int bLetter = 0; // есть малая буква в пароле
        char[] symbols = letters.toCharArray();

        int r;
        // генерю первые 4 символов цифры

        r = (int)(52 + Math.random() * (62 - 52));
        byteArrayOutputStream.write(symbols[r]);
        r = (int)(52 + Math.random() * (62 - 52));
        byteArrayOutputStream.write(symbols[r]);
        r = (int)(52 + Math.random() * (62 - 52));
        byteArrayOutputStream.write(symbols[r]);
        r = (int)(52 + Math.random() * (62 - 52));
        byteArrayOutputStream.write(symbols[r]);

      //  генерю вторые 2 символа большие буквы

        r = (int)(26 + Math.random() * (52 - 26));
        byteArrayOutputStream.write(symbols[r]);
        r = (int)(26 + Math.random() * (52 - 26));
        byteArrayOutputStream.write(symbols[r]);

        // генерю третьи 2 символа маленькие буквы
        r = (int)(0 + Math.random() * (26 - 0));
        byteArrayOutputStream.write(symbols[r]);
        r = (int)(0 + Math.random() * (26 - 0));
        byteArrayOutputStream.write(symbols[r]);


//        // генерю первые 6 символов
//        for (int i = 0; i < 6; i++)
//        {
//            r = (int) (Math.random() * symbols.length);
//            if (r <= 26) mLetter = 1;
//            else if (r >= 53) num = 1;
//            else bLetter = 1;
//            // добавили в буфер
//            byteArrayOutputStream.write(symbols[r]);
//        }
//
//        // седьмой символ
//        if ((mLetter + num + bLetter) >= 2) // хотя бы два символа уже сгенерились на предыдущих этапах
//        {
//            r = (int) (Math.random() * symbols.length);
//            if (r <= 26) mLetter = 1;
//            else if (r >= 53) num = 1;
//            else bLetter = 1;
//            // добавили в буфер
//            byteArrayOutputStream.write(symbols[r]);
//        } else
//        {
//            while ((mLetter + num + bLetter) == 1) // иначе генерим до тех пор пока не получим нужный вид символа
//            {
//                r = (int) (Math.random() * symbols.length);
//                if (r <= 26) mLetter = 1;
//                else if (r >= 53) num = 1;
//                else bLetter = 1;
//                if ((mLetter + num + bLetter) == 2)// два символа сгенерились
//                    // добавили в буфер
//                    byteArrayOutputStream.write(symbols[r]);
//            }
//        }
//
//        // восьмой символ
//        if (((mLetter + num + bLetter) == 3)) // все символы уже сгенерились на предыдущих этапах
//        {
//            r = (int) (Math.random() * symbols.length);
//            // добавили в буфер
//            byteArrayOutputStream.write(symbols[r]);
//        } else
//        {
//            while ((mLetter + num + bLetter) == 2) // иначе генерим до тех пор пока не получим нужный вид символа
//            {
//                r = (int) (Math.random() * symbols.length);
//                if (r <= 26) mLetter = 1;
//                else if (r >= 53) num = 1;
//                else bLetter = 1;
//                if ((mLetter + num + bLetter) == 3)// два символа сгенерились
//                    // добавили в буфер
//                    byteArrayOutputStream.write(symbols[r]);
//            }
//        }
        return byteArrayOutputStream;
    }
}
