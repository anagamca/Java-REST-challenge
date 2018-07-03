package com.interview.core.service;

import com.interview.core.models.Person;
import com.interview.core.persist.dao.people.PersonDao;
import com.interview.core.utils.SystemEnviron;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Service to interact with people so that PersonDao access is unnecessary fromm above layers
 */
public class PersonService {
    private static Logger logger = LoggerFactory.getLogger(PersonService.class);

    //TODO create singleton to store and track people - or if you are using a db produce a singleton service


    public void initializePeople(){
        //TODO: initialize the singleton above by adding a Person for each person found in test-resources/people.csv
        throw new NotImplementedException();
    }


    /**
     * Create a new Person and save to the database
     * @param firstName
     * @param lastName
     * @param age
     * @param gitHubAcct
     * @param thirdGradeGrad
     * @return
     */
    public PersonDao createNewPerson(String firstName, String lastName,
                                            long age, String gitHubAcct, Date thirdGradeGrad) {
        //TODO validate the minimal amount of fields needed to save a user

        //TODO if user already exists dont save throw an appropriate error

        //TODO create and save the user
        throw new NotImplementedException();
    }


}
