/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pt2a.parsingXML.parsing;

import Pt2a.parsingXML.model.SAXHandler;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Luis Moa
 */
public class SAXParsing {

    public static void main(String[] args) {
        //Here we start the parsing
        SAXHandler saxHandler = parsingXMLwithSAX();
        //Showing up the deserialized objects
        showBooksAndTitles(saxHandler);
       
    }

    public static SAXHandler parsingXMLwithSAX() {

        SAXParserFactory parserFactory;

        SAXParser parser;

        //So this handler helps us to get XML tag-objects Deserialized
        //(from XML to JAVA objects)
        SAXHandler handler = null;

        try {

            //New parser sax's factory to build sax parsers
            parserFactory = SAXParserFactory.newInstance();
            //New parser object instance
            parser = parserFactory.newSAXParser();
            //New Handler to manage the xml parsing while is reading xml by handler
            handler = new SAXHandler();
            //Parser Configuration :: Load file to parse and which handler we'll use for it.
            parser.parse(ClassLoader.getSystemResourceAsStream("book1.xml"),
                    handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("Handler -> "+handler);
        return handler;
    }

    public static void showBooksAndTitles(SAXHandler saxHandler) {
        System.out.println("--->"+saxHandler.getBookList().size());
        saxHandler.getBookList().forEach((book) -> {
            System.out.println(book);
        });
    }

}
