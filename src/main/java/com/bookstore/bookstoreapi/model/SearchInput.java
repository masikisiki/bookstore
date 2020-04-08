package com.bookstore.bookstoreapi.model;

import java.io.Serializable;

public class SearchInput  implements Serializable {
    private String term;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
