package web.xml;

import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class AnalizarXMLconSAX {

	class PeopleHandler extends DefaultHandler {
		boolean parent = false;
		boolean kids = false;
		
		public void startElement(String nsURI, String localName, String rawName, Attributes attributes) throws SAXException {
			if (rawName.equalsIgnoreCase("name")) parent = true;
			if (rawName.equalsIgnoreCase("children")) kids=true;
		}
		
		public void characters(char[] ch, int start, int length) {
			if (parent) {
				System.out.println("name     :"+new String(ch,start,length));
				parent=false;
			} else if (kids) {
				System.out.println("children :"+new String(ch,start,length));
				kids=false;
			}
		}
		
		public PeopleHandler() throws org.xml.sax.SAXException {
			super();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		new AnalizarXMLconSAX(args);
	}

	public AnalizarXMLconSAX(String[] args) throws SAXException, IOException {
		XMLReader parser = XMLReaderFactory
				.createXMLReader("org.apache.xerces.parsers.SAXParser");
		parser.setContentHandler(new PeopleHandler());
		parser.parse(args.length ==1 ? args[0] : "/home/david/java.dvr/canarias.pry/src/david/canarias/xml/people.xml");
	}
}
