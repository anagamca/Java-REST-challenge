package com.interview.core.persist.dao.people;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.core.persist.EntityManagerFactory;
import com.interview.core.persist.dao.IdDao;

/**
 * Person DAO containing user information
 */
@Table(name = "people")
@Entity
public class PersonDao extends IdDao {
	
	private static Logger logger = LoggerFactory.getLogger(PersonDao.class);

    @Column(updatable = false)
    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @Column(name = "firstName")
    private String firstName;
    
    @Column(name = "lastName")
    private String lastName;
    
    @Column(name = "age")
    private Long age;
    
    @Column(name = "gitHubAccount", unique = true, nullable = false, updatable = false)
    private String gitHubAccount;
    
    private Date graduationDate;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getGitHubAccount() {
		return gitHubAccount;
	}

	public void setGitHubAccount(String gitHubAccount) {
		this.gitHubAccount = gitHubAccount;
	}

	public Date getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}
    
}
