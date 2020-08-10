<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Covid19 Tracker</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<style>
	.card{
		  /*background-color: #FF4136;*/
		  width: 400px;
		  height: 320px;
		  position: absolute;
		  left: 50%;
		  top: 50%;
		  transform: translate(-50%, -50%);
	}
	
	hr {
	border: black;
	}
</style>
<%
	String username = (String)session.getAttribute("username");
	String loginAs = (String)session.getAttribute("loginas");
	String homeUrl = null;
	if(loginAs.equals("patient"))
	{
		homeUrl = "userhome";
	}
	else
	{
		if(loginAs.equals("doctor"))
		{
			homeUrl ="doctorhome";
		}
	}
	System.out.print("going to : "+homeUrl);
%>
</head>
<body style="background-color: #424242" class="d-flex">
	<div class="card col-5">
		<div class="card-body">
		<h3>User Verified!</h3>
		<p>Redirecting to ... ${homeUrl} click on Login</p>
			<form action=<%out.print(homeUrl);%>>
			  <div class="form-group">
			    <label for="formGroupExampleInput"><h6>Verified!</h6></label>
			    <input value="${username}" minlength="4" type="text" class="form-control" id="formGroupExampleInput" placeholder="4 digit code" name="username">
			  </div>
			  <input type="submit" class="btn btn-success" value="Login"/>
			  <hr/>
			</form>
		</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>