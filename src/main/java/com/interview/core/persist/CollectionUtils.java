package com.interview.core.persist;

import com.interview.core.persist.dao.BaseDao;
import com.interview.core.persist.dao.CustomIdBaseDao;
import com.interview.core.persist.dao.IdDao;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Utilities to execute common Criteria queries against the database
 */
public class CollectionUtils {
    private static Logger logger = LoggerFactory.getLogger(CollectionUtils.class);

    /**
     * Select all objects of class clazz, where fieldName = value
     * @param fieldName - String name of field
     * @param value - Object value of field
     * @param clazz - determining table to query
     * @return List of IdDao results
     */
    public static List selectAllBy(String fieldName, Object value, Class<? extends IdDao> clazz) {
        List results = new ArrayList<>();
        EntityManager em = EntityManagerFactory.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = builder.createQuery(clazz);
            Root entity = criteriaQuery.from(clazz);
            criteriaQuery.select(entity);
            criteriaQuery.where(builder.equal(entity.get(fieldName), value));
            criteriaQuery.distinct(true);
            results = em.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            em.close();
        }
        return results;
    }

    public static List selectAll(Class<? extends IdDao> clazz) {
        List results = new ArrayList<>();
        EntityManager em = EntityManagerFactory.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = builder.createQuery(clazz);
            Root entity = criteriaQuery.from(clazz);
            criteriaQuery.select(entity);
            criteriaQuery.distinct(true);
            results = em.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            em.close();
        }
        return results;
    }

    public static List selectAllByCustomBaseDao(String fieldName, Object value, Class<? extends CustomIdBaseDao> clazz) {
        List results = new ArrayList<>();
        EntityManager em = EntityManagerFactory.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = builder.createQuery(clazz);
            Root entity = criteriaQuery.from(clazz);
            criteriaQuery.select(entity);
            criteriaQuery.where(builder.equal(entity.get(fieldName), value));
            criteriaQuery.distinct(true);
            results = em.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            em.close();
        }
        return results;
    }

    /**
     * Select all objects of class clazz, where for every column(key) in map = value
     * @param fieldValueMap - Map of fields and their values
     * @param clazz - determining table to query
     * @return List of IdDao results
     */
    public static List selectAllBy(Map<String, Object> fieldValueMap, Class<? extends IdDao> clazz) {
        List results = new ArrayList<>();
        EntityManager em = EntityManagerFactory.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = builder.createQuery(clazz);
            Root entity = criteriaQuery.from(clazz);
            criteriaQuery.select(entity);
            for(String key : fieldValueMap.keySet()) {
                criteriaQuery.where(builder.equal(entity.get(key), fieldValueMap.get(key)));
            }
            criteriaQuery.distinct(true);
            results = em.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            em.close();
        }
        return results;
    }

    /**
     * Select Single object fromm table clazz, where fieldName = value, and where nullfield is null
     * @param fieldValueMap - Map of fields and their values
     * @param clazz - determining table to query
     * @param nullFields List of String field names where they should be null
     * @return T result
     */
    public static List selectAllBy(Map<String, Object> fieldValueMap, List<String> nullFields, Class<? extends IdDao> clazz) {
        List results = new ArrayList<>();
        EntityManager em = EntityManagerFactory.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = builder.createQuery(clazz);
            Root entity = criteriaQuery.from(clazz);
            Predicate p = builder.conjunction();
            for(String key : fieldValueMap.keySet()) {
                p = builder.and(p, builder.equal(entity.get(key), fieldValueMap.get(key)));
            }
            for(String field : nullFields) {
               p = builder.and(p, builder.isNull(entity.get(field)));
            }
            criteriaQuery.select(entity)
            .distinct(true)
            .where(p);

            results = em.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            em.close();
        }
        return results;
    }

    /**
     * Select Single object fromm table clazz, where fieldName = value
     * @param fieldName - String name of field
     * @param value - Object value of field
     * @param clazz - determining table to query
     * @param <T> Type to return
     * @return T result
     */
    public static <T> T selectSingleBy(String fieldName, Object value, Class<? extends IdDao> clazz) {
        Object result = null;
        EntityManager em = EntityManagerFactory.getEntityManager();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = builder.createQuery(clazz);
            Root entity = criteriaQuery.from(clazz);
            criteriaQuery.select(entity);
            criteriaQuery.where(builder.equal(entity.get(fieldName), value));
            criteriaQuery.distinct(true);
            result = em.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            em.close();
        }
        return (T) result;
    }

    /**
     * Batch Save to Database
     * @param lists of daos
     */
    public static void batchSave(List<List<BaseDao>> lists) {
        Session session = EntityManagerFactory.getSession(EntityManagerFactory.getEntityManager());
        Transaction tx = session.beginTransaction();
        Integer totalSize = lists.size();
        Float count = 0.0f;
        try {
            for(List listOfDaos  : lists) {
                logger.info(count/totalSize + "% complete batch saving");
                for (int i = 0; i < listOfDaos.size(); i++) {
                    session.save(listOfDaos.get(i));
                    if (i % 50 == 0) { //20, same as the JDBC batch size
                        //flush a batch of inserts and release memory:
                        logger.info("flushing sesssion after batch saving " + i + " lists");
                        session.flush();
                        session.clear();
                    }
                }
                count += 1;
            }
            tx.commit();
        } catch (Exception e) {
            logger.error("error batch saving", e);
        } finally {
            session.close();
        }

    }

}
