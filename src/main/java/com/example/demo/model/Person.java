package com.example.demo.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    
    private final UUID id;
    
    // notblank enforce the rest to always provide this
    @NotBlank
    private final String name;

    // jsonproperty allows this object to understand mapping from json data
    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {;
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Person other = (Person) obj;
        return (name == other.name && id == other.id);
    }
}
