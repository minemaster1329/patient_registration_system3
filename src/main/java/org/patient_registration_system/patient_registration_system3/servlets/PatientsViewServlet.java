package org.patient_registration_system.patient_registration_system3.servlets;

import org.patient_registration_system.patient_registration_system3.model.PatientRegistrationSystemDatabase;
import org.patient_registration_system.patient_registration_system3.model.data_models.Person;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
public class PatientsViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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

        out.println("</table>");

        //List<Person> getAllPatients = PatientRegistrationSystemDatabase.getInstance().getAllPatients();
        /*for (Person p : getAllPatients){
            out.println("<tr>");
            out.println("<td>"+p.getId()+"</td>");
            out.println("<td>"+p.getName()+"</td>");
            out.println("<td>"+p.getSurname()+"</td>");
            out.println("<td>"+p.getMiddleName()+"</td>");
            out.println("<td>"+p.getGender()+"</td>");
            out.println("<td>"+p.getEmail()+"</td>");
            out.println("</tr>");
        }
        out.println("</table>");*/
        Cookie[] cookies = request.getCookies();
        if (cookies.length > 0){
            Optional<Cookie> cookie = Arrays.stream(cookies).filter((p)->p.getName().equals("last_added")).findFirst();
            cookie.ifPresent(value -> out.println("<p>Last added by you: " + value.getValue() + "</p>"));
        }
        out.println("</body></html>");
    }
}
