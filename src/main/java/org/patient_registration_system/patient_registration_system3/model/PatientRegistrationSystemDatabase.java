package org.patient_registration_system.patient_registration_system3.model;

public class PatientRegistrationSystemDatabase {
    private static PatientRegistrationSystemDatabase instance = null;

    private PatientRegistrationSystemDatabase(){}

    public static PatientRegistrationSystemDatabase getInstance(){
        if (instance == null) {
            instance = new PatientRegistrationSystemDatabase();
        }
        return instance;
    }


}
