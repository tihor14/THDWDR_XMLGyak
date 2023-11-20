package saxTHDWDR;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class XsdTHDWDR {

    public static void main(String[] args) {
        String xmlFileName = "THDWDR_kurzusfelvetel.xml";
        String xsdFileName = "THDWDR_kurzusfelvetel.xsd";

        if (validateXMLSchema(xsdFileName, xmlFileName)) {
            System.out.println("XSD Validation successful");
        } else {
            System.out.println("Unsuccessful validation");
        }
    }

    private static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            javax.xml.validation.Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
            return true;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }
}
