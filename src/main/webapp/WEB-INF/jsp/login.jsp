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
	<title>Log In</title>
	
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
				<h3 class="panel-title">Please log in</h3>
			</div>
			
		<div class="panel-body">
			
				<c:if test="${param.error != null}">
			    	<div class="alert alert-danger">
			        	Invalid username and password.
			    	</div>
		    	</c:if>
		    
			    <c:if test="${param.logout != null}">
				    <div class="alert alert-danger">
				        You have been logged out.
				    </div>
			    </c:if>
		
					<form:form role="form" method="post">
					
						<div class="form-group">
							<label for="username">User Name</label>
							<input id="username" name="username" type="text" class="form-control" placeholder="Enter useranme" />
						</div>
						
						<div class="form-group">
							<label for="password">Password</label>
							<input type="password" id="password" name="password" class="form-control" placeholder="Password" />
							<form:errors cssClass="error" path="password" />
						</div>
						
						<div class="form-group">
	                       <input name="remember-me" type="checkbox"> Remember me
				        </div>
				           
						<button type="submit" class="btn btn-primary">Log In</button>
						<div style="float:right; position: relative">
							<a class="btn btn-danger" href="/forgot-password">Forgot Password</a>
						</div>   
					</form:form>
					
			</div>
			<div class="panel-footer">
				Don't have an account! <a href="/register">Register Here</a>
            </div>
		</div>
	</div>
</div>
</body>
</html>