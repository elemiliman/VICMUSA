<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Forgot Password</title>
	
	<!-- Bootstrap -->
	<link href="/public/lib/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<!-- Our CSS -->
	<link href="/public/css/styles.css" rel="stylesheet">

</head>
<body>
<div class="container">
	<div style="margin-top:70px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2"> 
		<div class="panel panel-primary">
			
			<div class="panel-heading">
				<h3 class="panel-title">Forgot password?</h3>
			</div>
				
			<div class="panel-body">
				
					<form:form modelAttribute="forgotPasswordForm" role="form">
				
				<form:errors />
				
					<div class="form-group">
					<p class="help-block">Enter your email address and we will send you a link to reset your password.</p>
						<form:input path="email" type="email" class="form-control" placeholder="Enter email" />
						<form:errors cssClass="error" path="email" />
					</div>
							
					<button type="submit" class="btn btn-primary">Reset password</button>
					</form:form>
			</div>
		</div>
	</div>
</div>
</body>
</html>