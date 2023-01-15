package cn.pxl.capture03.subsection01;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Demo01Xml {
    public static void doDemo() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        File file = new File("coreDemo2/src/main/java/cn/pxl/capture03/common/one.xml");
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //2.从url中读取 3.从输出流中读取
            //1.从xml文件中读取
            Document document = documentBuilder.parse(file);
            //获取根元素
            Element root = document.getDocumentElement();
            //获取根标签名
            System.out.println(root.getTagName());//h1
            //获取子标签集合
            NodeList childNodes = root.getChildNodes();
            //遍历所有子标签集合，这种情况，会把子标签，还有子标签之间的空白都输出。
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                System.out.println(item.getNodeName());
            }
            //遍历所有子标签集合，并且去除空格
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                //排除标签之间的空格
                if(item instanceof Element){
                    Element childElement = (Element) item;
                    System.out.println(childElement.getNodeName());
                    //获取标签的内容
                    Node firstChild = childElement.getFirstChild();
                    if(firstChild instanceof Text){
                        //输出xml文件的内容。
                        Text textStr = (Text) firstChild;
                        System.out.println(textStr.getData().trim());
                    }

                    //获取元素标签中的所有属性
                    NamedNodeMap attributes = childElement.getAttributes();
                    for (int j = 0; j < attributes.getLength(); j++) {
                        //获取属性
                        Node attribute = attributes.item(j);
                        //获取属性名
                        String nodeName = attribute.getNodeName();
                        System.out.println(nodeName);
                        //获取属性值
                        String nodeValue = attribute.getNodeValue();
                        System.out.println(nodeValue);
                    }
                    //知道属性名时，可以直接获取属性值
                    String name = childElement.getAttribute("name");
                    System.out.println(name);
                }
            }

        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
