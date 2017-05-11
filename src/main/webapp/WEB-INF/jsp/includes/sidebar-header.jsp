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
	<title>VICM - <c:out value="${PageTitle}"/></title>
	
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
	        <li><a href="<c:url value='/blogs'/>">BLOGS</a></li>
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

<span class="openbtn" onclick="openNav()">&nbsp;&nbsp;<i class="fa fa-bars" aria-hidden="true"></i></span>
<div id="mySidenav" class="sidenav">
       <!-- menu -->
      <div id="MainMenu">
        <div class="list-group panel">
          <a href="javascript:void(0)" class=" list-group-item-close text-right" data-parent="#MainMenu" onclick="closeNav()"><i class="fa fa-times" aria-hidden="true"></i></a>
          <a href="/users/<sec:authentication property='principal.user.userId' />/profile" class="list-group-item" data-parent="#MainMenu"><span class="glyphicon glyphicon-user"></span> &nbsp;&nbsp;Profile</a>
          <a href="/users/<sec:authentication property='principal.user.userId' />/account" class="list-group-item" data-parent="#MainMenu"><span class="glyphicon glyphicon-lock"></span> &nbsp;&nbsp;Account</a>
          <a href="/users/<sec:authentication property='principal.user.userId' />/notification" class="list-group-item" data-parent="#MainMenu"><span class="glyphicon glyphicon-envelope"></span> &nbsp;&nbsp;Notifications</a>
          
          <sec:authorize access="hasRole('ROLE_ADMIN')">
          <a href="/users/all" class="list-group-item" data-parent="#MainMenu"><span class="glyphicon glyphicon-list-alt"></span> &nbsp;&nbsp;Users</a>
          </sec:authorize>
          
          <sec:authorize access="hasRole('ROLE_EDITOR') or hasRole('ROLE_ADMIN')">
          <a href="/upload-gallery" class="list-group-item" data-parent="#MainMenu"><i class="fa fa-upload" aria-hidden="true"></i> &nbsp;&nbsp;Gallery Upload</a>
          </sec:authorize>
          
          <sec:authorize access="hasRole('ROLE_CONTRIBUTOR') or hasRole('ROLE_EDITOR') or hasRole('ROLE_ADMIN')">
          <a href="#user-blog" class="list-group-item" data-toggle="collapse" data-parent="#MainMenu">Blog&nbsp;&nbsp;<i class="fa fa-caret-down"></i></a>
          <div class="collapse" id="user-blog">
            <a href="/blog/create" class="list-group-item"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;&nbsp;Create</a>
            <a href="/blog/published" class="list-group-item"><span class="glyphicon glyphicon-bookmark"></span> &nbsp;&nbsp;Published</a>
            <a href="/blog/drafts" class="list-group-item"><span class="glyphicon glyphicon-pencil"></span> &nbsp;&nbsp;Drafts</a>
          </div>
          </sec:authorize>
          
          <sec:authorize access="hasRole('ROLE_EDITOR') or hasRole('ROLE_ADMIN')">
          <a href="#user-sermon" class="list-group-item" data-toggle="collapse" data-parent="#MainMenu">Sermon&nbsp;&nbsp;<i class="fa fa-caret-down"></i></a>
          <div class="collapse" id="user-sermon">
            <a href="/sermon/create" class="list-group-item"><i class="fa fa-plus-square" aria-hidden="true"></i>&nbsp;&nbsp;Create</a>
            <a href="/sermon/published" class="list-group-item"><span class="glyphicon glyphicon-bookmark"></span> &nbsp;&nbsp;Published</a>
            <a href="/sermon/drafts" class="list-group-item"><span class="glyphicon glyphicon-pencil"></span> &nbsp;&nbsp;Drafts</a>
          </div>
          </sec:authorize>
          
        </div>
      </div>
    
</div>

    