package com.acme.smg.rest;

import com.acme.smg.model.IPersistenceStorage;
import com.acme.smg.PersistenceStorage;
import com.acme.smg.model.Topic;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Provides;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Root container resource, aka topics
 * http://host:port/site/topics
 */
@Path("/topics")
public  class TopicListResource {

    @Inject  Logger logger;
    
    @Context UriInfo context;

    @Inject IPersistenceStorage storage;

    //factories
    @Inject Provider<TopicResource>         topicResource;
    @Inject Provider<ArticleListResource>   articlesResource;
    @Inject Provider<ArticleResource>       articleResource;

    @Inject 
    public void setStorage(IPersistenceStorage storage) {
        this.storage = storage;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Topic> getJson() {
        return storage.getTopics().values();
    }   

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String content) {
        return Response.created(context.getAbsolutePath()).build();
    }

    @Provides
    @Path("{id}")
    public TopicResource getTopicResource(@PathParam("id") String id) {
        return  topicResource.get().build(id);
    }

    @Provides
    @Path("{id}/articles")
    public ArticleListResource getArticlesResource(@PathParam("id") String id) {
        return articlesResource.get().build(id);        }

    @Provides
    @Path("/{id}/articles/{articleId}")
    public ArticleResource getArticleResource(@PathParam("id") String topicId, 
                    @PathParam("articleId") String articleId) {
        return articleResource.get().build(topicId, articleId);
    } 

}


