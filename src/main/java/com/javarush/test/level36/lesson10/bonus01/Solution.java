package com.javarush.test.level36.lesson10.bonus01;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/*
удалил
*/
public class Solution
{
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;
    public Solution(String packageName)
    {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException
    {
        Solution solution = new Solution("C:\\JavaRushHomeWork\\src\\com\\javarush\\test\\level36\\lesson10\\bonus01\\data\\second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException
    {
        if(this.packageName == null || this.packageName.isEmpty()) return;

        final File binaryDir = new File(packageName);
        if(binaryDir == null || !binaryDir.isDirectory()) return;

        File[] binaries = getBinaries(binaryDir);
        if(binaries == null || binaries.length == 0) return;


        class BinaryClassLoader extends ClassLoader
        {
            private File binaryDir;

            public BinaryClassLoader(File binaryDir, ClassLoader parent)
            {
                super(parent);
                this.binaryDir = binaryDir;
            }


            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException
            {
                try
                {
                    String clazzPath = binaryDir + File.separator + name + ".class";
                    byte[] rawClazzData = loadClassDataFromBinary(new File(clazzPath));

                    return defineClass(null, rawClazzData, 0, rawClazzData.length);
                }
                catch (IOException ignore)
                {
                    return super.findClass(name);
                }
            }


            private byte[] loadClassDataFromBinary(File clazzFile) throws IOException
            {
                return Files.readAllBytes(clazzFile.toPath());
            }
        }

        BinaryClassLoader loader = new BinaryClassLoader(binaryDir, ClassLoader.getSystemClassLoader());
        loadBinaries(binaries, loader);
    }

    public HiddenClass getHiddenClassObjectByKey(String key)
    {
        if(hiddenClasses.size() == 0) return null;

        for(Class clazz : hiddenClasses)
        {
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase()))
            {
                try
                {
                    Constructor<?>[] constructors = clazz.getDeclaredConstructors();
                    for(Constructor<?> constructor : constructors)
                    {
                        if(constructor.getParameterTypes().length == 0)
                        {
                            constructor.setAccessible(true);
                            return (HiddenClass) constructor.newInstance();
                        }
                    }
                }
                catch (InstantiationException | IllegalAccessException | InvocationTargetException ignore)
                {

                }
            }
        }

        return null;
    }


    private File[] getBinaries(File binaryDir)
    {
        if(binaryDir == null) return null;
        File[] binaries = binaryDir.listFiles();
        return binaries;
    }


    private void loadBinaries(File[] binaries, ClassLoader loader)
    {
        for(File binary : binaries)
        {
            try
            {
                String clazzBinaryName = binary.getName();
                String clazzBinaryNameWithoutExtension = clazzBinaryName.substring(0, clazzBinaryName.length() - 6);

                Class clazz = loader.loadClass(clazzBinaryNameWithoutExtension);

                if(isValidClazz(clazz))
                {
                    hiddenClasses.add(clazz);
                }
            }
            catch (ClassNotFoundException ignore)
            {

            }
        }
    }


    private boolean isValidClazz(Class clazz)
    {
        return hasHiddenClassInterface(clazz) && hasNoParametersConstructor(clazz);
    }


    private boolean hasHiddenClassInterface(Class clazz)
    {
        if(clazz == null) return false;

        Class<?>[] interfaces = clazz.getInterfaces();
        if(interfaces.length > 0)
        {
            for(Class<?> iface : interfaces)
            {
                if(HiddenClass.class.equals(iface))
                {
                    return true;
                }
            }
        }

        return false;
    }


    private boolean hasNoParametersConstructor(Class clazz)
    {
        if(clazz == null) return false;

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        if(constructors.length > 0)
        {
            for(Constructor<?> constructor : constructors)
            {
                if(constructor.getParameterTypes().length == 0)
                {
                    return true;
                }
            }
        }

        return false;
    }
}
