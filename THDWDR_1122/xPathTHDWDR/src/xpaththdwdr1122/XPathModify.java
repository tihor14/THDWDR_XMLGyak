package xpaththdwdr1122;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;


public class XPathModify {

    public static void main(String[] args) {
        try {
            // Parse XML document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("orarend_THDWDR.xml");

            // M�dos�t�sok elv�gz�se
            modifyDocument(document);

            // Eredm�ny ki�r�sa a konzolra
            printDocument(document);

            // Eredm�ny ki�r�sa f�jlba
            saveDocument(document, "orarend_THDWDR1.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void modifyDocument(Document document) {
        try {
            // M�dos�t�s 1: Hallgat� nev�nek megv�ltoztat�sa
            Node hallgatoNode = document.getElementsByTagName("hallgato").item(0);
            Node hnevNode = findNode(hallgatoNode, "hnev");
            hnevNode.setTextContent("Kov�cs G�bor");

         // M�dos�t�s 2: �j kurzus hozz�ad�sa
            Node kurzusokNode = document.getElementsByTagName("kurzusok").item(0);
            Node newKurzusNode = document.createElement("kurzus");
            newKurzusNode.setTextContent("\n");
            Attr idAttribute = document.createAttribute("id");
            idAttribute.setValue("THDWDR");
            ((Element) newKurzusNode).setAttributeNode(idAttribute);  // Castol�s Attr t�pusra

            Node kurzusnevNode = document.createElement("kurzusnev");
            kurzusnevNode.setTextContent("�j kurzus");
            newKurzusNode.appendChild(kurzusnevNode);

            Node kreditNode = document.createElement("kredit");
            kreditNode.setTextContent("3");
            newKurzusNode.appendChild(kreditNode);

            Node helyNode = document.createElement("hely");
            helyNode.setTextContent("Inf/105");
            newKurzusNode.appendChild(helyNode);

            Node idopontNode = document.createElement("idopont");
            idopontNode.setTextContent("Cs�t�rt�k 10-12");
            newKurzusNode.appendChild(idopontNode);

            Node oktatoNode = document.createElement("oktato");
            oktatoNode.setTextContent("Dr. Nagy P�ter");
            newKurzusNode.appendChild(oktatoNode);

            kurzusokNode.appendChild(newKurzusNode);

            // M�dos�t�s 3: Kurzus t�rl�se
            Node kurzusToDelete = findKurzus(document, "WEB technol�gi�k1");
            if (kurzusToDelete != null) {
                kurzusToDelete.getParentNode().removeChild(kurzusToDelete);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Node findNode(Node parent, String nodeName) {
        NodeList nodes = parent.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeName().equals(nodeName)) {
                return node;
            }
        }
        return null;
    }

    private static Node findKurzus(Document document, String kurzusnev) {
        NodeList kurzusok = document.getElementsByTagName("kurzus");
        for (int i = 0; i < kurzusok.getLength(); i++) {
            Node kurzus = kurzusok.item(i);
            Node kurzusnevNode = findNode(kurzus, "kurzusnev");
            if (kurzusnevNode != null && kurzusnevNode.getTextContent().equals(kurzusnev)) {
                return kurzus;
            }
        }
        return null;
    }

    private static void printDocument(Document document) {
        try {
            // Konzolra �r�s
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(System.out);

            transformer.transform(domSource, streamResult);
            System.out.println("\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveDocument(Document document, String fileName) {
        try {
            // F�jlba �r�s
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(fileName);

            transformer.transform(domSource, streamResult);
            System.out.println("A m�dos�tott dokumentum el lett mentve a " + fileName + " f�jlba.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
