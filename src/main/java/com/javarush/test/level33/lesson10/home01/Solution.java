package com.javarush.test.level33.lesson10.home01;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* Создание класса по строке xml
Восстановите класс по переданной строке xml.
Класс должен быть в отдельном файле.
Метод getClassName должен возвращать восстановленный класс.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws JAXBException {
        String xmlData =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                        "<shop>\n" +
                        "    <goods>\n" +
                        "        <names>S1</names>\n" +
                        "        <names>S2</names>\n" +
                        "    </goods>\n" +
                        "    <count>12</count>\n" +
                        "    <profit>123.4</profit>\n" +
                        "    <secretData>String1</secretData>\n" +
                        "    <secretData>String2</secretData>\n" +
                        "    <secretData>String3</secretData>\n" +
                        "    <secretData>String4</secretData>\n" +
                        "    <secretData>String5</secretData>\n" +
                        "</shop>";

        StringReader reader = new StringReader(xmlData);

        JAXBContext context = JAXBContext.newInstance(getClassName());
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Object o = unmarshaller.unmarshal(reader);

        System.out.println(o.toString());
    }

    public static Class getClassName() {

        return new Shop().getClass();  //your class name
    }
// проверка
//        public static void main(String[] args) throws IOException, JAXBException
//        {
//            Shop shop=new Shop();
//            shop.names.add("S1");
//            shop.names.add("S2");
//            shop.count=12;
//            shop.profit=123.4;
//            shop.secretData.add("String1");
//            shop.secretData.add("String2");
//            shop.secretData.add("String3");
//            shop.secretData.add("String4");
//            shop.secretData.add("String5");
//            StringWriter writer = new StringWriter();
//            convertToXml(writer, shop);
//            System.out.println(writer.toString());
//        }
//        public static void convertToXml(StringWriter writer, Object obj) throws IOException, JAXBException
//        {
//            JAXBContext context = JAXBContext.newInstance(obj.getClass());
//            Marshaller marshaller = context.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            marshaller.marshal(obj, writer);
//        }


}
