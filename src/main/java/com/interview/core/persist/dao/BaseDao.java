package com.interview.core.persist.dao;

import org.hibernate.Hibernate;

public class BaseDao {
    public boolean hydrated = false;

    public void hydrate(){}

    protected void initializeObject(Object object){
        try {
            Hibernate.initialize(object);
        } catch (Exception e){
            System.out.println("Error initializing hibernate object");
        }
    }
}
