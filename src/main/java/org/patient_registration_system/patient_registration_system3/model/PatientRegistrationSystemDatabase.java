package org.patient_registration_system.patient_registration_system3.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.patient_registration_system.patient_registration_system3.model.data_models.Person;

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
        return patientArrayList.stream().toList();
    }

    public void initializeSingleton() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        patientArrayList = objectMapper.readValue(Paths.get("/home/stduser/patients.json").toFile(), new TypeReference<List<Person>>(){});
    }

    public void saveDatabase() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String data = objectMapper.writeValueAsString(patientArrayList);
        OutputStream outputStream = Files.newOutputStream(Paths.get("/home/stduser/patients.json"), CREATE, TRUNCATE_EXISTING);

        outputStream.write(data.getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }

    public boolean addNewPatient(Person person){
        patientArrayList.add(person);
        try {
            saveDatabase();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
