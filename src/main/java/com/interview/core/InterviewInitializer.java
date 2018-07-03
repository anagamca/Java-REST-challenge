package com.interview.core;

import org.jboss.logging.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * High level application initializer and thread manager
 */
public class InterviewInitializer {
    private static Logger logger = Logger.getLogger(InterviewInitializer.class);
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void start() {
        logger.info("Initializing Core...");
        logger.info("Finished Initializing Core");
        //TODO bonus question: write a poller process that executes a print line every 30 seconds
    }
}
