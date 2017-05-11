<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1">
		
		<title><c:out value="${PageTitle}"/> - Vineyard International Christian Ministries Inc.</title>
        
       <!-- Bootstrap -->
	   <link href="/public/lib/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
	   
	   <!-- VICM Icon --> 
	   <link rel="icon" type="image/png" href="/public/img/favicon.png"/>
	   
	   <!-- VICM CSS -->
	   <link href="/public/css/style.css" rel="stylesheet">
	   
	   <!-- Loading third party fonts -->
	   <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css" rel="stylesheet">
	   <link href="/public/lib/bootstrap-3.3.7/fonts/novecento-font/novecento-font.css" rel="stylesheet" type="text/css">
	   <link href="/public/lib/bootstrap-3.3.7/fonts/font-awesome.min.css" rel="stylesheet" type="text/css">
		
		<!--[if lt IE 9]>
		<script src="js/ie-support/html5.js"></script>
		<script src="js/ie-support/respond.js"></script>
		<![endif]-->

	</head>


	<body>
		<div class="site-content">
			<header class="site-header">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6 col-md-pull-1 hidden-xs hidden-sm">
                            <span class="map-address"><strong><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span> &nbsp;1140 TELLER AVENUE BRONX, NY 10456</strong></span>
                        </div>
                        <div class="col-md-6 col-md-push-1">
                        	<sec:authorize access="isAnonymous()">
	                            <strong><a href="<c:url value='/register' />"><span class="glyphicon glyphicon-pencil"></span> REGISTER</a></strong>&nbsp;|&nbsp;
	                            <strong><a href="/login">LOGIN <span class="glyphicon glyphicon-log-in"></span></a></strong> &nbsp;&nbsp;&nbsp;&nbsp;
	                        </sec:authorize>
	                        <sec:authorize access="isAuthenticated()">
				                <strong><a href="/users/<sec:authentication property='principal.user.userName' />" > <span class="glyphicon glyphicon-user"></span>
				                    <sec:authentication property="principal.user.firstName" />
				                </a></strong>&nbsp;|&nbsp;
				         
				                <strong><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log out</a></strong>&nbsp;&nbsp;&nbsp;&nbsp;
				            </sec:authorize>
	                            <a href ="<c:url value='/donate/to-vicm'/>" class = "btn btn-primary donate-btn" role = "button"><strong>DONATE</strong></a>
                        </div>
                    </div>
                </div>
				<div class="container">
					<a  href="<c:url value='/' />" class="branding">
						<img src="/public/img/vicmusa-logo.PNG" alt="" class="logo">
						<h1 class="site-title"><strong>VINEYARD INTERNATIONAL CHRISTIAN MINISTRIES INC.</strong></h1>
					</a>

					<div class="main-navigation">
						<button class="menu-toggle"><i class="fa fa-bars"></i> Menu</button>
						<ul class="menu">
                            <li class="menu-item"><a href="<c:url value='/' />"><strong>Home</strong></a></li>
                            <li class="menu-item"><a href="<c:url value='/about-us'/>"><strong>About</strong></a></li>
                            <li class="menu-item"><a href="<c:url value='/sermons'/>"><strong>Sermons</strong></a></li>
                            <li class="menu-item"><a href="<c:url value='/events'/>"><strong>Events</strong></a></li>
                            <li class="menu-item"><a href="#"><strong>Ministries</strong></a></li>
                            <li class="menu-item"><a href="<c:url value='/gallery'/>"><strong>Gallery</strong></a></li>
                            <li class="menu-item"><a href="<c:url value='/blogs'/>"><strong>Blog</strong></a></li>
                            <li class="menu-item"><a href="#"><strong>Contact</strong></a></li>
						</ul>
					</div>

					<div class="mobile-navigation"></div>
				</div>
			</header> <!-- .site-header -->
	
	
	
		
	<sec:authorize access="hasRole('ROLE_UNVERIFIED')">
		<div class="alert alert-warning alert-dismissable" id="floating_alert">
		  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		  Your email id is unverified. <a href="/users/resend-verification-mail">Click here</a> to get the verification email.
		</div>
    </sec:authorize>
    
	<c:if test="${not empty flashMessage}">
		<div class="alert alert-${flashKind} alert-dismissable" id="floating_alert">
		  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		  ${flashMessage}
		</div>
	</c:if>