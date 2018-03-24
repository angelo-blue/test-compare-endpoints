package org.mike.stubserver;
import org.apache.camel.Header;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Determines germanity of the greeting. 
 * @author mike
 */
@Component
public class HelloFirer {
	private static final Logger logger = LogManager.getLogger(RestListener.class);

    public String getBody1(@Header("id") String id) {
    		String result = "Hello";
    		if (id.startsWith("1")) {
    			result = "GutenTag";
    		}
    		return result;
    }
    
    public String getBody2(@Header("id") String id) {
		String result = "Hello";
		return result;
    }
}