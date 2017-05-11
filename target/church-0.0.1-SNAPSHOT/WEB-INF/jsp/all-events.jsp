<%@include file="includes/header.jsp" %>

<div class="page-head events" >
	<div class="container">
		<h2 class="page-title">Events</h2>
	</div>
</div>
<div class="container white-container">
	
	<div class="row">
		<div class="col-sm-8 content ">
			<h2 class="section-title">Upcoming Events</h2>
			<ul class="event-list large">
				<c:forEach items="${events}" var="event" varStatus="loopStatus">
					<li>
						<h3 class="event-title"><a href="/events/event/${event.eventId}"><c:out value="${event.eventName}" /></a></h3>
						<span class="event-meta">
							<span><i class="fa fa-calendar"></i> <c:out value="${event.eventStartDateFormatted}" /></span>
							<span><i class="fa fa-map-marker"></i> <c:out value="${event.eventLocation}" /></span>
						</span>
						<p><c:out value="${event.eventDescription}" /></p>
						<a href="/events/event/${event.eventId}" class="btn btn-primary btn-more">More</a>
					</li>
				</c:forEach>
			</ul>
			
			<!-- Pager -->
			<tag:paginate max="15" offset="${offset}" count="${count}" uri="/events" next="&raquo;" previous="&laquo;" />
			
		</div>
		
		
		<div class="sidebar col-md-3 col-md-offset-1">
			<div class="widget">
				<h3 class="widget-title">Donations</h3>
				<p>Distinctio unde consequuntur delectus, repudiandae, impedit atque earum adipisci, explicabo perferendis.</p>
				<a href="#" class="button">Make a donation</a>
			</div>

			<div class="widget">
				<h3 class="widget-title">Text widget </h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum aliquam obcaecati velit, atque necessitatibus molestias ullam tempore itaque quidem sequi ea sed consectetur, eligendi cupiditate saepe! Hic veniam maiores explicabo.</p>
			</div>
		</div>
	</div>
</div>

<%@include file="includes/footer.jsp" %>