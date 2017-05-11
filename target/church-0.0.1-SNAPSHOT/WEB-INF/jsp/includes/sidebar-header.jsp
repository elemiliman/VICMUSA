<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><c:out value="${PageTitle}"/></title>
	
	<!-- Bootstrap -->
	<link href="/public/lib/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
	<!-- Our CSS -->
	<link href="/public/css/styles.css" rel="stylesheet">
	<link href="/public/lib/bootstrap-3.3.7/css/datepicker.css" rel="stylesheet">
	
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" id="main-navbar" role="navigation">
	  <div class="container">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="<c:url value='/' />">VICM - Grace Church</a>
	    </div>
	    <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav navbar-right">
	        <li><a href="<c:url value='/about-us'/>">ABOUT</a></li>
	        <li><a href="#services">EVENTS</a></li>
	        <li><a href="<c:url value='/sermons'/>">SERMONS</a></li>
	        <li><a href="<c:url value='/gallery'/>">GALLERY</a></li>
	        <li><a href="#contact">BLOGS</a></li>
	        <sec:authorize access="isAnonymous()">
	        	<li><a href="<c:url value='/register' />"><span class="glyphicon glyphicon-pencil"></span> REGISTER</a></li>
	        	<li><a href="/login">LOGIN <span class="glyphicon glyphicon-log-in"></span></a></li>
	        </sec:authorize>
             <sec:authorize access="isAuthenticated()">
              <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                      <span class="glyphicon glyphicon-user"></span>
                      <sec:authentication property="principal.user.firstName" /> <b class="caret"></b>
                  </a>
                  <ul class="dropdown-menu">
                     <li><a href="/users/<sec:authentication property='principal.user.userId' />"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
                     <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
                  </ul>
              </li>
            </sec:authorize>
            <li> 
            	<a href = "/donate/checkouts" class = "btn btn-primary donate" role = "button">
	               <strong>DONATE</strong>
	            </a> 
            </li>
	      </ul>
	    </div>
	  </div>
	</nav>
	
	<sec:authorize access="hasRole('ROLE_UNVERIFIED')">
		<div class="alert alert-warning alert-dismissable">
		  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		  Your email id is unverified. <a href="/users/resend-verification-mail">Click here</a> to get the verification mail again.
		</div>
    </sec:authorize>

	
<div id="wrapper">

    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
        	<li class="sidebar-brand">
            	<p><strong>Personal settings</strong></p>
            </li>
            <c:if test="${user.editable}">
            <li>
                <a href="/users/${user.userId}/profile"><span class="glyphicon glyphicon-user"></span> &nbsp;&nbsp;Profile</a>
            </li>
            
            <li>
                <a href="/users/${user.userId}/account"><span class="glyphicon glyphicon-lock"></span> &nbsp;&nbsp;Account</a>
            </li>
            <li>
                <a href="/users/${user.userId}/notification"><span class="glyphicon glyphicon-envelope"></span> &nbsp;&nbsp;Notifications</a>
            </li>
            </c:if>
            
            <sec:authorize access="hasRole('ROLE_ADMIN')">
           	<li>
               <a href="/users/all"><span class="glyphicon glyphicon-list-alt"></span> &nbsp;&nbsp;VICM Users</a>
           	</li>
           	</sec:authorize>
           	
           	<sec:authorize access="hasRole('ROLE_EDITOR') or hasRole('ROLE_ADMIN')">
           	<li>
               <a href="/upload-gallery"><span class="glyphicon glyphicon-picture"></span> &nbsp;&nbsp;Gallery Upload</a>
           	</li>
           	</sec:authorize>
           	
           	<sec:authorize access="hasRole('ROLE_CONTRIBUTOR') or hasRole('ROLE_EDITOR') or hasRole('ROLE_ADMIN')">
           	<li class="dropdown">
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                     Blog <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                	<li><a href="/blog/create"><span class="glyphicon glyphicon-pencil"></span> &nbsp;&nbsp;Create</a></li>
                	<li><a href="/blog/published"><span class="glyphicon glyphicon-bookmark"></span> &nbsp;&nbsp;Published</a></li>
                	<li><a href="/blog/drafts"><span class="glyphicon glyphicon-pencil"></span> &nbsp;&nbsp;Drafts</a></li>
                </ul>
            </li>
            </sec:authorize>
            
            <sec:authorize access="hasRole('ROLE_EDITOR') or hasRole('ROLE_ADMIN')">
           	<li class="dropdown">
           		<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    	Sermon <b class="caret"></b>
               	</a>
               	<ul class="dropdown-menu">
               		<li><a href="/sermon/create"><span class="glyphicon glyphicon-pencil"></span> &nbsp;&nbsp;Create</a></li>
               		<li><a href="/sermon/published"><span class="glyphicon glyphicon-bookmark"></span> &nbsp;&nbsp;Published</a></li>
               		<li><a href="/sermon/drafts"><span class="glyphicon glyphicon-pencil"></span> &nbsp;&nbsp;Drafts</a></li>
               	</ul>
           	</li>
            </sec:authorize>
            
       </ul>
    </div>
    <!-- /#sidebar-wrapper -->