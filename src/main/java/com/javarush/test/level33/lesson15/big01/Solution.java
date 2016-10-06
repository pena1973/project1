package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.HashSet;
import java.util.Set;
import java.util.Date;
public class Solution
{
   public static Set<Long> getIds(Shortener shortener, Set<String> strings)
       {
           Set<Long> ids = new HashSet<Long>();

           java.util.Iterator<String> stringIterator = strings.iterator();
           while(stringIterator.hasNext()) {
               String element = stringIterator.next();
               ids.add(shortener.getId(element));
           }
           return ids;
       }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){

            Set<String> sts = new HashSet<String>();

            java.util.Iterator<Long> longIterator = keys.iterator();
            while(longIterator.hasNext()) {
                Long element = longIterator.next();
                sts.add(shortener.getString(element));
            }
            return sts;
        }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());

            Set<String> strings1 = new HashSet<String>();
            Set<String> strings2 = new HashSet<String>();
            for (int i = 0; i < elementsNumber; i++)
            {
                strings1.add(Helper.generateRandomString());
            }
            Shortener shortener = new Shortener(strategy);
            //-------
            Date timeStart = new Date();
            Set<Long> idies = getIds(shortener,strings1);
            Date timeFinish = new Date();

            int timeExs = (int) (timeFinish.getTime()-timeStart.getTime());
            Helper.printMessage("Время выполнения getIds "+ timeExs);
            //-------
            timeStart = new Date();
            strings2 = getStrings(shortener,idies);
            timeFinish = new Date();

            timeExs = (int) (timeFinish.getTime()-timeStart.getTime());
            Helper.printMessage("Время выполнения getStrings "+ timeExs);

            //-------
            if (strings1.size() != strings2.size())
            {
                Helper.printMessage("Тест не пройден.");
            }
            else {

                java.util.Iterator<String> stringIterator = strings1.iterator();
                while(stringIterator.hasNext()) {
                    String element = stringIterator.next();
                    if (! strings2.contains(element))
                    {
                        Helper.printMessage("Тест не пройден.");
                        break;
                    }
                }
                Helper.printMessage("Тест пройден.");
            }
        }

    public static void main(String[] args){

       HashMapStorageStrategy hashMapStorageStrategy  = new HashMapStorageStrategy();
       testStrategy( hashMapStorageStrategy, 100L);

       OurHashMapStorageStrategy ourHashMapStorageStrategy  = new OurHashMapStorageStrategy();
       testStrategy( ourHashMapStorageStrategy, 100L);

        FileStorageStrategy FileStorageStrategy  = new FileStorageStrategy();
       testStrategy( FileStorageStrategy, 100L);

       OurHashBiMapStorageStrategy ourHashBiMapStorageStrategy  = new OurHashBiMapStorageStrategy();
       testStrategy( ourHashBiMapStorageStrategy, 100L);

        HashBiMapStorageStrategy HashBiMapStorageStrategy  = new HashBiMapStorageStrategy();
        testStrategy( HashBiMapStorageStrategy, 100L);

        DualHashBidiMapStorageStrategy dualHashBidiMapStorageStrategy  = new DualHashBidiMapStorageStrategy();
        testStrategy( dualHashBidiMapStorageStrategy, 100L);

//
//        FileBucket fileBucket = new FileBucket(FileSystems.getDefault().getPath("C:\\Users\\Sid927\\Desktop\\java\\"));
//        Entry  entry1 = new Entry(111,  01L,  "kjglkgl", null);
//        Entry  entry2 = new Entry(111,  02L,  "zxczl", entry1);
//        Entry  entry3 = new Entry(111,  03L,  "654156", entry2);
//        fileBucket.putEntry(entry3);
//        Entry entry4 = fileBucket.getEntry();
        }
}
