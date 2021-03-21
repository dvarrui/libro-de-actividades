package Pt2a.parsingXML.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Luis ML
 */
public class Book {

    private String category;
    private Title title;
    private String author;
    private int year;
    private float price;

    public Book() {
    }

    public Book(String category, Title title, String author, int year, float price) {
        this.category = category;
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "\nBook ::\n\tcategory=" + category + "\n\ttitle=" + title.getTitle() + "\n\tlanguage= " + title.getLang() + "\n\tauthor=" + author + "\n\tyear=" + year + "\n\tprice=" + price + '\n';
    }

}
