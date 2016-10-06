package com.javarush.test.level17.lesson10.home02;

/* Comparable
Реализуйте интерфейс Comparable<Beach> в классе Beach, который будет использоваться нитями.
*/

public class Beach implements Comparable<Beach> {
    volatile private String name;      //название
    volatile private float distance;   //расстояние
    volatile private int quality;    //качество
    public static void main(String[] args) throws Exception
    {
        Beach beach1 = new Beach("beach1", 123.6f, 8);
        Beach beach2 = new Beach("beach2", 124.6f, 7);
        Beach beach3 = new Beach("beach3", 124.6f, 8);

        System.out.println(beach1.compareTo(beach2));
       // System.out.println(beach2.compareTo(beach3));
        //System.out.println(beach1.compareTo(beach3));
    }

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public float getDistance() {
        return distance;
    }

    public synchronized void setDistance(float distance) {
        this.distance = distance;
    }

    public int getQuality() {
        return quality;
    }

    public synchronized void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    public synchronized int compareTo(Beach o)
    {
        int distanceParam = (int) (this.getDistance() - o.getDistance());
        int qualityParam = this.getQuality() - o.getQuality();
        return 10000 * name.compareTo(o.getName()) + 100 * distanceParam + qualityParam;


    }
}
