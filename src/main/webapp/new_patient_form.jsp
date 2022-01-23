<%@ page
        import="org.patient_registration_system.patient_registration_system3.model.PatientRegistrationSystemDatabase" %>
<%@ page import="org.patient_registration_system.patient_registration_system3.pubstuff.PublicRegexes" %>
<%@ page import="org.patient_registration_system.patient_registration_system3.pubstuff.PublicStaticMethods" %><%--
  Created by IntelliJ IDEA.
  User: stduser
  Date: 04.01.2022
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/new_patient_form.css"/>
    <title>Title</title>
</head>
    <body>
        <h1>New patient data</h1>
        <br>
        <form action="AddNewPatientServlet" method="POST" name="new_patient_form" onsubmit="return validate_form()">
            <p>PESEL: <input type="text" size="20" name="pesel"/>&nbsp;<span id="form_pesel_validity" class="input_invalid"></span></p>
            <p>First name: <input type="text" size="20" name="firstname"/>&nbsp;<span id="form_name_validity" class="input_invalid"></span></p>
            <p>Surname: <input type="text" size="20" name="surname"/>&nbsp;<span id="form_surname_validity" class="input_invalid"></span></p>
            <p>Middle name: <input type="text" size="20" name="middlename"/>&nbsp;<span id="form_middlename_validity" class="input_invalid"></span></p>
            <p>Email : <input type="text" size="20" name="email"/>&nbsp;<span id="form_email_validity" class="input_invalid"></span></p>
            <p>Gender: <select name="gender"><option value="m">Male</option><option value="f">Female</option><option value="u">Unknown</option></select></p>
            <br>
            <button type="submit">Submit</button>
            <button type="reset">Reset</button>
        </form>
        <p><a href="index.jsp">return to main page</a></p>
        <script type="text/javascript">
            function validate_form(){
                clearAllSpans();
                let all_data_correct = true;

                let regex = /^\d{11}$/;
                let value = document.forms["new_patient_form"]["pesel"].value;
                if (!regex.test(value)){
                    let sp = document.getElementById("form_pesel_validity");
                    sp.innerText = "Invalid PESEL format";
                    all_data_correct = false;
                }

                else if (!validatePESELchecksum(value)){
                    let sp = document.getElementById("form_pesel_validity");
                    sp.innerText = "Invalid PESEL checksum";
                    all_data_correct = false;
                }

                regex = /^[A-Z][a-z]+$/;
                value = document.forms["new_patient_form"]["firstname"].value;
                if (!regex.test(value)){
                    let sp = document.getElementById("form_name_validity");
                    sp.innerText = "Invalid name";
                    all_data_correct = false;
                }

                value = document.forms["new_patient_form"]["surname"].value;
                if (!regex.test(value)){
                    let sp = document.getElementById("form_surname_validity");
                    sp.innerText = "Invalid surname";
                    all_data_correct = false;
                }

                value = document.forms["new_patient_form"]["middlename"].value;
                if (value.length !== 0 && !regex.test(value)){
                    let sp = document.getElementById("form_middlename_validity");
                    sp.innerText = "Invalid middlename";
                    all_data_correct = false;
                }

                regex = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,6}$/;
                value = document.forms["new_patient_form"]["email"].value;
                if (value.length !== 0 && regex.test(value)){
                    let sp = document.getElementById("form_email_validity");
                    sp.innerText = "Invalid email";
                    all_data_correct = false;
                }
                return all_data_correct;
            }

            function clearAllSpans(){
                document.getElementById("form_name_validity").innerText="";
                document.getElementById("form_surname_validity").innerText="";
                document.getElementById("form_middlename_validity").innerText="";
                document.getElementById("form_pesel_validity").innerText="";
                document.getElementById("form_email_validity").innerText="";
            }

            function validatePESELchecksum(pesel){
                let weights = [1,3,7,9];
                let sum = 0;
                for (let i = 0; i < 10; i++){
                    sum += parseInt(pesel.charAt(i))*weights[i % 4];
                }

                if (sum > 0){
                    sum %=10;
                    sum = 10-sum;
                }
                return (parseInt(pesel.charAt(10)) === sum);
            }
        </script>
    </body>
</html>
