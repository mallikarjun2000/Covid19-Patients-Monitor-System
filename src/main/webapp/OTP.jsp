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
</head>
<body style="background-color: #424242" class="d-flex">
	<div class="card col-5">
		<div class="card-body">
		<h3>Enter OTP</h3>
		<p>OTP has been sent to <strong><%out.print(request.getAttribute("email")); %></strong><p>
			<form action="verifiyotp" method="post">
			  <div class="form-group">
			    <label for="formGroupExampleInput"><h6>OTP</h6></label>
			    <input minlength="4" type="text" class="form-control" id="formGroupExampleInput" placeholder="4 digit code" name="otp">
			  </div>
			  <input type="submit" class="btn btn-warning" value="Submit"/>
			  <a class="btn btn-danger" href="index.jsp">Go Back</a>
			  <hr/>
			</form>
			<!-- <a href="userRegistration.html">New ? Register here</a> -->
		</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</body>
</html>