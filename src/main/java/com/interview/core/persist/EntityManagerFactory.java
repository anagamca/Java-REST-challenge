package com.interview.core.persist;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Factory to produce the entity manager for the JPA layer
 */
public class EntityManagerFactory {
    private static Logger logger = LoggerFactory.getLogger(EntityManagerFactory.class);
    private static final javax.persistence.EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("CoreEntityManager");
        } catch (Exception e) {
            logger.error(e.getCause().getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static Session getSelfSession() { return getEntityManager().unwrap(Session.class); }

    public static Session getSession(EntityManager manager) { return manager.unwrap(Session.class); }
}
