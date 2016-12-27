package com.acme.smg.rest;

import com.acme.smg.model.Article;
import com.acme.smg.PersistenceStorage;
import com.acme.smg.IPersistenceStorage;
import com.google.inject.Inject;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/***
  * Articles of topic resource
  * 
  * http://host:port/site/topics/<topicId>/articles
  */
public class ArticleListResource {

    String topicId;
    @Inject IPersistenceStorage storage;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Article> getJson() {
        return storage.getTopic(topicId).getArticles();
    }

    public ArticleListResource build(String id) {
        this.topicId = id;
        return this;
    }
}