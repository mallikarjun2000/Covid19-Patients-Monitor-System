<%@page import="com.model.Alert"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%
	User user = (User)request.getAttribute("user");
System.out.print(user.getUsername());
	if(user.getUsername() == null)
	{
		out.print("<h3>no user found</h3>");
	}
	Boolean status = (Boolean) request.getAttribute("stauts");
	Boolean isDoctor = (Boolean)request.getAttribute("isDoctor");
	System.out.println(isDoctor);
	ArrayList<Alert> listOfHealthRecordsOfUser = (ArrayList<Alert>) request.getAttribute("listOfHealthRecordsOfUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<title>${user.getUsername()}</title>
</head>
<body style="background:#E3DFDE;margin: 20px">
<div class="container d-flex flex-column">
	<h3 class="align-self-center m-2"> Details of ${user.getFirstName()} </h3>
	<div class="container card p-1">
		<div class="row">
			<h6 class="col-12 col-md-4">Name </h6>
			<p class="col-12 col-md-8">: ${user.getFirstName()}</p>
		</div>
		<div class="row">
			<h6 class="col-12 col-md-4">Email </h6>
			<p class="col-12 col-md-8">: ${user.getEmail()}</p>
		</div>
		<div class="row">
			<h6 class="col-12 col-md-4">Contact Number </h6>
			<p class="col-12 col-md-8">: ${user.getPhoneNumber()}</p>
		</div>
	</div>
	<hr/>
	<div class="d-flex flex-row align-items-end justify-content-end mt-20 mb-2 mr-4">
	<c:if test="${isDoctor==true}">
		<c:if test="${status == false }">
			<a onclick="history.back()" href="<c:url value = "recoveruser?username=${user.getUsername()}"/>" class="btn btn-success">Recovered</a>
		</c:if> 
		<c:if test="${status == true }">
			<a onclick="history.back()" href="<c:url value = "recoveruser?username=${user.getUsername()}"/>" class="btn btn-danger">Not Recovered</a>
		</c:if>
<%-- 		<a href="<c:url value = "recoveruser?username=${user.getUsername()}"/>" class="btn btn-success">Recovered</a>
 --%></c:if>
		<button type="button" class="btn btn-primary offset-1" name="back" onclick="history.back()"> Go Back</button>
		<a class="btn btn-warning ml-4">Print</a>
	</div>
	<hr/>
	<c:if test="${isDoctor == true}">
	<h5><strong>Previous Records</strong></h5>
	<div class="col-12 row">
		<table class="table">
			<thead  class="thead-dark">
				<tr>
					<th scope="col">Date</th>
			      <th scope="col">Weight</th>
			      <th scope="col">Temperature</th>
			      <th scope="col">Blood Pressure</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listOfHealthRecordsOfUser}" var = "alert">
				<tr>
					<td>${alert.getCreatedDate() }</td>
					<td>${alert.getWeight() }</td>
					<td>${alert.getTemperature() }</td>
					<td>${alert.getBloodPressure() }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</c:if>
</div>
</body>
</html>