package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// RowMapper and JdbcTemplate, ensure we are loading from springframework, not flyway
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// just change PersonService to use this label "postgres" instead of "fakedao"
@Repository("mysql")
public class PersonDataAccessService implements PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "select id, name from person";
        RowMapper<Person> rowMapper = (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            return new Person(id, resultSet.getString("name"));
        };
        // dun need this.jdbcTemplate since there is only 1 copy of it
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "select id, name from person where id = ?";
        RowMapper<Person> rowMapper = (resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("id"));
            return new Person(personId, resultSet.getString("name"));
        };
        // queryforobject returns object instead of list (per query)
        // Person person = jdbcTemplate.queryForObject(sql, rowMapper, id);
        // queryForObject raises exception if no results or more than 1
        Person person = jdbcTemplate.query(
            sql, 
            rs -> rs.next() ? rowMapper.mapRow(rs, 1) : null,
            id.toString() // NOTE in java passing types is important, passing uuid type will not quote as string!
        );
        return Optional.ofNullable(person);
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
