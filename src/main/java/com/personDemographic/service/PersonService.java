package com.personDemographic.service;

import com.personDemographic.domain.Person;

import java.util.List;

public interface PersonService {
    Person save(Person person);
    List<Person> findAll();
}
