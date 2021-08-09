package com.example.demo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import com.example.demo.dao.FakePersonDataAccessService;
import com.example.demo.model.Person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FakePersonDataAccessServiceTest {

    private FakePersonDataAccessService underTest;

    @BeforeEach
    public void setUp() {
        underTest = new FakePersonDataAccessService();
    }

    @Test
    public void canPerformCrud() {
        // Given person called James Bond aged 33
        UUID idOne = UUID.randomUUID();
        Person personOne = new Person(idOne, "James Bond");

        // ...And Anna Smith aged 40
        UUID idTwo = UUID.randomUUID();
        Person personTwo = new Person(idTwo, "Anna Smith");

        // When James and Anna added to db
        underTest.insertPerson(idOne, personOne);
        underTest.insertPerson(idTwo, personTwo);

        // Then can retrieve James by id
        assertEquals(underTest.selectPersonById(idOne).get(), personOne);
        assertEquals(underTest.selectPersonById(idTwo).get(), personTwo);

        List<Person> people = underTest.selectAllPeople();

        assertEquals(2, people.size());

        Person personUpdate = new Person(idOne, "Tom Jones");
        assertEquals(1, underTest.updatePersonById(idOne, personUpdate));
        assertEquals(personUpdate, underTest.selectPersonById(idOne).get());

        assertEquals(1, underTest.deletePersonById(idOne));

        assertEquals(true, underTest.selectPersonById(idOne).isEmpty());

        people = underTest.selectAllPeople();
        assertThat(people.size(), is(1));
        assertThat(people, hasItem(personTwo));
    }
    
    @Test
    public void willReturn0IfNoPersonFoundToDelete() {
        UUID id = UUID.randomUUID();

        int deleteResult = underTest.deletePersonById(id);

        assertEquals(0, deleteResult);
    }

    @Test
    public void willReturn0IfNoPersonFoundToUpdate() {
        UUID id = UUID.randomUUID();
        Person person = new Person(id, "James bond"); // not in db yet

        int updateResult = underTest.updatePersonById(id, person);

        assertEquals(0, updateResult);
    }

}
