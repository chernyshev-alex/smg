package com.acme.smg;

import com.acme.smg.model.IPersistenceStorage;
import com.acme.smg.rest.TopicListResource;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import org.junit.BeforeClass;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import java.util.Collection;
import javax.servlet.ServletContextEvent;
import javax.ws.rs.core.MediaType;
import org.junit.Assert;

public class ApplicationTest {
    
    public static class AppConfigTest extends GuiceServletContextListener {
        @Override
        protected Injector getInjector() {
            return Guice.createInjector(new ServletModule(){
                @Override
                protected void configureServlets() {    
                    bind(TopicListResource.class);
                    bind(IPersistenceStorage.class).to(PersistenceStorageTest.class);
                    serve("/smg/*").with(GuiceContainer.class);
                }
            });
        }       
        
        @Override
        public void contextInitialized(ServletContextEvent sce) {
            super.contextInitialized(sce);
            PersistenceStorageTest.buildExamples(4);
        }
    }

    private static final String BASE_URI = "http://localhost:8484/smg";
    Client client = Client.create();
       
    @BeforeClass
    public static void setUpClass() throws Exception {
        System.setProperty("port", "8484");
        Application app = new Application();
        app.run(new AppConfigTest().getClass());
      //app.setStorage();
    }

    @org.junit.Test
    public void testGetTopics() {
	WebResource webResource = client.resource(BASE_URI + "/topics");
        ClientResponse resp = webResource.accept(MediaType.APPLICATION_JSON)
                   .get(ClientResponse.class);
        
        Assert.assertTrue(resp.getStatus() == 200);
        
        Collection col = resp.getEntity(Collection.class);
        Assert.assertTrue(! col.isEmpty());
    }

    @org.junit.Test
    public void testGetTopic() {
        // TODO
    }
    
}
