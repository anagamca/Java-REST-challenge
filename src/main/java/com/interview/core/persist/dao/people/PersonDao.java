package com.interview.core.persist.dao.people;


import com.interview.core.persist.dao.IdDao;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

/**
 * Person DAO containing user information
 */
@Entity
@Table(name = "people")
@SequenceGenerator(name = "default_gen", sequenceName = "people_id_seq", allocationSize = 1)
public class PersonDao extends IdDao {

    @Column(updatable = false)
    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @Column(name = "firstName")
    private String firstName;

    //TODO last: finish this dao and persist it to the database

}
