package com.acme.smg.rest;

import com.acme.smg.IPersistenceStorage;
import com.acme.smg.model.Topic;
import com.google.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

  /***
     * Topic resource
     * 
     * http://host:port/site/topics/<topicId>
     */
public class TopicResource {

    @Inject IPersistenceStorage storage;

    private String id;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Topic getJson() {
        return storage.getTopic(this.id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Topic topic) {
        storage.addTopic(topic);
    }

    @DELETE
    public void delete() {
        storage.deleteTopic(id);
    }

    public TopicResource build(String id) {
        this.id = id;
        return this;
    }
}
