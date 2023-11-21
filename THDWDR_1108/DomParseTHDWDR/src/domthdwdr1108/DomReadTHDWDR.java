package domthdwdr1108;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DomReadTHDWDR {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("kurzusfelvetelTHDWDR.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // struktúra normalizalasa
            doc.getDocumentElement().normalize();

            // gyökérelem
            System.out.println("Gyokerelem: " + doc.getDocumentElement().getNodeName());

            // Gyökerelem attributumai
            Element rootElement = doc.getDocumentElement();
            System.out.println("Gyökerelem attributumai:");
            for (int i = 0; i < rootElement.getAttributes().getLength(); i++) {
                Node attribute = rootElement.getAttributes().item(i);
                System.out.println(attribute.getNodeName() + ": " + attribute.getNodeValue());
            }

            // gyerek elemek
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("\nElem: " + element.getNodeName());

                    // Attributumok feldolgozasa
                    System.out.println("Attributumok:");
                    for (int j = 0; j < element.getAttributes().getLength(); j++) {
                        Node attribute = element.getAttributes().item(j);
                        System.out.println(attribute.getNodeName() + ": " + attribute.getNodeValue());
                    }

                    // Gyerek elemek feldolgozasa
                    NodeList childNodes = element.getChildNodes();
                    for (int k = 0; k < childNodes.getLength(); k++) {
                        Node childNode = childNodes.item(k);

                        if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element childElement = (Element) childNode;
                            System.out.println(childElement.getNodeName() + ": " + childElement.getTextContent());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
