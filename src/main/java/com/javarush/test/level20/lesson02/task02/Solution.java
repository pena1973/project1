package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            javaRush.users.add(new User());
            javaRush.users.get(0).setLastName("Иван");
            javaRush.users.get(0).setFirstName("Иванов");
            javaRush.users.get(0).setMale(true);
            javaRush.users.get(0).setCountry(User.Country.RUSSIA);
            javaRush.users.get(0).setBirthDate(sdf.parse("31.12.1958"));
            javaRush.users.add(new User());
            javaRush.users.get(1).setLastName("Петр");
            javaRush.users.get(1).setFirstName("Петров");
            javaRush.users.get(1).setMale(false);
            javaRush.users.get(1).setCountry(User.Country.UKRAINE);
            javaRush.users.get(1).setBirthDate(sdf.parse("01.01.1990"));
            javaRush.users.add(new User());  // пустой юзер
            javaRush.users.add(null);  // нулевой юзер
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            PrintWriter writer = new PrintWriter(outputStream);
            writer.println("#START");
            writer.println(users.size());
            for (User user : users)
            {
                // в файл
                if (user != null) writer.println("user");
                else writer.println("null");
                if (user != null)
                {
                    if (user.getFirstName() != null) writer.println(user.getFirstName());
                    else writer.println("null");
                    if (user.getLastName() != null) writer.println(user.getLastName());
                    else writer.println("null");
                    if (user.getBirthDate() != null) writer.println(sdf.format(user.getBirthDate()));
                    else writer.println("null");
                    if (user.getCountry() != null) writer.println(user.getCountry());
                    else writer.println("null");
                    writer.println(user.isMale());
                }
                // в консоль
                if (user != null) System.out.println("user");
                else System.out.println("null");
                if (user != null)
                {
                    if (user.getFirstName() != null) System.out.println(user.getFirstName());
                    else System.out.println("null");
                    if (user.getLastName() != null) System.out.println(user.getLastName());
                    else System.out.println("null");
                    if (user.getBirthDate() != null) System.out.println(sdf.format(user.getBirthDate()));
                    else System.out.println("null");
                    if (user.getCountry() != null) System.out.println(user.getCountry());
                    else System.out.println("null");
                    System.out.println(user.isMale());
                }
            }
            writer.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            reader.readLine();
            int asSize = Integer.parseInt(reader.readLine());
            for (int i = 0; i < asSize; i++)
            {
                String user = reader.readLine();
                if (!user.equals("null"))
                {
                    String firstName = reader.readLine();
                    firstName = (!firstName.equals("null")) ? firstName : null;
                    String lastName = reader.readLine();
                    lastName = (!lastName.equals("null")) ? lastName : null;
                    String date = reader.readLine();
                    Date birthDate = (!date.equals("null")) ? sdf.parse(date) : null;
                    String countryStr = reader.readLine();
                    User.Country country = (!countryStr.equals("null")) ? User.Country.valueOf(countryStr) : null;
                    String male = reader.readLine();
                    boolean isMale = (male.equals("true"));
                    users.add(new User());
                    users.get(users.size() - 1).setFirstName(firstName);
                    users.get(users.size() - 1).setLastName(lastName);
                    users.get(users.size() - 1).setBirthDate(birthDate);
                    users.get(users.size() - 1).setCountry(country);
                    users.get(users.size() - 1).setMale(isMale);
                }
                else users.add(null);
            }
        }
    }
}
