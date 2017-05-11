<%@include file="includes/header.jsp" %>

         <div class="container">
        	<div class="row">
				<%@include file="includes/sidebar.jsp" %>
				<div class="col-sm-9">
					<table class="table table-striped table-bordered">
						<thead class="thead-inverse">
							<tr>
								<th class="myColor"><strong>#</strong></th>
								<th class="myColor"><strong>Event Name</strong></th>
								<th class="myColor"><strong>Location</strong></th>
								<th class="myColor"><strong>Start Date</strong></th>
								<th class="myColor"><strong>End Date</strong></th>
								<th class="myColor"><strong>Time</strong></th>
								<th class="myColor"><strong>Image</strong></th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${createdEvents}" var="event" varStatus="loopStatus">
								<tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}" > 
									<td>${offset + loopStatus.index + 1 }</td>
								<td><a href="/event/${event.eventId}/edit"><c:out value="${event.eventName}" /></a></td>
								<td><c:out value="${event.eventLocation}" /></td>
								<td><c:out value="${event.eventStartDate}" /></td>
								<td><c:out value="${event.eventEndDate}" /></td>
								<td><c:out value="${event.eventTime}" /></td>
								<td>
									<div class="imgage">
	      								<img class="img-responsive" src="<c:out value='${event.eventImage}'/>" alt="" width="50px" height="50px" >
	  								</div>
								</td>
							</tr>
						</c:forEach>
						<tr>
						</tbody>
					</table>
					<tag:paginate max="15" offset="${offset}" count="${count}" uri="/event/view" next="&raquo;" previous="&laquo;" />
				</div>
			</div>
		</div>

<%@include file="includes/sidebar-footer.jsp" %>