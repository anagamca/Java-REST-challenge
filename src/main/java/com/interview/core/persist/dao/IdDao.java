package com.interview.core.persist.dao;

import com.interview.core.persist.EntityManagerFactory;
import com.interview.core.persist.dao.people.PersonDao;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

/**
 * Abstract Base Dao to abstract out the ease of database interaction on any given DAO
 */
@MappedSuperclass
public class IdDao extends BaseDao {
   
	private static Logger logger = LoggerFactory.getLogger(IdDao.class);

    /**
     * Abstracted Id so that not all DAOs have to implement the generated identifier
     */
    @Id
    @GeneratedValue(
        strategy= GenerationType.AUTO, 
        generator="native"
    )
    @GenericGenerator(
        name = "native", 
        strategy = "native"
    )
    private long id;

    public long getId() {
        return id;
    }

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
    public IdDao delete() {
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
    
    /**
     * Abstract method to retrieve an item by its id
     * @param id - String id of object
     * @param clazz - Class of object
     * @param <T> - Type to return
     * @return T, the object with class clazz with id of id
     */
	public static <T> List<T> findAll(String table, Class<T> clazz) {
		EntityManager em = EntityManagerFactory.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> rootEntry = cq.from(clazz);
		CriteriaQuery<T> all = cq.select(rootEntry);
		TypedQuery<T> allQuery = em.createQuery(all);
		List<T> allObjects = null;
		try {
			allObjects = allQuery.getResultList();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} finally {
			em.close();
		}
		return allObjects;
	}
	
	public static <T> List<T> sortByGradeDate(String table, Class<T> clazz, String propertyName) {
		EntityManager em = EntityManagerFactory.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> rootEntry = cq.from(clazz);
		cq.orderBy(cb.asc(rootEntry.get(propertyName)));
		CriteriaQuery<T> all = cq.select(rootEntry);
		TypedQuery<T> allQuery = em.createQuery(all);
		List<T> allObjects = null;
		try {
			allObjects = allQuery.getResultList();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} finally {
			em.close();
		}
		return allObjects;
	}
	
	public static <T> T loadByFieldWithWhereClause(String field, Class<T> clazz, String value) {
		EntityManager em = EntityManagerFactory.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> rootEntry = cq.from(clazz);
		cq.select(rootEntry).where(cb.like(rootEntry.get(field), value));
		TypedQuery<T> query = em.createQuery(cq);
        T obj = null;
        try {
            obj = query.getSingleResult();
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            em.close();
        }
        return obj;
    }
	
}
