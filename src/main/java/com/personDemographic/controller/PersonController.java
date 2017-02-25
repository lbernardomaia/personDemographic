package com.personDemographic.controller;

import com.personDemographic.domain.Person;
import com.personDemographic.exception.ValidationException;
import com.personDemographic.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
public class PersonController {

    private static final Logger LOGGER = Logger.getLogger(PersonController.class.getName());
    public static final String PAGE_HOME = "home";
    public static final String PAGE_NEW = "new";
    public static final String PAGE_LIST = "list";

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET, value = {"/","/home"})
    public String page_home() throws Exception {
        return PAGE_HOME;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/new"})
    public String page_new_person (Model model) throws Exception {
        Person person = new Person();
        model.addAttribute("person", person);
        return PAGE_NEW;
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/new"})
    public String new_person (Person person, HttpServletRequest request) throws Exception {
        try {
            personService.save(person);
        }catch (ValidationException e){
            request.setAttribute("message", e.getMessage());
            LOGGER.warning(e.getMessage());
            return PAGE_NEW;
        }
        request.setAttribute("message", "The register has been created");
        return PAGE_HOME;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/list"})
    public String page_list(HttpServletRequest request) throws Exception {
        request.setAttribute("persons", personService.findAll());
        return PAGE_LIST;
    }

}
