package com.javarush.test.level21.lesson02.task01;

/* Определяем адрес сети
1) Даны IP-адрес и маска подсети, необходимо вычислить адрес сети - метод getNetAddress.
Используйте операцию поразрядной конъюнкции (логическое И).
Пример:
IP-адрес:       11000000 10101000 00000001 00000010 (192.168.1.2)
Маска подсети:  11111111 11111111 11111110 00000000 (255.255.254.0)
Адрес сети:     11000000 10101000 00000000 00000000 (192.168.0.0)
2) Реализовать метод print, который выведет в консоль данные в двоичном коде
3) Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[] result = new byte[4];
        result[0] = (byte)(ip[0]& mask[0]);
        result[1] = (byte)(ip[1]& mask[1]);
        result[2] = (byte)(ip[2]& mask[2]);
        result[3] = (byte)(ip[3]& mask[3]);
        return result;
    }

    public static void print(byte[] bytes) {

        String s0 = Integer.toBinaryString((((int) bytes[0]<0) ? 256:0) +(int) bytes[0]);
        String s1 = Integer.toBinaryString((((int) bytes[1]<0) ? 256:0) +(int) bytes[1]);
        String s2 = Integer.toBinaryString((((int) bytes[2]<0) ? 256:0) +(int) bytes[2]);
        String s3 = Integer.toBinaryString((((int) bytes[3]<0) ? 256:0) +(int) bytes[3]);
        s0 =("00000000"+s0).substring(s0.length(),s0.length()+8);
        s1 =("00000000"+s1).substring(s1.length(),s1.length()+8);
        s2 =("00000000"+s2).substring(s2.length(),s2.length()+8);
        s3 =("00000000"+s3).substring(s3.length(),s3.length()+8);

        System.out.println(
                s0 + " " +
                s1 + " " +
                s2 + " " +
                s3);
    }
}
