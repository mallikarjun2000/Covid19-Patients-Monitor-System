<%@page import="com.model.Alert"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous"><title>Admin Home</title>
<title>Doctor Home</title>
</head>
<%
	ArrayList<Alert> listOfPatientsUnderRisk = (ArrayList)session.getAttribute("listOfPatientsUnderRisk");
%>
<body style="background:#E3DFDE">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark d-flex flex-row">
            <a class="navbar-brand" href="#">CMT</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse d-flex justify-content-end" id="navbarNav">
              <ul class="navbar-nav ml-5">
                <li class="nav-item active">
                  <a class="nav-link" href="doctorhome">Home<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="getpatients">My Patients</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">Helplines</a>
                </li>
                <li class="nav-item">
                      <form action="logout" method="post">
                    <input class="btn btn-danger" type="submit" value="Logout"/>
                      </form>
                </li>
              </ul>
            </div>
       </nav>
       
       <!--  Update Dialgue  -->
       <div id="updateModal" class="modal fade" role="dialog">
		    	<div class="modal-dialog modal-lg" role="content">
		    		<div class="modal-content">
		    			<div class="modal-header">
		    			<h4 class="modal-title">Edit Details </h4>
		                <button type="button" class="close" data-dismiss="modal">&times;</button>
				    	</div>
				    	<div class="modal-body">
				    		<form action="doctorservlet" method="put">
				    			<div class="form-group row">
				    				<label class="col-12 col-md-3">First Name :</label>
				    				<input class="form-control col-12 col-md-7" type="text" 
				    					value="${doctor.getFirstName()}" name="firstname"/>
				    			</div>
				    			<div class="form-group row">
				    				<label class="col-12 col-md-3">Last Name :</label>
				    				<input class="form-control col-12 col-md-7" type="text" 
				    					value="${doctor.getLastName()}" name="lastname"/>
				    			</div>
				    			<div class="form-group row">
				    				<label class="col-12 col-md-3">Username :</label>
				    				<input class="form-control col-12 col-md-7" type="text" 
				    					value="${doctor.getUsername()}" name="username"/>
				    			</div>
				    			<div class="form-group row">
				    				<label class="col-12 col-md-3">Email :</label>
				    				<input class="form-control col-12 col-md-7" type="text" 
				    					value="${doctor.getEmail()}" name="email"/>
				    			</div>
				    			<div class="form-group row">
				    				<label class="col-12 col-md-3">Contact Number :</label>
				    				<input class="form-control col-12 col-md-7" type="text" 
				    					value="${doctor.getPhoneNumber()}" name="phonenumber"/>
				    			</div>
				    			<div class="form-row">
		                            <button type="button" class="btn btn-secondary btn-sm ml-auto" data-dismiss="modal">Cancel</button>
		                            <input class="btn btn-primary ml-2" type="submit" value="submit" />     
		                        </div>
				    		</form>
				   		</div>
		    		</div>
		    	</div>
		    </div>
		    
		    
       
<div class="container d-flex flex-column align-content-center justify-content-around">
	<h3>Welcome Doctor</h3>
	
	<div id="userDetails" class="container col-9 card d-flex flex-column p-2" style="margin: 10px">
          	  		<h4>Doctor Details</h4>
              		<div class="row">
              			<label class="col-4 col-md-3">Name </label>
              			<label class="col-6 col-md-4">: ${doctor.getFirstName()} ${doctor.getLastName()}</label>
              		</div>
              		<div class="row">
              			<label class="col-4 col-md-3">Email </label>
              			<label class="col-6 col-md-4">: ${doctor.getEmail()}</label>
              		</div>
              		<div class="row">
              			<label class="col-4 col-md-3">Username </label>
              			<label class="col-6 col-md-4">: ${doctor.getUsername()}</label>
              		</div>
              		<div class="row">
              			<label class="col-4 col-md-3">Contact Number </label>
              			<label class="col-6 col-md-4">: ${doctor.getPhoneNumber()}</label>
              		</div>
              		<a data-toggle="modal" data-target="#updateModal" class="btn btn-danger col-12 col-md-2" href="#">Update Details</a>
              </div>
              <a href="userRegistration.html" class=" col-12 col-md-3 btn btn-warning">Register a Patient</a>
	<table class="table">
       			<thead class="thead-dark">
       				<tr>
					  <th scope="col">Reported Date</th>
					  <th>Username</th>
				      <th scope="col">Report  Id</th>
				      <th scope="col">View Report</th>
					</tr>
       			</thead>
       			<tbody>
       				<c:forEach items="${listOfPatientsUnderRisk}" var="alert">
       					<tr>
       						<form class="form" action="getreport">
       							<td scope="col">${alert.getCreatedDate()}</td>
       							<td scope="col">${alert.getUsername()}</td>
       							<td scope="col"><input class="form-control" name="reportId" type="text" value="${alert.getReportId()}"/></td>
       							<td scope="col"><input type="submit" value="View Report" class="btn btn-warning" /></td>
       						</form>
       					</tr>
       				</c:forEach>
       			</tbody>
       		</table>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>