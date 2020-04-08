package com.bookstore.bookstoreapi.model;

import com.bookstore.bookstoreapi.auditing.Auditable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement
@Entity
public class Book extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String category;
    private int year;
    private double price;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<Author> authors;

    public Book() {
    }

    public Book(String title, String category, double price, int year) {
        this.title = title;
        this.category = category;
        this.price = price;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    @XmlAttribute
    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    @XmlElement
    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", authors=" + authors +
                '}';
    }
}

