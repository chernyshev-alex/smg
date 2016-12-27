
package com.acme.smg;
 
import com.acme.smg.rest.TopicListResource;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.grizzly.http.embed.GrizzlyWebServer;
import com.sun.grizzly.http.servlet.ServletAdapter;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;

public class Application {
    
    static final Logger logger = Logger.getLogger(Application.class.getName());

    public static class AppConfig extends GuiceServletContextListener {
        @Override
        protected Injector getInjector() {
            return Guice.createInjector(new ServletModule(){
                @Override
                protected void configureServlets() {    
                    bind(TopicListResource.class);
                    bind(IPersistenceStorage.class).to(PersistenceStorage.class);
                    serve("/smg/*").with(GuiceContainer.class);
                }
            });
        }
    }
    
    @SuppressWarnings("serial")
    public static class DummySevlet extends HttpServlet { }
    
    public void run(Class configClazz) {
        
        int port = Integer.valueOf(System.getProperty("port"));
        
        GrizzlyWebServer server = new GrizzlyWebServer(port);
        
        ServletAdapter adapter = new ServletAdapter(new DummySevlet());
        adapter.addServletListener(configClazz.getName());
        adapter.addFilter(new GuiceFilter(), "GuiceFilter", null);
        server.addGrizzlyAdapter(adapter, new String[]{ "/" });
        
        try {
            server.start();
        } catch (IOException ex) {
            logger.severe(ex.getMessage());
        }
    }
    
    public static void main(String[] args)  {
        new Application().run(AppConfig.class);
    }
}
