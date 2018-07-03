package com.interview.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContext;

/**
 * Context Listener for the application, starting point and place for loading pre application configuration
 */
public class InterviewContextListener extends ContextLoaderListener {
    private static Logger logger = LoggerFactory.getLogger(ContextLoaderListener.class);

    @Override
    protected void customizeContext(ServletContext servletContext, ConfigurableWebApplicationContext applicationContext) {
        logger.info("Loading The Interview!");
        InterviewInitializer.start();
        logger.info("Interview fully initialized");
    }
}
