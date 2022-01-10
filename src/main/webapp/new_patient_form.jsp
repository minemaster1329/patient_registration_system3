<%@ page
        import="org.patient_registration_system.patient_registration_system3.model.PatientRegistrationSystemDatabase" %>
<%@ page import="org.patient_registration_system.patient_registration_system3.pubstuff.PublicRegexes" %><%--
  Created by IntelliJ IDEA.
  User: stduser
  Date: 04.01.2022
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
    <body>
        <h1>New patient data</h1>
        <br>
        <form action="AddNewPatientServlet" method="GET" name="new_patient_form" onsubmit="return validate_form()">
            <p>PESEL: <input type="text" size="20" name="pesel"/>&nbsp;<span id="form_pesel_validity"></span></p>
            <p>First name: <input type="text" size="20" name="firstname"/>&nbsp;<span id="form_name_validity"></span></p>
            <p>Surname: <input type="text" size="20" name="surname"/>&nbsp;<span id="form_surnamename_validity"></span></p>
            <p>Middle name: <input type="text" size="20" name="middlename"/>&nbsp;<span id="form_middlename_validity"></span></p>
            <p>Email name: <input type="text" size="20" name="email"/>&nbsp;<span id="form_email_validity"></span></p>
            <p>Gender: <select name="gender"><option value="m">Male</option><option value="f">Female</option><option value="u">Unknown</option></select></p>
            <br>
            <button type="submit">Submit</button>
            <button type="reset">Reset</button>
        </form>
        <script type="text/javascript">
            function validate_form(){
                let name_regex = /^[A-Z][a-z]+$/;
                let name_value = document.forms["new_patient_form"]["firstname"].value;

                if (name_regex.test(name_value)){
                    let sp = document.getElementById("form_name_validity");
                    sp.innerText = "Name is valid";
                }

                return false;
            }
        </script>
    </body>
</html>
