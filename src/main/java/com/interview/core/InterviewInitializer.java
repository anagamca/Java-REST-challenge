package com.interview.core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.jboss.logging.Logger;

/**
 * High level application initializer and thread manager
 */
public class InterviewInitializer {
    private static Logger logger = Logger.getLogger(InterviewInitializer.class);
    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

    public static void start(){
        logger.info("Initializing Core...");
        logger.info("Finished Initializing Core");
        //TODO bonus question: write a poller process that executes a print line every 30 seconds
        runScheduler();
       
    }
    
    
	@SuppressWarnings("unused")
	public static void runScheduler() {
		ScheduledFuture<?> scheduledFuture = executorService.scheduleWithFixedDelay(new Runnable() {
		    public void run() {
		    	logger.info("Hey I'm awake and doing something ....");
		    }
		}, 0, 30, TimeUnit.SECONDS);
	}
	
   
}
