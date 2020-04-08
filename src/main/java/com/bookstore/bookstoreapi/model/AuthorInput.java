package com.bookstore.bookstoreapi.model;

import java.io.Serializable;

public class AuthorInput implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthorInput(){}
    public AuthorInput(String name) {
        this.name = name;
    }
}
