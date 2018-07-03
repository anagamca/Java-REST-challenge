package com.interview.core.persist.dao;

import com.interview.core.persist.EntityManagerFactory;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.MappedSuperclass;

/**
 * Abstract Base Dao to abstract out the ease of database interaction on any given DAO
 */
@MappedSuperclass
public class CustomIdBaseDao extends BaseDao {
    private static Logger logger = LoggerFactory.getLogger(CustomIdBaseDao.class);

    /**
     * Abstracted Save method to persist the object to the database
     * @param <T> - Type to return
     * @return T, persisted object
     */
    public <T> T save() {
        EntityManager em = EntityManagerFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(this);
            transaction.commit();

        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            em.close();
        }
        return (T) this;
    }

    /**
     * Abstracted Update method to merge the object with an already persisted object in the database
     * @param <T> - Type to return
     * @return T, the newly merged object
     */
    public <T> T update() {
        EntityManager em = EntityManagerFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.merge(this);
            transaction.commit();
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            em.close();
        }
        return (T) this;
    }

    /**
     * Abstract delete method to remove an object fromm the database
     * @return IdDao, deleted object
     */
    public CustomIdBaseDao delete() {
        EntityManager em = EntityManagerFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(em.contains(this) ? this : em.merge(this));
            transaction.commit();
        } catch (Exception e) {
            logger.error("Error deleting entity due to " + e.getMessage() + " " + e.getCause());
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            em.close();
        }

        return this;
    }

    /**
     * Abstract method to retrieve an item by its id
     * @param id - long id of object
     * @param clazz - Class of object
     * @param <T> - Type to return
     * @return T, the object with class clazz with id of id
     */
    public static <T> T loadById(long id, Class<T> clazz) {
        EntityManager em = EntityManagerFactory.getEntityManager();

        T obj = null;
        try {
            obj = (T) em.find(clazz, id);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            em.close();
        }
        return obj;
    }

    /**
     * Abstract method to retrieve an item by its id
     * @param id - String id of object
     * @param clazz - Class of object
     * @param <T> - Type to return
     * @return T, the object with class clazz with id of id
     */
    public static <T> T loadById(String id, Class<T> clazz) {
        EntityManager em = EntityManagerFactory.getEntityManager();
        T obj = null;
        try {
            obj = (T) em.find(clazz, id);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            em.close();
        }
        return obj;
    }
}
