package com.acme.smg.model;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class Topic implements Serializable {

    private static final String EMPTY_STRING = "";    
    
    private String id;
    private String title = EMPTY_STRING;
    // fits for rarely write, read often
    private final Set<Article>  articles = new CopyOnWriteArraySet<>();
        
    public Topic(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Article> getArticles() {
        return articles;
    }
    
    public static Topic getInstance(String id) {
        return new Topic(id);
    }

    public static Topic createWith(String id, String title) {
        Topic t = getInstance(id);
        t.setTitle(title);
        return t;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Topic)) {
            return false;
        }
        Topic other = (Topic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.smg.Topic[ id=" + id + " ]";
    }
    
}
