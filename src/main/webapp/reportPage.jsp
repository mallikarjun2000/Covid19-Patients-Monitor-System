<%@page import="com.model.Alert, com.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<title>Report Details</title>
</head>
<body style="background:#E3DFDE;margin: 20px">
<%
	User user = (User)request.getAttribute("user");
	Alert alert = (Alert)request.getAttribute("alert");
%>
<div class="container d-flex flex-column card">
	<h3 class="align-self-center m-2"> Report of ${user.getFirstName()} </h3>
	<div class="container">
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
				<tr>
					<td>${alert.getCreatedDate() }</td>
					<td>${alert.getWeight() }</td>
					<td>${alert.getTemperature() }</td>
					<td>${alert.getBloodPressure() }</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="row">
		<p class="ml-4">The above data is reported as higher than normal range on ${alert.getCreatedDate()}. Please contact the person immediately</p>
	</div>
	<div class="d-flex flex-row align-items-end justify-content-end mt-20 mb-2 mr-4">
		<a href="doctorhome" class="btn btn-primary">Go Back</a>
		<a class="btn btn-warning ml-4">Print</a>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>