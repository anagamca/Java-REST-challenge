package com.interview.core.web;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Application initial configuration
 */
public class ApplicationConfig extends ResourceConfig {

    private final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);
    public ApplicationConfig() {
        this.packages("org.glassfish.jersey.examples.multipart")
                .register(MultiPartFeature.class);
        
        this.packages("com.interview.core.web")
        .register(new ApplicationBinder());
       
        
    }

}
