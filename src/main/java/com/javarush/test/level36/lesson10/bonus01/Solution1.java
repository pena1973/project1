package com.javarush.test.level36.lesson10.bonus01;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* Осваиваем ClassLoader и Reflection
Аргументом для класса Solution является абсолютный путь к пакету,
например, "C:\JavaRushHomeWork\src\com\javarush\test\level36\lesson10\bonus01\data\second".
Имя пакета может содержать File.separator.
В этом пакете находятся только скомпилированные классы.
Известно, что каждый класс имеет конструктор без параметров и реализует интерфейс HiddenClass.
Считайте все классы с файловой системы, создайте фабрику - реализуйте метод getHiddenClassObjectByKey.
Известно, что есть только один класс, простое имя которого начинается с String key без учета регистра.
*/
public class Solution1
{
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution1(String packageName)
    {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException
    {
        //Solution solution = new Solution("C:\\JavaRushHomeWork\\src\\com\\javarush\\test\\level36\\lesson10\\bonus01\\data\\second");
        Solution solution = new Solution("C:\\Users\\Sid927\\Desktop\\java\\idea\\JavaRushHomeWork\\JavaRushHomeWork\\out\\production\\JavaRushHomeWork\\com\\javarush\\test\\level36\\lesson10\\bonus01\\data\\second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));

    }

    public void scanFileSystem() throws ClassNotFoundException
    {
        final File dir = new File(packageName);
        // Ищем нужныe файлы

        FilenameFilter filter = new FilenameFilter()
        {
            public boolean accept(File directory, String fileName)
            {
                return fileName.endsWith(".class");
            }
        };

        class MyClassLoader extends ClassLoader
        {
            File binaryDir;

            public MyClassLoader(File binaryDir, ClassLoader parent)
            {
                super(parent);
                this.binaryDir = binaryDir;
            }

            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException
            {
                byte[] b = new byte[0];
                Path path = Paths.get(binaryDir + File.separator + name + ".class");
                try
                {
                    b = Files.readAllBytes(path);
                    return defineClass(null, b, 0, b.length);
                }
                catch (IOException ignore)
                {
                    return super.findClass(name);
                }
            }
        }

        MyClassLoader loader = new MyClassLoader(dir, ClassLoader.getSystemClassLoader());

        for (File file : dir.listFiles(filter))
        {
            if (file.isFile())
            {
                String name = file.getName().replace(".class", "");
                Class aClass = loader.loadClass(name);

                hiddenClasses.add(aClass);
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key)
    {
        if (key ==null) return null;
        for (Class aClass : hiddenClasses)
        {
            // проверки
            if (!aClass.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) continue;
            if (!HiddenClass.class.isAssignableFrom(aClass)) continue;


            Constructor[] constructors = aClass.getDeclaredConstructors();
            for (Constructor constructor : constructors)
            {
                constructor.setAccessible(true);
                if (constructor.getParameterTypes().length == 0)
                {
                    try
                    {
                        return (HiddenClass) constructors[0].newInstance();
                    }
                    catch (InstantiationException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }
                    catch (InvocationTargetException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

        return null;
    }


}
