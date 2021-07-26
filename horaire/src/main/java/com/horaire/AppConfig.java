package com.horaire;
import java.util.Optional;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
 
//@Configuration
public class AppConfig {
 
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
        return server ->
            Optional.ofNullable(server)
                    .ifPresent(s -> s.addAdditionalTomcatConnectors(redirectConnector()));
    }
 
    private Connector redirectConnector() {
        Connector connector = new Connector("AJP/1.3");
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setRedirectPort(8443);
        connector.setSecure(false);
        connector.setAllowTrace(false);
        return connector;
    }
}