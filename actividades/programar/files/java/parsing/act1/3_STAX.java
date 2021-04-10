package Pt2a.parsingXML.parsing;

import Pt2a.parsingXML.model.Book;
import Pt2a.parsingXML.model.Title;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author Luis Moa
 */
public class StAXParsing {

    static List<Book> bookList = null;
    static List<Title> titleList = null;
    static Book book = null;
    static Title title = null;
    static String tagContent = "";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            parsingXMLwithStAX();
            showBooksAndTitles();

        } catch (XMLStreamException xmlE) {
            xmlE.printStackTrace();
        }
    }

    public static void parsingXMLwithStAX() throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newInstance();

        XMLStreamReader xmlReader = factory.createXMLStreamReader(ClassLoader.getSystemResourceAsStream("book1.xml"));

        //We itearate each element read from the parser
        while (xmlReader.hasNext()) {
            //We manage the following event (which kind of event will be)
            int event = xmlReader.next();

            //We can find 3 event-types :start ,end, characters (as SAX-Handler has)
            switch (event) {

                case XMLStreamConstants.START_ELEMENT:
                    manageStartElement(xmlReader);
                    break;
                case XMLStreamConstants.CHARACTERS:
                    tagContent = xmlReader.getText().trim();
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    manageEndElement(xmlReader);
                    break;
                /**
                 * Trobem també : case XMLStreamConstants.START_DOCUMENT:
                 */
            }

        }
    }

    public static void manageStartElement(XMLStreamReader xmlReader) {
        manageBookParsingTag(xmlReader);
        manageTitleParsingTag(xmlReader);
    }

    public static void manageBookParsingTag(XMLStreamReader xmlReader) {
        //If the start tag is found then we create our List (listBook)
        //this tag is the parent one which wraps books within
        if (xmlReader.getLocalName().equals("bookstore")) {
            bookList = new ArrayList<>();
            titleList = new ArrayList<>();//We need to create this one too at the beginning to add titles
        }
        //Then if the parser finds a book (inside bookstore parent tag)
        //we need to add this one to the list we already instantiated
        if (xmlReader.getLocalName().equals("book")) {
            book = new Book();
            //We search for the first attribute wheather it has the persed tag
            book.setCategory(xmlReader.getAttributeValue(0));
        }
    }

    public static void manageTitleParsingTag(XMLStreamReader xmlReader) {
        //I just figured out that we need to instiantiate each object on the StartElement state
        //so, then each attribute must be deserialized from this state or an java.lang.IllegalStateException
        //will raise
        if (xmlReader.getLocalName().equals("title")) {
            //System.out.println("Hey! I found a title!!!");
            title = new Title();

            if (xmlReader.isAttributeSpecified(0)) {
                //System.out.println("Title attribute found!");
                title.setLang(xmlReader.getAttributeLocalName(0));
            }
        }

    }

    public static void manageEndElement(XMLStreamReader xmlReader) {
        //Inside book tag we have child-nodes. We need to filter them and
        //add to our object.[Deserialized data -> Java Object]
        switch (xmlReader.getLocalName()) {
            case "title":
                //We cannot instiantiate here (a different state than START_ELEMENT) the attributes
                title.setTitle(tagContent);
                //Then we add this one to our book object
                book.setTitle(title);
                break;
            case "author":
                book.setAuthor(tagContent);
                break;
            case "year":
                book.setYear(Integer.parseInt(tagContent));
                break;
            case "price":
                book.setPrice(Float.parseFloat(tagContent));
                break;
            case "book":
                //This happens when it ends the book-tag (It reads so the </book>) , 
                //so we have to add the object deserialized to our list
                bookList.add(book);
                titleList.add(title);
                break;

        }
    }

    public static void showBooksAndTitles() {
        bookList.forEach((book) -> {
            System.out.println(book);
        });
    }

}