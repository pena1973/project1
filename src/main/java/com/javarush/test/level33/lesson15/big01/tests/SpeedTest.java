package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class SpeedTest
{
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids){

        Date timeStart = new Date();
        java.util.Iterator<String> itr = strings.iterator();
        while(itr.hasNext()){
        ids.add(shortener.getId(itr.next()));
        }
        Date timeFinish = new Date();

        return (timeFinish.getTime()-timeStart.getTime());
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings){

        Date timeStart = new Date();
        java.util.Iterator<Long> itr = ids.iterator();
        while(itr.hasNext()){
            strings.add(shortener.getString(itr.next()));
        }
        Date timeFinish = new Date();

        return (timeFinish.getTime()-timeStart.getTime());
    }
    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<String>();

        for (int i = 0; i <10000 ; i++)
        {
            origStrings.add(Helper.generateRandomString());
        }

        Set<String> str1 = origStrings;
        Set<String> str2 = origStrings;
        Set<Long> ids1 = new HashSet<Long>();
        Set<Long> ids2 = new HashSet<Long>();

        float timeids1 = getTimeForGettingIds(shortener1,str1,ids1);
        float timeids2 = getTimeForGettingIds(shortener2,str2,ids2);

        Assert.assertTrue(timeids1>timeids2);

        float timestr1 = getTimeForGettingStrings(shortener1, ids1,str1);
        float timestr2 = getTimeForGettingStrings(shortener2, ids2, str2);

        Assert.assertEquals(timestr1, timestr2, 5);
    }

}
