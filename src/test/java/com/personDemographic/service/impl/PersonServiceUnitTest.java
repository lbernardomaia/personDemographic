package com.personDemographic.service.impl;

import com.personDemographic.domain.Person;
import com.personDemographic.exception.ValidationException;
import com.personDemographic.repository.PersonRepository;
import com.personDemographic.service.PersonService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class PersonServiceUnitTest {

    private PersonService personService;
    private PersonRepository personRepository;
    private Person person;

    @Before
    public void setUp() {
        person = createPerson();

        personRepository = mock(PersonRepository.class);

        personService = new PersonServiceImpl(personRepository);
    }

    private Person createPerson(){
        DateTime dateTime = new DateTime().withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfDay(0)
                .withMillisOfSecond(0);

        dateTime = dateTime.minusYears(17);

        return new Person("Jack", "1234580L", dateTime.toDate(), "0897803395");
    }

    @Test
    public void save_person_with_sucess(){
        personService.save(person);
    }

    @Test(expected = ValidationException.class)
    public void save_person_without_name(){
        person.setName("");
        personService.save(person);
    }

    @Test
    public void save_person_with_name_25_characters(){
        person.setName("QWERTYUIOPASDFGHJKLZXCVBN");
        personService.save(person);
    }

    @Test(expected = ValidationException.class)
    public void save_person_with_name_26_characters(){
        person.setName("QWERTYUIOPASDFGHJKLZXCVBNM");
        personService.save(person);
    }

    @Test(expected = ValidationException.class)
    public void save_person_without_pps_number(){
        person.setPpsNumber("");

        personService.save(person);
    }

    @Test(expected = ValidationException.class)
    public void save_person_without_date_birth(){
        person.setDateBirth(null);

        personService.save(person);
    }

    @Test
    public void save_person_with_date_less_than_current_date(){
        DateTime dateTime = new DateTime().withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfDay(0)
                .withMillisOfSecond(0);

        dateTime = dateTime.minusDays(1);
        dateTime = dateTime.minusYears(17);

        person.setDateBirth(dateTime.toDate());

        personService.save(person);
    }

    @Test(expected = ValidationException.class)
    public void save_person_with_date_birth_in_the_future(){
        DateTime dateTime = new DateTime().withHourOfDay(0)
                                          .withMinuteOfHour(0)
                                          .withSecondOfMinute(0)
                                          .withMillisOfDay(0)
                                          .withMillisOfSecond(0);

        dateTime = dateTime.plusDays(1);

        person.setDateBirth(dateTime.toDate());

        personService.save(person);
    }

    @Test(expected = ValidationException.class)
    public void save_person_with_16_years_old(){
        DateTime dateTime = new DateTime().withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfDay(0)
                .withMillisOfSecond(0);

        dateTime = dateTime.minusYears(16);

        person.setDateBirth(dateTime.toDate());

        personService.save(person);
    }

    @Test(expected = ValidationException.class)
    public void save_person_with_wrong_mobile_number(){
        person.setMobilePhone("5597803395");

        personService.save(person);
    }
}
