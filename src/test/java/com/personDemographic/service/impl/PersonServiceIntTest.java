package com.personDemographic.service.impl;

import com.personDemographic.domain.Person;
import com.personDemographic.exception.ValidationException;
import com.personDemographic.repository.PersonRepository;
import com.personDemographic.service.PersonService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceIntTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void setUp() {
        personRepository.deleteAll();
    }

    @Test
    public void find_all_persons(){
        DateTime dateTime = new DateTime().withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfDay(0)
                .withMillisOfSecond(0);

        dateTime = dateTime.minusYears(17);

        personRepository.save(new Person("Jack", "1234580L", dateTime.toDate(), "0897803395"));
        personRepository.save(new Person("George", "1234576U", dateTime.toDate(), null));
        personRepository.save(new Person("Steve", "1234572M", dateTime.toDate(), "0897803393"));

        List<Person> superHeroes = personService.findAll();
        assert superHeroes.size() > 0;
    }

    @Test
    public void save_person_with_sucess(){
        DateTime dateTime = new DateTime().withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfDay(0)
                .withMillisOfSecond(0);

        dateTime = dateTime.minusYears(17);

        Person person = new Person("Jack", "1234580L", dateTime.toDate(), "0897803395");

        Person personRecovered = personService.save(person);

        assert personRecovered.getId() > 0;
    }

    @Test(expected = ValidationException.class)
    public void save_person_with_duplicate_pps_number(){
        DateTime dateTime = new DateTime().withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfDay(0)
                .withMillisOfSecond(0);

        dateTime = dateTime.minusYears(17);

        Person person1 = new Person("Jack", "1234580L", dateTime.toDate(), "0897803395");

        personService.save(person1);

        Person person2 = new Person("Jack", "1234580L", dateTime.toDate(), "0897803395");

        personService.save(person2);
    }
}
