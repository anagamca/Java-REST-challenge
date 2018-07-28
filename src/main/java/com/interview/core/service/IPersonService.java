package com.interview.core.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import com.interview.core.models.Person;
import com.interview.core.persist.dao.people.PersonDao;

public interface IPersonService {
	
	public PersonDao createNewPerson(String firstName, String lastName,
            long age, String gitHubAcct, Date thirdGradeGrad);


	public List<Person> getPeople();
	
	public Person getPersonById(int id);
	
	public Person getPersonByName(String firstOrLastName);
	
	
	public List<Person> getPeopleSortedByThirdGradeGraduation();
	
	public List<Person> addPeople(List<Person> people);
	
	public List<Person> addPeopleFromFile(InputStream is) throws URISyntaxException, IOException;
}
