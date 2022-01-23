package org.patient_registration_system.patient_registration_system3.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.patient_registration_system.patient_registration_system3.model.data_models.Person;
import org.patient_registration_system.patient_registration_system3.model.exceptions.DuplicatePatientIdException;

import javax.swing.plaf.IconUIResource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class PatientRegistrationSystemDatabase {
    private static PatientRegistrationSystemDatabase instance = null;
    private List<Person> patientArrayList = new ArrayList<>();

    private PatientRegistrationSystemDatabase(){}

    public static PatientRegistrationSystemDatabase getInstance(){
        if (instance == null) {
            instance = new PatientRegistrationSystemDatabase();
        }
        return instance;
    }

    public Long getPatientsCount(){
        if (patientArrayList == null) return -1L;
        else return (long) patientArrayList.size();
    }

    public List<Person> getAllPatients(){
        return new ArrayList<>(patientArrayList);
    }

    public void initializeSingleton() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //Linux Only
        patientArrayList = objectMapper.readValue(Paths.get("/home/payara/database/patients.json").toFile(), new TypeReference<List<Person>>(){});
        //Windows Only
        //patientArrayList = objectMapper.readValue(Paths.get("C:\jwiium\patients.json").toFile(), new TypeReference<List<Person>>(){});
    }

    public void saveDatabase() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String data = objectMapper.writeValueAsString(patientArrayList);
        //Linux only
        OutputStream outputStream = Files.newOutputStream(Paths.get("/home/payara/database/patients.json"), CREATE, TRUNCATE_EXISTING);
        //Windows Only
        //OutputStream outputStream = Files.newOutputStream(Paths.get("C:\jwiium\patients.json"), CREATE, TRUNCATE_EXISTING);

        outputStream.write(data.getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }

    public boolean addNewPatient(Person person) throws DuplicatePatientIdException{
        if (patientWithIdExists(person.getId())) throw new DuplicatePatientIdException("Patient with this id already exists");
        patientArrayList.add(person);
        try {
            saveDatabase();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean patientWithIdExists(String id){
        return patientArrayList.stream().anyMatch((p)->p.getId().equals(id));
    }
}
