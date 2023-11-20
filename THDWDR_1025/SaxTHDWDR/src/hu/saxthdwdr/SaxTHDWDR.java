package hu.saxthdwdr;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SaxTHDWDR {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            // L�trehozzuk a SAXParserFactory-t
            SAXParserFactory factory = SAXParserFactory.newInstance();

            // L�trehozzuk a SAXParser-t
            SAXParser saxParser = factory.newSAXParser();

            // L�trehozzuk az esem�nykezel� keretet
            SaxHandler handler = new SaxHandler();

            // Be�ll�tjuk az esem�nykezel�t a SAXParser-re
            saxParser.parse("THDWDR_kurzusfelvetel.xml", handler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
	}

}

class SaxHandler extends DefaultHandler {
    boolean inElement = false; // jelzi, hogy jelenleg egy XML elemen bel�l vagyunk

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("start: " + qName + "{id:" + attributes.getValue("id") + "}");
        inElement = true;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("end: " + qName);
        inElement = false;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (inElement) {
            String content = new String(ch, start, length).trim();
            if (!content.isEmpty()) {
                System.out.println(content);
            }
        }
    }
}