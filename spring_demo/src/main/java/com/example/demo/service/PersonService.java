/**
 * Service class that handles injecting database connection
 * and provide callbacks to the database which then be called in the API definitions
 */

package com.example.demo.service;

import com.example.demo.dao.PersonDAO;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class PersonService {
    private final PersonDAO personDAO;

    @Autowired
    public PersonService(@Qualifier("postgres")PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public int addPerson(Person person) {
        return personDAO.insertPerson(person); // this function represents the insertPerson in the fake database class
    }

    public List<Person> getAllPeople() {
        return personDAO.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDAO.selectPersonById(id);
    }

    public int deletePerson(UUID id) {
        return personDAO.deletePersonById(id);
    }

    public int updatePerson(UUID id, Person person) {
        return personDAO.updatePersonById(id, person);
    }
}
