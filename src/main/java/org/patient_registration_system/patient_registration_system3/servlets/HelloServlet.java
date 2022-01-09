package org.patient_registration_system.patient_registration_system3.servlets;

import org.patient_registration_system.patient_registration_system3.model.PatientRegistrationSystemDatabase;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private boolean databaseInitializedCorrectly;
    public void init() {
        try {
            PatientRegistrationSystemDatabase.getInstance().initializeSingleton();
            message = "Database initialized correctly";
            databaseInitializedCorrectly = true;
        }
        catch (Exception e){
            databaseInitializedCorrectly = false;
            message = "Error when initializing database: "+e.getMessage();
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        if (databaseInitializedCorrectly){
            Long patients_count = PatientRegistrationSystemDatabase.getInstance().getPatientsCount();
            out.println("<p>Patients count: "+patients_count+"</p>");
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }
}