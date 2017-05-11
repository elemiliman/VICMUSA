<%@include file="includes/header.jsp" %>

<div class="page-head events" >
	<div class="container">
		<h2 class="page-title">Event</h2>
	</div>
</div>
<div class="container white-container">
	
	<div class="row">
		 <div class="col-md-6">
              <img class="img-responsive img-portfolio img-hover" src="<c:out value='${event.eventImage}'/>" alt="">
         </div>

         <div class="col-md-6">
         
			<h2 class="section-title"><strong><c:out value="${event.eventName}" /></strong></h2>
            Start: <c:out value="${event.eventStartDateFormatted}" /> <br>
            End: <c:out value="${event.eventEndDateFormatted}" /> <br>
            Time: <c:out value="${event.eventTime}" /> <br>
           	Venue: <c:out value="${event.eventLocation}" /><br>
            <div class="dropdown">
			  <a onclick="myFunction()" class = "btn btn-primary btn-more dropbtn" id="event-contact" role = "button">Contact Us</a>
			  <div id="myDropdown" class="dropdown-content">
			    <h2>Organizer</h2>
			    <p>NAME: <c:out value="${event.organizerName}" /></p>
			    <p>PHONE: <c:out value="${event.organizerPhone}" /></p>
			    <p>EMAIL: <c:out value="${event.organizerEmail}" /></p>
			    <p>WEBSITE: <a target="_blank" href="http://<c:url value='${event.organizerWebsite}' />"> <c:out value="${event.organizerWebsite}" /> </a></p>
			  </div>
			</div>
 
        </div>
        <br>
		<div class="content col-md-12">
			<h2 class="section-title">Description</h2>
			<p><c:out value="${event.eventDescription}" /></p>
		</div>
		
	</div>
</div>

<%@include file="includes/footer.jsp" %>