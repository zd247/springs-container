package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name FROM person";
        // return the query * from database
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name =  resultSet.getString("name");
            return new Person(id, name);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id){
        final String sql = "SELECT id , name FROM person WHERE id = ?";

        Person person =  jdbcTemplate.queryForObject(sql,
                new Object[]{id},
                (resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name =  resultSet.getString("name");
            return new Person(personId, name);
        });

        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql = "DELETE FROM person WHERE id=?";
        Object[] person = new Object[] {id};
        jdbcTemplate.update(sql, person);
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return 0;
    }
}