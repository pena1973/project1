package com.javarush.test.level33.lesson15.big01.tests;


import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest
{
    public void testStorage(Shortener shortener){
        String s1 = Helper.generateRandomString();
        String s2 = Helper.generateRandomString();
        String s3 = s1.toString();
        Long id1 = shortener.getId(s1);
        Long id2 = shortener.getId(s2);
        Long id3 = shortener.getId(s3);

        Assert.assertNotEquals(id2,id1);
        Assert.assertNotEquals(id2,id3);

        Assert.assertEquals(id1,id3);

        String s1_1 = shortener.getString(id1);
        String s2_1 = shortener.getString(id2);
        String s3_1 = shortener.getString(id3);

        Assert.assertEquals(s1,s1_1);
        Assert.assertEquals(s2,s2_1);
        Assert.assertEquals(s3,s3_1);

    }
    @Test
    public void testHashMapStorageStrategy(){testStorage(new Shortener( new HashMapStorageStrategy()));}
    @Test
    public void testOurHashMapStorageStrategy(){testStorage(new Shortener( new OurHashMapStorageStrategy()));}
    @Test
    public void testFileStorageStrategy(){testStorage(new Shortener( new FileStorageStrategy()));}
    @Test
    public void testHashBiMapStorageStrategy(){testStorage(new Shortener( new HashBiMapStorageStrategy()));}
    @Test
    public void testDualHashBidiMapStorageStrategy(){testStorage(new Shortener( new DualHashBidiMapStorageStrategy()));}
    @Test
    public void testOurHashBiMapStorageStrategy(){testStorage(new Shortener( new OurHashBiMapStorageStrategy()));}
}
