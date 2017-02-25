package com.personDemographic.service.impl;

import com.personDemographic.domain.Person;
import com.personDemographic.exception.ValidationException;
import com.personDemographic.repository.PersonRepository;
import com.personDemographic.service.PersonService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {
    private PersonRepository repository;

    @Autowired
    public PersonServiceImpl(PersonRepository repository){
        this.repository = repository;
    }

    @Override
    public Person save(Person person) {
        validatePerson(person);
        person.setCreationDate(new Date());
        return repository.save(person);
    }

    private void validatePerson(Person person) {
        validateRequiredFields(person);
        validateNameMaxCharacters(person.getName());
        validateUniquePPSNumber(person.getPpsNumber());
        validateDate(person.getDateBirth());
        validateMiniumAge(person.getDateBirth());
        validateMobileNumber(person.getMobilePhone());
    }

    private void validateRequiredFields(Person person) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Person>> errors = validator.validate(person);

        if (!errors.isEmpty()) {
            for (ConstraintViolation<Person> fieldError : errors ) {
                throw new ValidationException(fieldError.getMessageTemplate());
            }
        }
    }

    private void validateMobileNumber(String number) {
        if (!StringUtils.isEmpty(number) && number.length() > 3 && !number.substring(0,2).equals("08")){
            throw new ValidationException("Mobile Phone Number: Must begin with 08 prefix");
        }
    }

    private void validateMiniumAge(Date dateBirth) {
        int currentYear = new DateTime().getYear();
        int yearBirth = new DateTime(dateBirth.getTime()).getYear();

        if ((currentYear - yearBirth) <= 16){
            throw new ValidationException("Date Birth: Must be over 16 years old");
        }
    }

    private void validateDate(Date dateBirth) {
        DateTime dateTime = new DateTime().withHourOfDay(0)
                                          .withMinuteOfHour(0)
                                          .withSecondOfMinute(0)
                                          .withMillisOfDay(0)
                                          .withMillisOfSecond(0);


        if (dateBirth.after(dateTime.toDate())){
            throw new ValidationException("Date Birth: Cannot be futute date");
        }
    }

    private void validateUniquePPSNumber(String ppsNumber) {
        Person person = new Person();
        person.setPpsNumber(ppsNumber);

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase();

        Example<Person> personExample = Example.of(person, matcher);

        if (repository.findOne(personExample) != null){
            throw new ValidationException("PPS Number: The PPS Number have already been registered");
        }
    }

    private void validateNameMaxCharacters(String name) {
        if (name.length() > 25 ){
            throw new ValidationException("Name: Max 25 Characters");
        }
    }

    @Override
    public List<Person> findAll() {
        Sort sort = new Sort(Sort.Direction.ASC, "creationDate");
        return repository.findAll(sort);
    }
}
