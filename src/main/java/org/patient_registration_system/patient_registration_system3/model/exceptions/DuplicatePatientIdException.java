package org.patient_registration_system.patient_registration_system3.model.exceptions;

public class DuplicatePatientIdException extends Exception {
    public DuplicatePatientIdException(String message) {
        super(message);
    }
}
