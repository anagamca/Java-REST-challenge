package com.interview.core.persist.mappers;

import java.util.ArrayList;
import java.util.List;

import com.interview.core.models.Person;
import com.interview.core.persist.dao.people.PersonDao;

/**
 * This Mapper is to convert the PersonDao to a REST safe model
 * Any sensitive data should not be mapped.
 */
public class PersonMapper {

    /**
     * TODO: Map a list of PersonDao to Person
     * @param personList - List of PersonDao
     * @return List, Persons
     */
	public List<Person> to(List<PersonDao> personList) {
		List<Person> persons = new ArrayList<>();
		for (PersonDao personDao : personList) {
			persons.add(to(personDao));
		}
		return persons;
	}

    /**
     * TODO: Map PersonDao to Person
     * @param personDao PersonDao to map
     * @return Person mapped PersonDao
     */
    public Person to(PersonDao personDao) {
    	if(personDao == null) {
    		return new Person();
    	}
    	Person person = new Person();
		person.setFirstname(personDao.getFirstName());
		person.setLastname(personDao.getLastName());
		person.setGraduationDate(personDao.getGraduationDate());
		person.setAge(personDao.getAge());
		return person;
    }

    public PersonDao from(Person person) {
    	if(person == null) {
    		return new PersonDao();
    	}
    	PersonDao personDao = new PersonDao();
    	personDao.setFirstName(person.getFirstname());
    	personDao.setLastName(person.getLastname());
    	personDao.setAge(person.getAge());
    	personDao.setGitHubAccount(person.getGitHubAccount());
    	personDao.setGraduationDate(person.getGraduationDate());
        return personDao;
    }

}
