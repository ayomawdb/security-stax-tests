import com.ctc.wstx.api.ReaderConfig;
import com.ctc.wstx.stax.WstxInputFactory;
import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.util.SecurityManager;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by ayoma on 6/21/17.
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        int ENTITY_EXPANSION_LIMIT = 0;

        //SAXParserFactory spf = SAXParserFactory.newInstance();
        XMLInputFactory factory = WstxInputFactory.newFactory();
        try {

            factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
            //factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
            factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception");
        }

        factory.setProperty("com.ctc.wstx.maxEntityDepth", 1);

        //entity expansion
        try {
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("sample.xml"));
            while (eventReader.hasNext()) {
                System.out.println(eventReader.next().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //external entity injection
        try{
            XMLEventReader eventReader1 = factory.createXMLEventReader(new FileReader("sample1.xml") );
            while(eventReader1.hasNext()) {
                System.out.println(eventReader1.next().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
