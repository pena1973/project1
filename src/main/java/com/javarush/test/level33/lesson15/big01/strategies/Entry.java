package com.javarush.test.level33.lesson15.big01.strategies;

import java.io.Serializable;
import java.util.Objects;

public class Entry implements Serializable
{
     Long key;
     String value;
     Entry next;
     int hash;
    public Entry(int hash, Long key, String value, Entry next)
    {
        this.hash = hash;
        this.value = value;
        this.next = next;
        this.key = key;
    }

      public Long getKey(){
        return this.key;
      }
        public String getValue(){
        return this.value;
    }

//        public int hashCode(){
//        return this.hash;
//    }
//        public String toString(){
//        return super.toString();
//    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
    }

//    @Override
//    public int hashCode() {
//        int result = key != null ? key.hashCode() : 0;
//        result = 31 * result + (value != null ? value.hashCode() : 0);
//        result = 31 * result + (next != null ? next.hashCode() : 0);
//        result = 31 * result + hash;
//        return result;
//    }
    @Override
    public String toString() {
        return "Entry{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", next=" + next +
                ", hash=" + hash +
                '}';
    }
    }

