package com.interview.core.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.core.models.Person;
import com.interview.core.persist.dao.people.PersonDao;
import com.interview.core.persist.mappers.PersonMapper;
import com.interview.core.service.IPersonService;

/**
 * Rest Service for interacting with people
 */
@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleRestService {
	
    private static Logger logger = LoggerFactory.getLogger(PeopleRestService.class);
    
    PersonMapper personMapper = new PersonMapper();
    
    @Inject
    private IPersonService personService;
    
    @GET
    public List<Person> getPeople() {
        return personService.getPeople();
    }

    @GET
    @Path("/people/sorted")
    public List<Person> getPeopleSortedByThirdGradeGraduation() {
       return personService.getPeopleSortedByThirdGradeGraduation();
    }

    @GET
    @Path("/person/{id}")
    public Person getPersonId(@PathParam("id") long id) {
    	return personService.getPersonById((int)id);
    }

    @GET
    @Path("/person/byname/{fullname}")
    public Person getPersonByName(@PathParam("fullname") String fullName) {
        return personService.getPersonByName(fullName);
    }

	@POST
	@Path("/person")
	public Person addPersonToPeople(Person person) {
		PersonDao personDao = personService.createNewPerson(person.getFirstname(), person.getLastname(),
				person.getAge(), person.getGitHubAccount(), person.getGraduationDate());
		return personMapper.to(personDao);
	}

    @POST
    public List<Person> addPeopleToPeople(List<Person> people) {
    	logger.info("Number of people to be added {}", people.size());
        return personService.addPeople(people);
    }
    
    @POST
    @Path("/person/upload")
    public List<Person> peopleFileUpload(@FormDataParam("file") InputStream uploadedInputStream) throws URISyntaxException, IOException {
        return personService.addPeopleFromFile(uploadedInputStream);
    }


}
