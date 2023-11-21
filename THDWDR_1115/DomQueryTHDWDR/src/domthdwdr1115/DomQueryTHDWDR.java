package domthdwdr1115;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DomQueryTHDWDR {

    public static void main(String[] args) {
        try {
            // XML fájl beolvasása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("kurzusfelvetelTHDWDR.xml"));
            document.getDocumentElement().normalize();

            System.out.println("1. feladat: Kérdezze le a felvett kurzusok nevét egy listába, majd írja a konzolra!");
            List<String> kurzusNevek = getKurzusNevek(document);
            System.out.println("Kurzusnév: " + kurzusNevek);

            System.out.println("\n\n2. feladat: Kérdezze le a kurzusfelvetelTHDWDR.xml dokumentum elsõ példányát (hallgató \r\n"
            		+ "adatait tartalmazza) és írja ki strukturált formában a konzolra és egy fájlba.");
            queryAndPrintFirstHallgato(document, "strukturalthdwdr.txt");

            System.out.println("\n\n3. feladat: Kérdezze le a kurzusokat oktatók neveit listába, majd írja a konzolra.");
            List<String> oktatoNevek = getOktatoNevek(document);
            System.out.println("Oktató nevek: " + oktatoNevek);

            System.out.println("\n\n4. feladat: Tervezzen meg egy összetett lekérdezést.");
            // azok a kurzusok, amelyeket "Dr. Hornyák Olivér" oktat
            queryAndPrintKurzusokByOktato(document, "Dr. Hornyák Olivér");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static List<String> getKurzusNevek(Document document) {
        List<String> kurzusNevek = new ArrayList<>();
        NodeList kurzusNodeList = document.getElementsByTagName("kurzusnev");
        for (int i = 0; i < kurzusNodeList.getLength(); i++) {
            Node node = kurzusNodeList.item(i);
            kurzusNevek.add(node.getTextContent());
        }
        return kurzusNevek;
    }

    private static void queryAndPrintFirstHallgato(Document document, String outputFileName) {
        NodeList hallgatoNodeList = document.getElementsByTagName("hallgato");
        if (hallgatoNodeList.getLength() > 0) {
            Node hallgatoNode = hallgatoNodeList.item(0);
            printNodeDetails(hallgatoNode);
            writeNodeDetailsToFile(hallgatoNode, outputFileName);
        }
    }

    private static List<String> getOktatoNevek(Document document) {
        List<String> oktatoNevek = new ArrayList<>();
        NodeList oktatoNodeList = document.getElementsByTagName("oktato");
        for (int i = 0; i < oktatoNodeList.getLength(); i++) {
            Node node = oktatoNodeList.item(i);
            oktatoNevek.add(node.getTextContent());
        }
        return oktatoNevek;
    }


    private static void queryAndPrintKurzusokByOktato(Document document, String oktatoName) {
        NodeList kurzusNodeList = document.getElementsByTagName("kurzus");
        for (int i = 0; i < kurzusNodeList.getLength(); i++) {
            Node kurzusNode = kurzusNodeList.item(i);
            NodeList childNodes = kurzusNode.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node childNode = childNodes.item(j);
                if ("oktato".equals(childNode.getNodeName()) && oktatoName.equals(childNode.getTextContent())) {
                    System.out.println("\nKurzus, amit oktat " + oktatoName + ":");
                    printNodeDetails(kurzusNode);
                }
            }
        }
    }

    private static void printNodeDetails(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println("\nNode neve: " + node.getNodeName());
            if (node.hasAttributes()) {
                NamedNodeMap attributes = node.getAttributes();
                for (int i = 0; i < attributes.getLength(); i++) {
                    Node attribute = attributes.item(i);
                    System.out.println("Attribútum: " + attribute.getNodeName() + ", Érték: " + attribute.getNodeValue());
                }
            }
            System.out.println("Node tartalma: " + node.getTextContent());
        }
    }

    private static void writeNodeDetailsToFile(Node node, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writeNodeDetailsToFileRecursive(node, writer, 0);
            System.out.println("\nNode adatai kiírva a fájlba: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeNodeDetailsToFileRecursive(Node node, BufferedWriter writer, int level) throws IOException {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            writer.write(getIndentation(level) + "Node neve: " + node.getNodeName());
            writer.newLine();
            if (node.hasAttributes()) {
                NamedNodeMap attributes = node.getAttributes();
                for (int i = 0; i < attributes.getLength(); i++) {
                    Node attribute = attributes.item(i);
                    writer.write(getIndentation(level + 1) + "Attribútum: " + attribute.getNodeName() + ", Érték: " + attribute.getNodeValue());
                    writer.newLine();
                }
            }
            writer.write(getIndentation(level + 1) + "Node tartalma: " + node.getTextContent());
            writer.newLine();
        }

        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            writeNodeDetailsToFileRecursive(childNode, writer, level + 1);
        }
    }

    private static String getIndentation(int level) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indentation.append("  ");
        }
        return indentation.toString();
    }
}


