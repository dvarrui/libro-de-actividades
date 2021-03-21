/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pt2a.parsingXML.model;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX-Handler to parse our XML-files.
 *
 * This handler gets triggered when find those tags we want to be parsed.
 *
 * (we tell handler to be triggered when it reads a start tag , end tag and
 * text-content)
 *
 * @author Luis ML
 */
public class SAXHandler extends DefaultHandler {

    private List<Book> bookList = new ArrayList<>();
    private List<Title> titleList = new ArrayList<>();

    private Title title;
    private Book book;

    private String content;

    /**
     * It gets triggered when Handler reads the start of an element (a tag
     * element -> book tag)
     *
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        //If a tag Start is found (open tag == <hola>) we have to instanciate its object reference
        if (qName.equals("book")) {
            this.book = new Book();
            this.book.setCategory(attributes.getValue("category"));
        }
    }

    /**
     * It's triggered when SAXHandler reads an end-tag
     *
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        switch (qName) {
            case "title":
                this.title=new Title();
                title.setTitle(content);

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

            break;
        }
        
        this.bookList.add(book);
        this.titleList.add(title);
    }

    /**
     * If the SAXHandler finds a text (inside a tag, between start and end tag)
     *
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }

    public SAXHandler() {
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Title> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<Title> titleList) {
        this.titleList = titleList;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    

}
