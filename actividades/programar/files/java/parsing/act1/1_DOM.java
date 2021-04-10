package Pt2a.parsingXML.parsing;

import Pt2a.parsingXML.model.Book;
import Pt2a.parsingXML.model.Title;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author Luis Moa
 */
public class DOMParsing {

    public static void main(String[] args) {

        //Get books and titles from parsing with DOM
        HashMap<String, List<Object>> bookTitleMap = parsingXMLBook();
        //Showing up books and titles
        showBooksAndTitles(bookTitleMap);
    }

    /**
     * Starts the parsing XML with DOM parser and initializes object
     * deserialization from the XML.
     *
     * @return Objects deserialized
     */
    public static HashMap<String, List<Object>> parsingXMLBook() {
        //Creating DOM builder factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //Creating the variable to use it after try-catch
        DocumentBuilder builder;
        HashMap<String, List<Object>> bookTitleMap = null;
        try {
            // DOM builder Instantition
            builder = factory.newDocumentBuilder();

            //Load the xml file and Parse direclty (because of DOM works like this)
            Document document = builder.parse(ClassLoader.getSystemClassLoader().getResourceAsStream("book1.xml"));
            //DOMParsing.class.getResourceAsStream(folder\...\...) -> per usar les barres en comptes dels punts de java

            bookTitleMap = deserializingBookXML(document);

        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(DOMParsing.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bookTitleMap;
    }

    /**
     *
     * @return
     */
    public static HashMap<String, List<Object>> deserializingBookXML(Document document) {

        //Declaring how data will be collect all together
        List<Book> llistaBooks = new ArrayList<>();
        List<Title> llistaTitles = new ArrayList<>();
        HashMap<String, List<Object>> bookTitleMap = new HashMap<String, List<Object>>() {
            {
                put("Titles", (List<Object>) (Object) llistaTitles);
                put("Books", (List<Object>) (Object) llistaTitles);
            }
        };

        //Getting the list of parsed nodes and its childs
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        //Reading and managing each child-node by looping it
        for (int i = 0; i < nodeList.getLength(); i++) {

            //For each node <book> we make
            //1. capture the node itself (anyone /lo que sea que lea)
            Node node = nodeList.item(i);
            //2. We filter each node which is an Element (element ==> ex <html>)
            if (node instanceof Element) {
                //3. Creating the objects which will  be deserialize from XML (bookstore child == <book>)
                Book book = new Book();

                //4. Filtering node-attributes (which we want to retrieve)
                if (((Element) node).hasAttribute("category")) {
                    //4.1. From books we want to search for category attr
                    book.setCategory(((Element) node).getAttribute("category"));
                }

                // reading and adding nodes (book node and its child's)
                getBookChilds(node, book, bookTitleMap);

            }

        }

        return bookTitleMap;
    }

    /**
     * It searches book child nodes within XML parsed and deserialized each book
     * into book object. And it does the same with title .
     *
     * @param node Node object to search book-child (this node object is
     * actually a book node.
     *
     * @param book Book object to persist in memory and show up books which were
     * found
     * @param bookTitleMap HashMap which stores books and titles objects within
     * a lists
     */
    public static void getBookChilds(Node node, Book book, HashMap<String, List<Object>> bookTitleMap) {
        //5. Getting the list of parsed nodes (book's child-nodes)
        NodeList childNodes = node.getChildNodes();

        for (int j = 0; j < childNodes.getLength(); j++) {
            //We prepare the object (for deserialize) Title which belongs inside book (1-node level low/inside)
            Title title = new Title();
            //6. We select a node from the parsed NodeList
            Node cNode = childNodes.item(j);

            //7. We filter which elements are Elements itselves (<element>)
            if (cNode instanceof Element) {

                //8. Title tag attribute (I use an 'if' whether the XML title (one or more) doesnt have attribute)
                //So I avoid a try-catch using if-else here
                if (((Element) cNode).hasAttribute("lang")) {
                    title.setLang(((Element) cNode).getAttribute("lang"));
                }

                //9. Extracting CDATA or text between tags: <tag>hola a </tag>
                String content = cNode.getLastChild().getTextContent().trim();

                //10. We filter node-names to filter each book property
                //and then retrieve all the data within the tags
                switch (cNode.getNodeName()) {

                    case "title":
                        title.setTitle(content);
                        bookTitleMap.get("Titles").add(title);
                        book.setTitle(title);
                        break;
                    case "author":
                        book.setAuthor(content);
                        break;
                    case "year":

                        try {
                        book.setYear(Integer.parseInt(content));
                    } catch (NumberFormatException ex) { // handle your exception
                        System.out.println(ex);
                    }

                    break;
                    case "price":

                        try {
                        book.setPrice(Float.parseFloat(content));
                    } catch (NumberFormatException ex) { // handle your exception
                        System.out.println(ex);
                    }
                    bookTitleMap.get("Books").add(book);
                    break;
                }

            }
        }
    }

    /**
     * It simply shows books and title which were parsed and then deserialized
     * form XML book1
     *
     * @param bookTitleMap HashMap with books list and titles list
     */
    public static void showBooksAndTitles(HashMap<String, List<Object>> bookTitleMap) {
        if (bookTitleMap != null) {
            if (bookTitleMap.get("Books") != null && bookTitleMap.get("Titles") != null) {

                //No se perque s'imprimeix el title (objecte) si al toString de llibre no te Title (tal qual)
                bookTitleMap.get("Books").forEach((book) -> {
                    System.out.println(book);
                });

            } else {
                System.out.println("No books found!");
            }
        }
    }
}