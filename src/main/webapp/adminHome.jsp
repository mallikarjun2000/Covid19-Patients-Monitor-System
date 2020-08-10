<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,com.model.Alert " %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous"><title>Admin Home</title>
</head>
<body style="background:#b39ddb">
<%!
	ArrayList<Alert> listOfAlerts = null;
%>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	String username = String.valueOf(session.getAttribute("username"));
	if(username == null)
		response.sendRedirect("adminLogin.html");
	ArrayList<Alert> listOfAlerts = (ArrayList<Alert>)session.getAttribute("listOfAlerts");
	int numberOfPatientsAdmitted = (Integer)request.getAttribute("numberOfPatientsAdmitted");
	int numberOfPatinetsRecovered = (Integer)request.getAttribute("numberOfPatinetsRecovered");
%>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark d-flex flex-row">
            <a class="navbar-brand" href="#">CMT</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse d-flex justify-content-end" id="navbarNav">
              <ul class="navbar-nav ml-5">
                <li class="nav-item active">
                  <a class="nav-link" href="adminhome">Home<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="getuserslist">Users List</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="getdoctors">DoctorsList</a>
                </li>
                <li class="nav-item">
                      <form action="logout" method="post">
                    <input class="btn btn-danger" type="submit" value="Logout"/>
                      </form>
                </li>
              </ul>
            </div>
       </nav>
       <div class="container d-flex flex-column align-content-center justify-content-center pt-1">
       <a class="col-3 btn btn-warning align-self-center" href="doctorRegistrationPage.html">Register a new Doctor</a>
       	<div class="row bg-dark mt-5">
       		<div class="col-4 card" style="padding: 1px;">
       			<div class="col-12 bg-danger">
       				<h3>${numberOfPatientsAdmitted }</h3>
       				<p>Number of active patients</p>
       			</div>
       		</div>
       		<div class="col-4 card" style="padding: 1px">
       		<div class="col-12 bg-success">
       				<h3>${numberOfPatinetsRecovered }</h3>
       				<p>Number of recovered patients</p>
       			</div>
       		</div>
       		<div class="col-4 card" style="padding: 1px">
       		<div class="col-12 bg-warning">
       				<h3>${numberOfPatinetsRecovered + numberOfPatientsAdmitted }</h3>
       				<p>Total patients</p>
       			</div>
       		</div>
       		</div>
       	</div>
       </div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
       
</body>
</html>