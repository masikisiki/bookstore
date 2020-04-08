package com.bookstore.bookstoreapi.model;

import com.bookstore.bookstoreapi.auditing.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;

@Entity
public class Author extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String fullName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    @XmlElement(name = "author")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Author() {
    }

    public Author(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
