package org.mike.stubserver;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Set up two rest services:
 *   /rest/stub1?id=nnn returns hello, or GutenTag if id begins with 1.
 *   /rest/stub2?id=nnn returns hello.  
 * @author mike
 */
@Component
public class RestListener extends RouteBuilder {
	private static final Logger logger = LogManager.getLogger(RestListener.class);

	@Autowired
	private HelloFirer helloFirer;

    @Override
    public void configure() throws Exception {

        rest("stub1")
        	  .get().outType(String.class).to("direct:stub1");

        rest("stub2")
        	  .get().outType(String.class).to("direct:stub2");

        from("direct:stub1")
          .setBody(method(helloFirer, "getBody1"))
          .marshal().json(JsonLibrary.Jackson)
          ;
        
        from("direct:stub2")
          .setBody(method(helloFirer, "getBody2"))
          .marshal().json(JsonLibrary.Jackson)
        ;
    }

}