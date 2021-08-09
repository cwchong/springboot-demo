package com.example.demo.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// restcontroller makes this a restful api
@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // postmapping -> defines a rest endpt
    // requestbody takes value from request instead of by param call
    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    // pathvariable allows getting url path parts mapped to params
    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id).orElse(null);
    }
    
    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePersonById(id);
    }

    @PutMapping(path = "{id}")
    public void updatePersonById(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person personToUpdate) {
        personService.updatePersonById(id, personToUpdate);
    }
}
