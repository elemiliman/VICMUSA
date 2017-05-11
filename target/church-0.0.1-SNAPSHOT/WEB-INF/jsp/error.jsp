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
	<title>Errors</title>
	
	<!-- Bootstrap -->
	<link href="/public/lib/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<!-- Our CSS -->
	<link href="/public/css/styles.css" rel="stylesheet">

</head>
<body>
<div class="container">
	<div class="col-md-6 col-md-offset-3">
	
	<div class="alert alert-danger">
		<p>There was an unexpected error (type=${error}, status=${status}): <i>${message}</i></p>
		<p>Click <a href="/">here</a> to go to home page</p>
	</div>
	<!-- jQuery (necessary for Boostrap's JavaScript plugins)  -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
	<script src="/public/lib/bootstrap-3.3.7/js/bootstrap.min.js"></script>
	</div>
</div>
</body>