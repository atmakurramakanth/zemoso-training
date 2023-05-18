package com.librarymanagementsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private String price;

    public Book(){}

    public Book(int id, String bookName, String authorName, String description, String price) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
        this.price = price;
    }

    public Book(String bookName, String authorName, String description, String price) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
