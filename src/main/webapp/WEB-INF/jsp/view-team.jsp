<%@include file="includes/header.jsp" %>

         <div class="container">
        	<div class="row">
				<%@include file="includes/sidebar.jsp" %>
				<div class="col-sm-9">
					<table class="table table-striped table-bordered">
						<thead class="thead-inverse">
							<tr>
								<th class="myColor"><strong>#</strong></th>
								<th class="myColor"><strong>Full Name</strong></th>
								<th class="myColor"><strong>Designation</strong></th>
								<th class="myColor"><strong>Facebook Profile</strong></th>
								<th class="myColor"><strong>Twitter Profile</strong></th>
								<th class="myColor"><strong>LinkedIn Profile</strong></th>
								<th class="myColor"><strong>Profile Picture</strong></th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${ourTeam}" var="team" varStatus="loopStatus">
								<tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}" > 
									<td>${offset + loopStatus.index + 1 }</td>
								<td><a href="/ourteam/view/${team.ourTeamId}/edit"><c:out value="${team.fullName}" /></a></td>
								<td><c:out value="${team.designation}" /></td>
								<td><c:out value="${team.faceBookProfile}" /></td>
								<td><c:out value="${team.twitterProfile}" /></td>
								<td><c:out value="${team.linkedInProfile}" /></td>
								<td>
									<div class="imgage">
	      								<img class="img-responsive" src="<c:out value='${team.profilePicture}'/>" alt="" width="50px" height="50px" >
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