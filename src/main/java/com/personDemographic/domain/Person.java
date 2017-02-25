package com.personDemographic.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "PPS Number: Required field")
    @Column(name="pps_number")
    private String ppsNumber;

    @NotBlank(message = "Name: Required field")
    private String name;

    @NotNull(message = "Date Of Birth: Required field")
    @Column(name="date_birth")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date dateBirth;

    @Column(name="mobile_phone_number")
    private String mobilePhone;

    @Column(name="creation_date")
    private Date creationDate;

    public Person(){
    }

    public Person(String name, String ppsNumber, Date dateBirth, String mobilePhone){
        this.name = name;
        this.ppsNumber = ppsNumber;
        this.dateBirth = dateBirth;
        this.mobilePhone = mobilePhone;
    }

    public Person(String name, String ppsNumber, Date dateBirth){
        this.name = name;
        this.ppsNumber = ppsNumber;
        this.dateBirth = dateBirth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPpsNumber() {
        return ppsNumber;
    }

    public void setPpsNumber(String ppsNumber) {
        this.ppsNumber = ppsNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
