<%@page import="com.model.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" href="buttons.css" ></link>
<title>All Users</title>
</head>
<%
	ArrayList<User> listOfPatients = (ArrayList<User>)request.getAttribute("listOfPatients");
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
                  <a class="nav-link" href="getpatients">Patients List</a>
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
<div class="container d-flex flex-column align-content-center justify-content-center p-1">
	<h6>Search Users</h6>
		<form class="form">
			<div class="row">
				<input placeholder="Enter Username" class=" col-12 col-md-8 form-control" type="text" name="username" />
				<input class="col-12 col-md-2 offset-md-1 btn btn-primary" value="search" type="submit"/>
			</div>
		</form>
	<h3> All Users</h3>
	<c:forEach items="${listOfPatients}" var ="user">
		<div class="card col-12 m-2 border border-success rounded-5 p-2">
			<div class="row">
				<label class="col-12 col-md-4">Name</label>
				<label class="col-12 col-md-5"> : ${user.getFirstName()} ${user.getLastName()}</label>
				<a class="btn btn-danger">Delete User</a>
			</div>
			<div class="row">
				<label class="col-12 col-md-4">Contact Number</label>
				<label class="col-12 col-md-5"> : ${user.getPhoneNumber()}</label>
			</div>
			<div class="row">
				<label class="col-12 col-md-4">Email</label>
				<label class="col-12 col-md-5"> : ${user.getEmail()}</label>
				<a href="<c:url value = "getuser?username=${user.getUsername()}"/>" class="btn btn-success">View Details</a>
			</div>
		</div>
	</c:forEach>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>