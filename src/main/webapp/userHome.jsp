<%@page import="com.model.Alert"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet"> 
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <title>Home Page</title>
    </head>
    <body style="background:#E3DFDE;">
    <%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    User user = (User)request.getAttribute("user");
    if(user != null)
    System.out.print(user.getFirstName()+"\n"+user.getLastName()+"\n"+user.getUsername()+"\n");
    else
    	System.out.print("user is null");
    ArrayList<Alert> listOfRecords = (ArrayList<Alert>)request.getAttribute("listOfRecords");
    %>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark d-flex flex-row">
            <a class="navbar-brand" href="#">CMT</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse d-flex justify-content-end" id="navbarNav">
              <ul class="navbar-nav ml-5">
                <li class="nav-item active">
                  <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <!-- <li class="nav-item">
                  <a class="nav-link" href="#userDetails">Profile</a>
                </li> -->
                <li class="nav-item">
                  <a class="nav-link" href="https://www.mohfw.gov.in/pdf/StatewiseCovidHospitalslink19062020.pdf">Helplines</a>
                </li>
                <li class="nav-item">
                      <form action="logout" method="post">
                    <input class="btn btn-danger" type="submit" value="Logout"/>
                      </form>
                </li>
              </ul>
            </div>
          </nav>
          
          <div id="loginModal" class="modal fade" role="dialog">
		        <div class="modal-dialog modal-lg" role="content">
		            <!-- Modal content-->
		            <div class="modal-content" style="height: 200px">
		                <div class="modal-header">
		                    <h4 class="modal-title">Enter details </h4>
		                    <button type="button" class="close" data-dismiss="modal">&times;</button>
		                </div>
		                <div class="modal-body">
		                    <form action="usershealth" method="post">
		                        <div class="form-row">
		                            <div class="form-group col-sm-4">
		                                    <label class="sr-only col-12" for="exampleInputEmail3">Weight</label>
		                                    <input minlength="2" type="text" class="form-control form-control-sm mr-1" name="weight" id="exampleInputEmail3" placeholder="Enter Weight">
		                            </div>
		                            <div class="form-group col-sm-4">
		                                <label class="sr-only col-12" for="exampleInputPassword3">Temperature</label>
		                                <input minlength="2" type="text" class="form-control form-control-sm mr-1" name="temperature" id="exampleInputPassword3" placeholder="Enter Temperature">
		                            </div>
		                            <div class="form-group col-sm-4">
		                                <label class="sr-only col-12" for="exampleInputPassword4">Blood Pressure</label>
		                                <input minlength="2" type="text" class="form-control form-control-sm mr-1" name="bloodpressure" id="exampleInputPassword4" placeholder="Enter Blood Pressure  / 120">
		                            </div>
		                            <br/>
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
		    
		    <div id="updateModal" class="modal fade" role="dialog">
		    	<div class="modal-dialog modal-lg" role="content">
		    		<div class="modal-content">
		    			<div class="modal-header">
		    			<h4 class="modal-title">Edit Details </h4>
		                <button type="button" class="close" data-dismiss="modal">&times;</button>
				    	</div>
				    	<div class="modal-body">
				    		<form action="userupdate" method="post">
				    			<div class="form-group row">
				    				<label class="col-12 col-md-3">First Name :</label>
				    				<input class="form-control col-12 col-md-7" type="text" 
				    					value=${user.getFirstName() } name="firstname"/>
				    			</div>
				    			<div class="form-group row">
				    				<label class="col-12 col-md-3">Last Name :</label>
				    				<input class="form-control col-12 col-md-7" type="text" 
				    					value=${user.getLastName() } name="lastname"/>
				    			</div>
				    			<div class="form-group row">
				    				<label class="col-12 col-md-3">Username :</label>
				    				<input class="form-control col-12 col-md-7" type="text" 
				    					value=${user.getUsername() } name="username"/>
				    			</div>
				    			<div class="form-group row">
				    				<label class="col-12 col-md-3">Email :</label>
				    				<input class="form-control col-12 col-md-7" type="text" 
				    					value=${user.getEmail() } name="email"/>
				    			</div>
				    			<div class="form-group row">
				    				<label class="col-12 col-md-3">Contact Number :</label>
				    				<input class="form-control col-12 col-md-7" type="text" 
				    					value=${user.getPhoneNumber() } name="phonenumber"/>
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
          
          <div class="container-fluid d-flex justify-content-center flex-column">
              <%-- <div class="row">
                    <h4 class="col-12 offset-md-2 col-md-4"><strong>Hello,</strong> <span style="font-family: 'Roboto', sans-serif">
                    <%out.print(session.getAttribute("username"));%>
                    </span></h4>
              </div> --%>
          	  <div id="userDetails" class="container col-12 card d-flex flex-column p-2" style="margin: 10px">
          	  		<h4>User Details</h4>
              		<div class="row">
              			<label class="col-4 col-md-3">Name </label>
              			<label class="col-6 col-md-4">: ${user.getFirstName()} ${user.getLastName()}</label>
              		</div>
              		<div class="row">
              			<label class="col-4 col-md-3">Email </label>
              			<label class="col-6 col-md-4">: ${user.getEmail()}</label>
              		</div>
              		<div class="row">
              			<label class="col-4 col-md-3">Username </label>
              			<label class="col-6 col-md-4">: ${user.getUsername()}</label>
              		</div>
              		<div class="row">
              			<label class="col-4 col-md-3">Contact Number </label>
              			<label class="col-6 col-md-4">: ${user.getPhoneNumber()}</label>
              		</div>
              		<a data-toggle="modal" data-target="#updateModal" class="btn btn-danger col-12 col-md-2" href="#">Update Details</a>
              </div>
          </div>
          <div class="d-flex flex-column align-content-center justify-content-center container">
          		<h4> Your Previous Records</h4>
          		<table class="table">
          			<thead class="thead-dark">
          				<tr>
          					<th>Date</th>
          					<th>Report Id</th>
          					<th>Weight</th>
          					<th>Temperature</th>
          					<th>Blood Pressure</th>
          				</tr>
          			</thead>
          			<tbody>
          				<c:forEach items="${listOfHealthRecords}" var="record">
          					<tr>
						      <th scope="row">${record.getCreatedDate()}</th>
						      <td>${record.getReportId()}</td>
						      <td>${record.getWeight()}</td>
						      <td>${record.getTemperature()}</td>
						      <td>${record.getBloodPressure()} / 120</td>
						    </tr>
          				</c:forEach>
          			</tbody>
          		</table>
          	<button class=" col-4 btn btn-danger" data-toggle="modal" data-target="#loginModal" style="width: 15%; justify-self: center; align-self: center; margin: 5px;"><a>Add Record</a></button>
          		
          </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>
</html>