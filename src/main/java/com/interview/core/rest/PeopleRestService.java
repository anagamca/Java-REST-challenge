package com.interview.core.rest;

import com.interview.core.models.Person;
import com.interview.core.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Rest Service for interacting with people
 */
@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleRestService {
    private static Logger logger = LoggerFactory.getLogger(PeopleRestService.class);

    @GET
    public List<Person> getPeople() {
        //TODO return a list of people
        throw new NotImplementedException();
    }

    @GET
    @Path("/people/sorted")
    public List<Person> getPeopleSortedByThirdGradeGraduation() {
        //TODO return list of people sorted by Date of third grade graduation.
        throw new NotImplementedException();
    }

    @GET
    @Path("/person/{id}")
    public Person getLoansPerState(@PathParam("id") long id) {
        //TODO return person with that id
        throw new NotImplementedException();
    }

    @GET
    @Path("/person/byname/{fullname}")
    public Person getPersonByName(@PathParam("fullname") String fullName) {
        //TODO return person with that name
        throw new NotImplementedException();
    }

    @POST
    @Path("/person")
    public Person addPersonToPeople(Person person) {
        //TODO create a new person and add it to your people to keep track of. return the new person
        throw new NotImplementedException();
    }

    @POST
    public List<Person> addPeopleToPeople(List<Person> people) {
        //TODO create a new people and add it to your people to keep track of. return all the people
        throw new NotImplementedException();
    }

}
