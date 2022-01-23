package org.patient_registration_system.patient_registration_system3.servlets;

import org.patient_registration_system.patient_registration_system3.model.PatientRegistrationSystemDatabase;
import org.patient_registration_system.patient_registration_system3.model.data_models.Gender;
import org.patient_registration_system.patient_registration_system3.model.data_models.Person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddNewPatientServlet", value = "/AddNewPatientServlet")
public class AddNewPatientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Person new_patient = new Person();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("</head>");
        out.println("<body>");
        boolean verification = true;
        try {
            new_patient.setName(request.getParameter("firstname"));
        }
        catch (Exception e) {
            out.println("<p>Patient's name form verification failed</p>");
            verification = false;
        }

        try {
            new_patient.setSurname(request.getParameter("surname"));
        }
        catch (Exception e) {
            out.println("<p>Patient's surname form verification failed</p>");
            verification = false;
        }

        try {
            new_patient.setMiddleName(request.getParameter("middlename"));
        }
        catch (Exception e) {
            out.println("<p>Patient's middlename form verification failed</p>");
            verification = false;
        }

        try {
            new_patient.setId(request.getParameter("pesel"));
        }
        catch (Exception e) {
            out.println("<p>Patient's pesel form verification failed</p>");
            verification = false;
        }

        try {
            new_patient.setEmail(request.getParameter("email"));
        }
        catch (Exception e) {
            out.println("<p>Patient's email form verification failed</p>");
            verification = false;
        }

        String gender = request.getParameter("gender");

        switch (gender){
            case "m":
                new_patient.setGender(Gender.Male);
                break;
            case "f":
                new_patient.setGender(Gender.Female);
                break;
            case "u":
                new_patient.setGender(Gender.Unknown);
                break;
            default:
                out.println("<p>Invalid Patient's gender</p>");
                verification = false;
                break;
        }

        if (verification){
            if (!PatientRegistrationSystemDatabase.getInstance().patientWithIdExists(new_patient.getId())){
                try {
                    PatientRegistrationSystemDatabase.getInstance().addNewPatient(new_patient);
                    out.println("<p>Patient added successfully</p>");
                    Cookie cookie = new Cookie("last_added", new_patient.getId());
                    response.addCookie(cookie);
                }
                catch (Exception e){
                    out.println("<p>Error when adding patient:</p>");
                    out.println("<p>"+e.getMessage()+"</p>");
                }
            }
            else {
                out.println("Patient with this id already Exists");
            }
            out.println("<a href=\"new_patient_form.jsp\">return to form</a>");
            out.println("<a href=\"index.jsp\">return to main page</a>");
        }
        else {
            out.println("<p>Form data verification is a crap! Fix it immediately...</p>");
        }
        out.println("</body>");
    }
}
