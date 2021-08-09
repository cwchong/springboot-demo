package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Person;

import org.springframework.stereotype.Repository;

// just change PersonService to use this label "postgres" instead of "fakedao"
@Repository("mysql")
public class PersonDataAccessService implements PersonDAO{

    @Override
    public int insertPerson(UUID id, Person person) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        return List.of(new Person(UUID.randomUUID(), "FROM someother DB"));
    }

    @Override
    public Optional<Person> selectPersonById(UUID iUuid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int deletePersonById(UUID id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
