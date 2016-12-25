package com.acme.smg.model;

import java.io.Serializable;

public class Article implements Serializable {

    private static final String EMPTY_STRING = "";    
    
    private String id;
    private String author = EMPTY_STRING;
    private String text = EMPTY_STRING;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Article(String id) {
        this.id = id;
    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.smg.Article[ id=" + id + " ]";
    }
    
}
