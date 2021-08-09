package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Person;

public interface PersonDAO {
    
    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> selectAllPeople();
    
    Optional<Person> selectPersonById(UUID iUuid);

    int deletePersonById(UUID id);

    int updatePersonById(UUID id, Person person);

}
