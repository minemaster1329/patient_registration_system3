package org.patient_registration_system.patient_registration_system3.model.data_models;

import org.patient_registration_system.patient_registration_system3.model.data_models.Gender;
import org.patient_registration_system.patient_registration_system3.model.exceptions.*;
import org.patient_registration_system.patient_registration_system3.pubstuff.PublicRegexes;
import org.patient_registration_system.patient_registration_system3.pubstuff.PublicStaticMethods;
public class Person {
    //region properties
    String id;
    String name;
    String surname;
    String middleName;
    Gender gender;
    String email;
    //endregion

    public Person(){
        id = "";
        name = "";
        surname = "";
        middleName = "";
        gender = Gender.Unknown;
        email = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws InvalidPESELFormatException, InvalidPESELChecksumException {
        if (id.matches(PublicRegexes.peselRegex)){
            if (PublicStaticMethods.validatePESELChecksum(id)){
                this.id = id;
            }
            else throw new InvalidPESELChecksumException("PESEL format correct, but checksum invalid");
        }
        else throw new InvalidPESELFormatException("Person's id must have correct format");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidNameFormatException {
        if (name.matches(PublicRegexes.nameRegex)){
            this.name = name;
        }
        else throw new InvalidNameFormatException("Person's name can contain only letters and must begin from uppercase");
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws InvalidSurnameFormatException {
        if (surname.matches(PublicRegexes.nameRegex)){
            this.surname = surname;
        }
        else throw new InvalidSurnameFormatException("Person's surname can contain only letters and must begin from uppercase");
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) throws InvalidMiddleNameFormatException {
        if (middleName.isEmpty() || middleName.matches(PublicRegexes.nameRegex)){
            this.middleName = middleName;
        }
        else throw new InvalidMiddleNameFormatException("Middle name can be empty or must only consist of letters and begin from uppercase");
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidEmailFormatException {
        if (email.isEmpty() || email.matches(PublicRegexes.emailRegex)){
            this.email = email;
        }
        else  throw new InvalidEmailFormatException("E-mail must have correct format");
    }
}
