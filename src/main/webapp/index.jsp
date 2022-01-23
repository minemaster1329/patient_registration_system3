<%@ page
        import="org.patient_registration_system.patient_registration_system3.model.PatientRegistrationSystemMySQLDatabaseSingleton" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <title>Patient Registration System</title>
</head>
<body>
<h1><%= "Patient's registration system" %>
</h1>
<br/>
<a href="hello-servlet">Get patients count</a>
</br>
<a href="patientsview-servlet">Show all patients</a>
</br>
<a href="new_patient_form.jsp">Add new patient</a>
</body>
</html>