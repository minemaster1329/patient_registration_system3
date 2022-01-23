package org.patient_registration_system.patient_registration_system3.model;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.annotation.Resource;
import javax.sql.DataSource;

public class PatientRegistrationSystemMySQLDatabaseSingleton {
    private static PatientRegistrationSystemMySQLDatabaseSingleton instance = null;

    private Exception exceptionWhenInit;

    private PatientRegistrationSystemMySQLDatabaseSingleton() {

    }

    public static PatientRegistrationSystemMySQLDatabaseSingleton getInstance(){
        if (instance == null){
            instance = new PatientRegistrationSystemMySQLDatabaseSingleton();
        }
        return instance;
    }

    public Exception getExceptionWhenInit() {
        return exceptionWhenInit;
    }
}
