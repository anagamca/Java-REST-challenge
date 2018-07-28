package com.interview.core.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.common.classloader.ClassLoaderUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.core.models.Person;
import com.interview.core.persist.dao.people.PersonDao;
import com.interview.core.persist.mappers.PersonMapper;

/**
 * Service to interact with people so that PersonDao access is unnecessary fromm above layers
 */


@Singleton
public class PersonService implements IPersonService{
    private static Logger logger = LoggerFactory.getLogger(PersonService.class);
    
    boolean isLoaded;
    PersonMapper personMapper = new PersonMapper();

    //TODO create singleton to store and track people - or if you are using a db produce a singleton service

    List<PersonDao> peopleToAdd = new ArrayList<>();

    //@PostConstruct
    public void init() throws URISyntaxException, IOException {
    	// its hack for now as PostConstruct called for each and every request. In Spring its just calls only during bean initializer
    	if(!isLoaded) {
    		URL url = ClassLoaderUtils.getResource("people.csv", PersonService.class);
    		InputStream is = new FileInputStream(url.getPath());
    		addPeopleFromFile(is);
    	}
    	isLoaded = Boolean.TRUE;
    }
    
    @Override
    public List<Person> addPeopleFromFile(InputStream in) throws URISyntaxException, IOException {
		// TODO: initialize the singleton above by adding a Person for each person found
		// in test-resources/people.csv
        // just moved to classpath src/main/resources
        BufferedReader  reader = new BufferedReader(new InputStreamReader(in));
        List<PersonDao> persons = new ArrayList<>();
        StringBuilder out = new StringBuilder();
        String line;
        int skipHeader = 0 ;
		while ((line = reader.readLine()) != null) {
			if(skipHeader == 0) {
				skipHeader++;
				continue;
			}
			out.append(line);

			String[] strArr = StringUtils.splitPreserveAllTokens(line, ",");
			PersonDao person = new PersonDao();
			person.setFirstName(strArr[0]);
			person.setLastName(strArr[1]);
			person.setAge(StringUtils.isEmpty(strArr[2]) ? 0
					: Long.parseLong(strArr[2]));
			person.setGitHubAccount(strArr[3]);

			String dateStr = strArr[4];
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date date;
			try {
				date = formatter.parse(dateStr);
				person.setGraduationDate(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			persons.add(person);
			skipHeader++;
		}
		reader.close();
		
		List<PersonDao> personsList = persons.stream().filter(person -> StringUtils.isNotEmpty(person.getGitHubAccount()))
				.collect(Collectors.toList());
		
		logger.info("Number of people getting stored {}", personsList.size());
		List<PersonDao> peopleAdded = new ArrayList<>();
		for(PersonDao personDao: personsList) {
			try {
				PersonDao personDaoNew = createNewPerson(personDao.getFirstName(), personDao.getLastName(), personDao.getAge(), personDao.getGitHubAccount(),
						personDao.getGraduationDate());	
				peopleAdded.add(personDaoNew);
			}catch(Exception ex) {
				logger.error("Person not added and reason is {}", ex);
			}
			
		}
		List<Person> newList  = personMapper.to(peopleAdded);
		return newList;
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
	public PersonDao createNewPerson(String firstName, String lastName, long age, String gitHubAcct,
			Date thirdGradeGrad) {
		PersonDao personDao = new PersonDao();
		personDao.setFirstName(firstName);
		personDao.setLastName(lastName);
		personDao.setAge(age);
		personDao.setGitHubAccount(gitHubAcct);
		personDao.setGraduationDate(thirdGradeGrad);
		personDao.save();
		return personDao;
	}


	@Override
	public List<Person> getPeople() {
    	List<PersonDao> list = (List<PersonDao>)PersonDao.findAll("people", PersonDao.class);
    	if(CollectionUtils.isEmpty(list)) {
    		return new ArrayList<>();
    	}
		return personMapper.to(list);
	}


	@Override
	public Person getPersonById(int id) {
		return personMapper.to(PersonDao.loadById(id, PersonDao.class));
	}


	@Override
	public Person getPersonByName(String fullName) {
		PersonDao personDao = PersonDao.loadByFieldWithWhereClause("firstName", PersonDao.class, fullName);
		return personMapper.to(personDao);
	}

	@Override
	public List<Person> getPeopleSortedByThirdGradeGraduation() {
    	List<PersonDao> list = (List<PersonDao>)PersonDao.sortByGradeDate("people", PersonDao.class, "graduationDate");
    	if(CollectionUtils.isEmpty(list)) {
    		return new ArrayList<>();
    	}
		return personMapper.to(list);
	}

	@Override
	public List<Person> addPeople(List<Person> people) {
		for (Person person : people) {
			createNewPerson(person.getFirstname(), person.getLastname(), person.getAge(), person.getGitHubAccount(),
					person.getGraduationDate());
		}
		return people;
	}


}
