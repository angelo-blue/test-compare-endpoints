package org.mike.stubserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * A very simple app, completely overkill with camel and spring boot, to
 * implement two rest services where the responses sometimes vary, for testing.
 * 
 * @author mike
 */
// exclude the default security this is just a stub.  
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class StubApplication {
	private static final Logger logger = LogManager.getLogger(StubApplication.class);

	public static void main(String[] args) {
		logger.debug("Starting app");
		SpringApplication.run(StubApplication.class, args);
	}

	/**
	 * Register the camel servlet for all urls starting rest/*
	 * @return
	 */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), "/rest/*");
        registration.setName("CamelServlet");
        return registration;
    }
}
