package com.interview.core.persist.mappers;

import com.interview.core.models.Person;
import com.interview.core.persist.dao.people.PersonDao;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

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
        throw new NotImplementedException();
    }

    /**
     * TODO: Map PersonDao to Person
     * @param personDao PersonDao to map
     * @return Person mapped PersonDao
     */
    public Person to(PersonDao personDao) {
        throw new NotImplementedException();
    }

    //TODO implement from
    public PersonDao from(Person person) {
        throw new NotImplementedException();
    }

}
