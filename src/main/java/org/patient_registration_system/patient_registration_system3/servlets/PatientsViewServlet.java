package org.patient_registration_system.patient_registration_system3.servlets;

import org.patient_registration_system.patient_registration_system3.model.PatientRegistrationSystemDatabase;
import org.patient_registration_system.patient_registration_system3.model.data_models.Person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "patientsviewServlet", value = "/patientsview-servlet")
public class PatientsViewServlet extends HttpServlet {

    public void init() {
        try {
            PatientRegistrationSystemDatabase.getInstance().initializeSingleton();
        }
        catch (Exception e){

        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Patients: </h1>");
        out.println("<table border=\"5\">");
        out.println("<tr>");
        out.println("<td>PESEL</td>");
        out.println("<td>Name</td>");
        out.println("<td>Surname</td>");
        out.println("<td>Middle Name</td>");
        out.println("<td>Gender</td>");
        out.println("<td>Email</td>");
        out.println("</tr>");

        List<Person> getAllPatients = PatientRegistrationSystemDatabase.getInstance().getAllPatients();
        for (Person p : getAllPatients){
            out.println("<tr>");
            out.println("<td>"+p.getId()+"</td>");
            out.println("<td>"+p.getName()+"</td>");
            out.println("<td>"+p.getSurname()+"</td>");
            out.println("<td>"+p.getMiddleName()+"</td>");
            out.println("<td>"+p.getGender()+"</td>");
            out.println("<td>"+p.getEmail()+"</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body></html>");
    }
}
