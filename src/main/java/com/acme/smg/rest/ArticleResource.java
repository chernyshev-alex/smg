package com.acme.smg.rest;

import com.acme.smg.model.Article;
import com.acme.smg.IPersistenceStorage;
import com.google.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/***
  * Article of topic
  * 
  * http://host:port/site/topics/<topicId>/articles/<articleId>
  */
 public  class ArticleResource {

     String topicId, articleId;
     @Inject IPersistenceStorage storage;     

     @GET
     @Produces(MediaType.APPLICATION_JSON)
     public Article getJson() throws NotFoundException {
         return storage.getArticle(topicId, articleId);
     }

     @PUT
     @Consumes(MediaType.APPLICATION_JSON)
     public void putJson(Article article) {
         article.setId(articleId);
         storage.addArticle(topicId, article);
     }

     @DELETE
     public void delete() {
         storage.deleteArticle(topicId, articleId);
     }

     public ArticleResource build(String topicId, String articleId) {
         this.topicId = topicId;
         this.articleId = articleId;
         return this;
     }
 }