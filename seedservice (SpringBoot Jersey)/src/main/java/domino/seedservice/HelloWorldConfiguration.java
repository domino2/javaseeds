package domino.seedservice;

import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloWorldConfiguration extends SpringBootServletInitializer {
    
	public static void main(String[] args) {
            
                new HelloWorldConfiguration()
                        .configure(new SpringApplicationBuilder(HelloWorldConfiguration.class))
                        .run(args);
	}

        @Bean
        public ServletRegistrationBean jerseyServlet() {
            
            ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/rest/*");
            registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
            return registration;
        }
}