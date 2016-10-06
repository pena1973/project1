package com.javarush.test.level33.lesson10.bonus01;
//import org.w3c.dom.Document;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.InputSource;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlType;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import java.io.StringReader;
//import java.io.StringWriter;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.regex.Pattern;

/* Комментарий внутри xml
Реализовать метод toXmlWithComment, который должен возвращать строку - xml представление объекта obj.
В строке перед каждым тэгом tagName должен быть вставлен комментарий comment.
Сериализация obj в xml может содержать CDATA с искомым тегом. Перед ним вставлять комментарий не нужно.

Пример вызова:  toXmlWithComment(firstSecondObject, "second", "it's a comment")
Пример результата:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<first>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second><![CDATA[need CDATA because of < and >]]></second>
    <!--it's a comment-->
    <second/>
</first>
*/

public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) { try {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, writer);

        final String givenXmlCode = writer.toString();
        Document document = convertStringToDocument(givenXmlCode);
        Element element = document.getDocumentElement();

        NodeList nodeList = document.getElementsByTagName("*");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeName().equals(tagName)) {
                Comment coment = document.createComment(comment);
                element.insertBefore(coment, node);
            }
            replaceTextWithCDATA(node, document);
        }

        String s = convertDocumentToString(document);

        return s;
    }
    catch (JAXBException e) {}
        return null;

    }

    private static String convertDocumentToString(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            // below code to remove XML declaration
            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            doc.setXmlStandalone(false);
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try
        {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource( new StringReader( xmlStr ) ) );
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static void replaceTextWithCDATA(Node node, Document doc) {
        if ((node.getNodeType() == 3) && (Pattern.compile("[<>&'\"]").matcher(node.getTextContent()).find())) {
            Node cnode = doc.createCDATASection(node.getNodeValue());
            node.getParentNode().replaceChild(cnode, node);
        }
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            replaceTextWithCDATA(list.item(i), doc);
        }
    }
}

//  мое решение - не проходит
//public class Solution {
//    public static void main(String[] args) throws Exception
//        {
//            String result = toXmlWithComment(new AnExample(), "needCDATA", "it's a comment - <needCDATA>");
//            System.out.println(result);
//        }
//
//    @XmlType(name = "anExample")
//    @XmlRootElement
//    public static class AnExample {
//        public String[] needCDATA = new String[]{"need CDATA because of < and >", ""};
//    }
//
//    public static String toXmlWithComment(Object obj, String tagName, String comment) throws Exception
//    {
//        JAXBContext context = JAXBContext.newInstance(obj.getClass());
//        Marshaller marshaller = context.createMarshaller();
//
//        StringWriter stringWriter = new StringWriter();
//        marshaller.marshal(obj, stringWriter);
//        stringWriter.close();
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document document = builder.parse(new InputSource(new StringReader(stringWriter.toString())));
//        document.setXmlStandalone(false);
//
//        changeTextNodesToCdataNodes(document, document);
//        NodeList list = document.getElementsByTagName(tagName);
//
//        for (int i = 0; i < list.getLength(); i++)
//            list.item(i).getParentNode().insertBefore(document.createComment(comment), list.item(i));
//
//        Transformer transformer = TransformerFactory.newInstance()
//                .newTransformer();
//        stringWriter = new StringWriter();
//        DOMSource source = new DOMSource(document);
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//        StreamResult result = new StreamResult(stringWriter);
//
//        transformer.transform(source, result);
//        stringWriter.close();
//        return stringWriter.toString();
//    }
//
//    private static void changeTextNodesToCdataNodes(Node firstNode, Document ownerDocument)
//    {
//
//        if (firstNode.hasChildNodes())
//        {
//            NodeList children = firstNode.getChildNodes();
//
//            for (int i = 0; i < children.getLength(); i++)
//            {
//                Node node = children.item(i);
//                if (node.getNodeType() == Node.TEXT_NODE)
//                {
//                    if (node.getTextContent().matches(".*[<>&].*"))
//                        node.getParentNode().replaceChild(ownerDocument.createCDATASection(node.getTextContent()), node);
//                } else
//                {
//                    changeTextNodesToCdataNodes(node, ownerDocument);
//                }
//            }
//        }
//        return;
//    }
//
//}
