package com.interview.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.interview.core.models.Person;
import com.interview.core.persist.dao.people.PersonDao;
import com.interview.core.rest.PeopleRestService;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PersonServiceTest.class)
public class PersonServiceTest {
    //TODO build out some unit tests for your service and REST layer and structure them at your will
	
	@InjectMocks
    PeopleRestService peopleRestService;
	
	@Mock
	IPersonService personService;
	
	@Mock PersonDao personDao;
	
	@Before
	public void init() throws Exception {

	}
	
	@Test
	public void testGetPeople() {
		List<Person> personList = new ArrayList<>();
		Person person = new Person();
		person.setFirstname("nagendran");
		person.setLastname("alagesan");
		personList.add(person);
		when(personService.getPeople()).thenReturn(personList);
		List<Person> people = peopleRestService.getPeople();
		assertTrue(people.size() == 1);
		assertEquals("Firstname should match", personList.get(0).getFirstname(), "nagendran");
	}

	
	@Test
	public void testGetPeopleNoData() {
		List<Person> people = peopleRestService.getPeople();
		assertTrue(people.size() == 0);
	}

	
   
}
